package edu.westga.edu.employee_management.controller;

import java.util.function.UnaryOperator;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Validation {

	public static final String EMAIL_REGEX = "^(.+)@(.+)$";

	/**
	 * Returns a formatter for validating integer inputs and preventing non integer
	 * inputs
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return formatter for integers
	 */
	public static UnaryOperator<Change> integerValidationFormatter() {
		UnaryOperator<TextFormatter.Change> numberValidationFormatter = change -> {
			if (change.getText().matches("\\d+")) {
				return change;
			} else {
				change.setText("");
				return change;
			}
		};

		return numberValidationFormatter;
	}

	/**
	 * Sets Validation for email on given text field. Border of text field become
	 * red on invalid input
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param field the field to add validation to
	 */
	public static void setEmailInputValidation(TextField field) {
		field.textProperty().addListener(event -> {
			if (field.textProperty().getValue().matches(EMAIL_REGEX)) {
				field.setBorder(null);

			} else {
				BorderStroke stroke = new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(3),
						new BorderWidths(2), new Insets(-2));
				field.setBorder(new Border(stroke));
			}
		});
	}
}
