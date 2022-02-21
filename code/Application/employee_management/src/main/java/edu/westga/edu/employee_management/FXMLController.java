package edu.westga.edu.employee_management;

import java.io.IOException;

import edu.westga.edu.employee_management.model.UserLogin;
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
    private Text incorrectCredentialsMessage;

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
	
	private UserLogin newLogin;

	@FXML
	private void btnClickAction(ActionEvent event) {
		this.newLogin = new UserLogin(this.usernameTxt.getText(), this.passwordTxt.getText());
		
		if (this.newLogin.verifyLogin()) {
			this.openLandingPage();
		} else if (this.usernameTxt.getText().equals("") || this.passwordTxt.getText().equals("")) {
			this.incorrectCredentialsMessage.setText("Please Input All Credentials And Try Again");
		} else {
			this.incorrectCredentialsMessage.setText("Incorrect Credentials. Please Try Again");
		}
		
	}

	private void openLandingPage() {
		try {
			SceneController.changeScene(Scenes.LANDINGPAGE, (Stage) this.loginButton.getScene().getWindow());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}