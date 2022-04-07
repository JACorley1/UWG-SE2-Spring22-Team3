package edu.westga.edu.employee_management.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map.Entry;

import edu.westga.edu.employee_management.MainApp;
import edu.westga.edu.employee_management.SceneController;
import edu.westga.edu.employee_management.Scenes;
import edu.westga.edu.employee_management.model.DaySheet;
import edu.westga.edu.employee_management.model.EmployeeManager;
import edu.westga.edu.employee_management.model.EmployeeProfile;
import edu.westga.edu.employee_management.model.PayPeriod;
import edu.westga.edu.employee_management.model.TimeSheet;
import edu.westga.edu.employee_management.model.UserLogin;
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

	private static final int BUTTON_COL_INDEX = 2;

	private static final int HOURS_COL_INDEX = 1;

	private EmployeeProfile user;
	private PayPeriod currentPayPeriod;
	private TimeSheet currentTimeSheet;

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
	private TextField dateStartedField;

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
	private Text profileErrorText;

	private EmployeeManager manager;

	private UserLogin login;

	@FXML
	void payPeriodBack(ActionEvent event) {

	}

	@FXML
	void clockIn(ActionEvent event) {
		this.user.getTimeSheet(LocalDate.now()).clockIn();
		this.updatePage();
	}

	@FXML
	void clockOut(ActionEvent event) {
		this.user.getTimeSheet(LocalDate.now()).clockOut();
		this.updatePage();
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
			Button button = (Button) event.getSource();
			DaySheet sheet = this.getButtonDaySheet(button);

			this.openDailyTimeWindow(sheet);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void openDailyTimeWindow(DaySheet sheet) throws IOException {
		FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/" + Scenes.DAILYTIMEPAGE + ".fxml"));
		Scene scene = new Scene(loader.load());
		Stage newWindow = new Stage();
		newWindow.setScene(scene);
		newWindow.setTitle("Daily Time");

		newWindow.setUserData(sheet);

		DailyTimePageController controller = (DailyTimePageController) loader.getController();
		controller.initializeUserData(newWindow);

		newWindow.show();
	}

	private DaySheet getButtonDaySheet(Button button) {
		int rowIndex = GridPane.getRowIndex(button);
		var children = this.secondWeekGrid.getChildren();

		if (children.contains(button)) {
			rowIndex += 7;
		}

		DaySheet sheet = this.currentTimeSheet.getTimeSheet().get(rowIndex);
		return sheet;
	}

	/**
	 * Initializes Landing Page
	 *
	 * Preconditions: none Postconditions: none
	 *
	 */
	public void initialize() {
		this.manager = new EmployeeManager();
		this.login = new UserLogin();

	}

	private void setUser() {
		if (this.getProfile() != null) {
			this.user = this.getProfile();
		} else {
			this.user = new EmployeeProfile(1, "Sophie", "", "Atelier", "example@gmail.com", "123-456-7890", true,
					"user name", "password");
		}
	}

	private void updateClockButtons() {
		if (this.currentTimeSheet.hasOpenTime()) {
			this.clockInButton.setDisable(true);
			this.clockOutButton.setDisable(false);
		} else {
			this.clockOutButton.setDisable(true);
			this.clockInButton.setDisable(false);
		}
	}

	private void updateGrid() {
		for (Entry<Integer, DaySheet> time : this.currentTimeSheet.getTimeSheet().entrySet()) {
			int dayIndex = time.getKey();
			this.setHoursTextField(dayIndex, HOURS_COL_INDEX, time.getValue().getHoursWorked());

			Node node = this.getNodeFromGridPane(BUTTON_COL_INDEX, dayIndex);
			node.setDisable(false);
		}
	}

	private void setHoursTextField(int dayIndex, int col, double hoursWorked) {
		Node node = this.getNodeFromGridPane(col, dayIndex);
		TextField field = (TextField) node;
		DecimalFormat formatter = new DecimalFormat("###.##");
		field.textProperty().set(formatter.format(hoursWorked) + " hr");
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


	private void updateGridPeriod() {
		this.payPeriodLabel.textProperty().set(this.currentPayPeriod.toString());
		List<LocalDate> dates = this.currentPayPeriod.toList();
		for (int i = 0; i < dates.size(); i++) {
			Node node = this.getNodeFromGridPane(0, i);
			Text text = (Text) node;

			LocalDate date = dates.get(i);
			String formattedDate = this.formatDate(date);

			text.textProperty().set(formattedDate);
		}
	}

	private String formatDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d");
		String dateString = date.format(formatter);
		return dateString;
	}

	private void updatePage() {
		this.updateClockButtons();
		this.updateGrid();
	}

	private void setUpLandingPageProfileFields(EmployeeProfile profile) {
		if (profile != null) {
			this.idField.setText(String.valueOf(profile.getID()));
			this.firstNameField.setText(profile.getFirstName());
			this.middleNameField.setText(profile.getMiddleName());
			this.lastNameField.setText(profile.getLastName());
			this.emailField.setText(profile.getEmail());
			this.phoneField.setText(profile.getPhone());
			this.employeeNameLabel.setText(profile.getFirstName() + " " + profile.getLastName());
		} else {
			EmployeeProfile sampleProfile = new EmployeeProfile(1, "Sophie", "", "Atelier", "example@gmail.com",
					"123-456-7890", true, "user name", "password");
			this.profileErrorText
					.setText("Profile could not be found," + System.lineSeparator() + "showing sample profile.");
			this.profileErrorText.setVisible(true);
			this.setUpLandingPageProfileFields(sampleProfile);
		}
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
			this.login = login;
			this.setUpLandingPageProfileFields(this.getProfile());
			this.setUser();

			if (this.user == null) {
				return;
			}

			if (this.user.isHR()) {
				this.hrViewButton.visibleProperty().set(true);
			}

			LocalDate today = LocalDate.now();
			this.currentPayPeriod = new PayPeriod(today);
			this.currentTimeSheet = this.user.getTimeSheet(today);

			this.updatePage();
			this.updateGridPeriod();
		}
	}

	private EmployeeProfile getProfile() {
		String userName = this.login.getUsername();
		String pass = this.login.getPassword();
		for (EmployeeProfile currProfile : this.manager.getProfiles()) {
			if (currProfile.getUserName().equalsIgnoreCase(userName)
					|| currProfile.getPassword().equalsIgnoreCase(pass)) {
				return currProfile;
			}
		}
		return null;
	}
}

