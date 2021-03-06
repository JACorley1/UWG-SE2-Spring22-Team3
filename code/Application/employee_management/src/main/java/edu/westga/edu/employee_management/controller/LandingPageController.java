package edu.westga.edu.employee_management.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import edu.westga.edu.employee_management.MainApp;
import edu.westga.edu.employee_management.SceneController;
import edu.westga.edu.employee_management.Scenes;
import edu.westga.edu.employee_management.model.EmployeeProfile;
import edu.westga.edu.employee_management.model.EmployeeRequest;
import edu.westga.edu.employee_management.model.manager.EmployeeRequestManager;
import edu.westga.edu.employee_management.viewmodel.LandingPageViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Manages the data for an LandingPageController object
 * 
 * @author Team 3
 * @version Sprint 3
 */
public class LandingPageController {
	
	private LandingPageViewModel viewModel;

	@FXML
	private Button logOutButton;

	@FXML
	private Button clockInButton;

	@FXML
	private Button clockOutButton;

	@FXML
	private Text employeeNameLabel;

	@FXML
	private Button hrViewButton;

	@FXML
	private TextField idField;

	@FXML
	private TextField firstNameField;

	@FXML
	private TextField emailField;

	@FXML
	private TextField phoneField;

	@FXML
	private TextField middleNameField;

	@FXML
	private TextField lastNameField;

	@FXML
	private Text payPeriodLabel;

	@FXML
	private Button payPeriodBackButton;

	@FXML
	private Button payPeriodForwardButton;

	@FXML
	private GridPane firstWeekGrid;

	@FXML
	private GridPane secondWeekGrid;
	
	@FXML
    private Button viewCurrentRequestsButton;

	@FXML
    private Text currentRequestsText;

    @FXML
    private Text numberOfRequestsText;

	@FXML
	private Text profileErrorText;
	
	@FXML
	private Button editButton;

	@FXML
	private Button doneButton;

	@FXML
	private Button cancelButton;

