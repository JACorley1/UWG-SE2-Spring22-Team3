package edu.westga.edu.employee_management;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXMLController {

	@FXML
	private Text applicationNameText;

	@FXML
	private Button loginButton;

	@FXML
	private Text passwordText;

	@FXML
	private TextField passwordTxt;

	@FXML
	private Text usernameText;

	@FXML
	private TextField usernameTxt;

	@FXML
	private void btnClickAction(ActionEvent event) {
		this.openLandingPage();
	}

	private void openLandingPage() {
		try {
			SceneController.changeScene(Scenes.LANDINGPAGE, (Stage) this.loginButton.getScene().getWindow());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}