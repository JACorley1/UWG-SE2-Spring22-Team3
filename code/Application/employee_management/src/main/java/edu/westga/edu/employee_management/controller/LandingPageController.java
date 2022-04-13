package edu.westga.edu.employee_management.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import edu.westga.edu.employee_management.MainApp;
import edu.westga.edu.employee_management.SceneController;
import edu.westga.edu.employee_management.Scenes;
import edu.westga.edu.employee_management.model.UserLogin;
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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private Button scheduleButton;

	@FXML
    private Text currentRequestsText;

    @FXML
    private Text numberOfRequestsText;

	@FXML
	private Text profileErrorText;
	
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
	void openSchedule(ActionEvent event) {
		try {
			SceneController.openWindow(Scenes.SCHEDULEPAGE, "Schedule Page");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void openDailyTimeWindow(ActionEvent event) throws IOException {
		Button button = (Button) event.getSource();
		int rowIndex = GridPane.getRowIndex(button);

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
	 * @param login the user login
	 */
	public void setLogin(UserLogin login) {
		if (login != null) {
			this.bindUI(login);
		}
	}



	private void bindUI(UserLogin login) {
		this.viewModel = new LandingPageViewModel(login);
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
			String value = hours.getValue() == 0 ? "-" : hours.getValue().toString() + " hrs";
			hourText.textProperty().bind(hours.asString(value));
		}
	}

	private void bindDates() {
		int column = LandingPageViewModel.DATE_COL_INDEX;
		ListProperty<Object> dates = this.viewModel.getTimeProperty().get(column);
		for (int i = 0; i < 14; i++) {
			Text dateLabel = (Text) this.getNodeFromGridPane(column, i);
			ObjectProperty<LocalDate> date = (ObjectProperty<LocalDate>) dates.get(i);
			dateLabel.textProperty().bind(date.asString(this.formatDate(date.getValue())));
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

	private String formatDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d");
		String dateString = date.format(formatter);
		return dateString;
	}
}