	@FXML
    void onViewRequestsButtonClick(ActionEvent event) {
		try {
			SceneController.openWindow(Scenes.REQUESTSPAGE, "EmployeeRequestsPage");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	@FXML
	void payPeriodBack(ActionEvent event) {
		this.viewModel.decrementPayPeriod();
	}

	@FXML
	void clockIn(ActionEvent event) {
		this.viewModel.clockIn();
	}

	@FXML
	void clockOut(ActionEvent event) {
		this.viewModel.clockOut();
	}
	
	@FXML
	void logOut(ActionEvent event) {
		try {
			EmployeeRequestManager requestManager = EmployeeRequestManager.getInstance();
			requestManager.setConfirmedRequests(new ArrayList<EmployeeRequest>());
			requestManager.setPendingRequests(new ArrayList<EmployeeRequest>());
			requestManager.setAllEmployeesConfirmedRequests(new ArrayList<EmployeeRequest>());
			requestManager.setAllEmployeesPendingRequests(new ArrayList<EmployeeRequest>());
			requestManager.setNumberOfRequests(0);
			
			SceneController.changeScene(Scenes.LOGIN, (Stage) this.logOutButton.getScene().getWindow());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	void openHRView(ActionEvent event) {
		try {
			SceneController.openWindow(Scenes.HRLANDINGPAGE, "HR Landing Page");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void payPeriodForward(ActionEvent event) {
		this.viewModel.incrementPayPeriod();
	}

	@FXML
	void openDailyTime(ActionEvent event) {
		try {
			this.openDailyTimeWindow(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onCancelEdit(ActionEvent event) {
		this.viewModel.resetProfileValues();
		this.setProfileMode(false);
	}

	@FXML
	void onDoneEdit(ActionEvent event) {
		this.viewModel.setProfile();
		this.setProfileMode(false);
	}

	@FXML
	void onEdit(ActionEvent event) {
		this.middleNameField.setDisable(false);
		this.emailField.setDisable(false);
		this.phoneField.setDisable(false);
		this.setProfileMode(true);
	}

	private void setProfileMode(boolean isEditing) {
		this.doneButton.setVisible(isEditing);
		this.cancelButton.setVisible(isEditing);
		this.editButton.setVisible(!isEditing);

		this.middleNameField.setDisable(!isEditing);
		this.emailField.setDisable(!isEditing);
		this.phoneField.setDisable(!isEditing);
	}

	private void openDailyTimeWindow(ActionEvent event) throws IOException {
		Button button = (Button) event.getSource();
		int rowIndex = GridPane.getRowIndex(button);
		int colIndex = LandingPageViewModel.BUTTON_COL_INDEX;
		if (!(this.getNodeFromGridPane(colIndex, rowIndex) == button)) {
			rowIndex += 7;
		}

		FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/" + Scenes.DAILYTIMEPAGE + ".fxml"));
		Scene scene = new Scene(loader.load());
		Stage newWindow = new Stage();
		newWindow.setScene(scene);
		newWindow.setTitle("Daily Time");

		newWindow.setUserData(this.viewModel.getDailyTimeUserData(rowIndex));

		DailyTimePageController controller = (DailyTimePageController) loader.getController();
		controller.initializeUserData(newWindow);

		newWindow.show();
	}

	/**
	 * Sets the login field and updates the user alongside
	 *
	 * @Preconditions: login != null
	 * @Postconditions: none
	 * 
	 * @param user the user login
	 */
	public void setLogin(EmployeeProfile user) {
		if (user != null) {
			this.bindUI(user);
			this.setProfileMode(false);
			this.setValidation();
		}
	}



	private void bindUI(EmployeeProfile user) {
		this.viewModel = new LandingPageViewModel(user);
		this.hrViewButton.visibleProperty().bind(this.viewModel.getHrViewButtonVisibleProperty());
		this.idField.textProperty().bindBidirectional(this.viewModel.getIdProperty());
		this.firstNameField.textProperty().bind(this.viewModel.getFirstNameProperty());
		this.middleNameField.textProperty().bindBidirectional(this.viewModel.getMiddleNameProperty());
		this.lastNameField.textProperty().bind(this.viewModel.getLastNameProperty());
		this.emailField.textProperty().bindBidirectional(this.viewModel.getEmailProperty());
		this.phoneField.textProperty().bindBidirectional(this.viewModel.getPhoneProperty());
		this.employeeNameLabel.textProperty().bind(this.viewModel.getEmployeeNameProperty());

		this.clockInButton.disableProperty().bind(this.viewModel.getClockInDisabledProperty());
		this.clockOutButton.disableProperty().bind(this.viewModel.getClockOutDisabledProperty());
		this.payPeriodLabel.textProperty().bind(this.viewModel.getPayPeriodTextProperty());

		this.numberOfRequestsText.textProperty().bind(this.viewModel.getNumberOfRequestsProperty());

		this.bindDates();
		this.bindHours();
		this.bindButtons();
	}

	private void bindButtons() {
		int column = LandingPageViewModel.BUTTON_COL_INDEX;
		ListProperty<Object> buttons = this.viewModel.getTimeProperty().get(column);
		for (int i = 0; i < 14; i++) {
			Button button = (Button) this.getNodeFromGridPane(column, i);
			BooleanProperty disabled = (BooleanProperty) buttons.get(i);
			button.disableProperty().bind(disabled);
		}
	}

	private void bindHours() {
		int column = LandingPageViewModel.HOURS_COL_INDEX;
		ListProperty<Object> dayHours = this.viewModel.getTimeProperty().get(column);
		for (int i = 0; i < 14; i++) {
			TextField hourText = (TextField) this.getNodeFromGridPane(column, i);
			DoubleProperty hours = (DoubleProperty) dayHours.get(i);
			hourText.textProperty().bind(hours.asString("%.1f hrs"));
		}
	}

	private void bindDates() {
		int column = LandingPageViewModel.DATE_COL_INDEX;
		ListProperty<Object> dates = this.viewModel.getTimeProperty().get(column);
		for (int i = 0; i < 14; i++) {
			Text dateLabel = (Text) this.getNodeFromGridPane(column, i);
			ObjectProperty<LocalDate> date = (ObjectProperty<LocalDate>) dates.get(i);
			dateLabel.textProperty().bind(date.asString("%1$ta %1$tb, %1$td"));
		}
	}

	private Node getNodeFromGridPane(int col, int row) {
		GridPane gridPane = this.firstWeekGrid;
		if (row >= 7) {
			gridPane = this.secondWeekGrid;
			row -= 7;
		}

		for (Node node : gridPane.getChildren()) {
			if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				return node;
			}
		}
		return null;
	}

	private void setValidation() {
		this.phoneField.setTextFormatter(new TextFormatter<>(Validation.integerValidationFormatter()));
		Validation.setEmailInputValidation(this.emailField);

		this.doneButton.disableProperty().bind(this.emailField.borderProperty().isNotNull());
	}

}

