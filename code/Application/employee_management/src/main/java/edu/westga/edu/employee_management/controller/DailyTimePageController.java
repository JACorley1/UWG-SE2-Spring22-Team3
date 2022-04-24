package edu.westga.edu.employee_management.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import edu.westga.edu.employee_management.model.DaySheet;
import edu.westga.edu.employee_management.model.EmployeeTime;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DailyTimePageController {

	private Stage stage;
	private DaySheet sheet;

	@FXML
	private Label totalHoursLabel;

	@FXML
	private GridPane clockinGrid;

	/**
	 * Initializes passed in user data
	 * 
	 * Preconditions: none Postconditions: none
	 *
	 * @param stage the window stage
	 */
	public void initializeUserData(Stage stage) {
		this.stage = stage;
		this.sheet = (DaySheet) this.stage.getUserData();
		double hours = this.sheet.getHoursWorked();
		NumberFormat formatter = new DecimalFormat("#0.0");
		this.totalHoursLabel.textProperty().setValue("Hours: " + formatter.format(hours));
		this.createTimeTextFields();
	}

	private void createTimeTextFields() {
		for (EmployeeTime time : this.sheet.getTimes()) {
			TextField clockinField = new TextField();
			TextField clockoutField = new TextField();

			clockinField.setDisable(true);
			clockoutField.setDisable(true);

			clockinField.setText(this.formatTime(time.getClockInTime()));
			if (time.getClockOutTime() == null) {
				clockoutField.setText("-");
			} else {
				clockoutField.setText(this.formatTime(time.getClockOutTime()));
			}

			this.clockinGrid.addRow(this.clockinGrid.getRowCount(), clockinField, clockoutField);
		}
	}

	private String formatTime(LocalDateTime time) {
		String timeString = time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
		return timeString;
	}
}
