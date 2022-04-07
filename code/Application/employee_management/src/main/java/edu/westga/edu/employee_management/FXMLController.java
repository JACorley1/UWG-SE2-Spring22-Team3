package edu.westga.edu.employee_management;

import java.io.IOException;

import edu.westga.edu.employee_management.controller.LandingPageController;
import edu.westga.edu.employee_management.model.UserLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		
		if (this.usernameTxt.getText().equals("") || this.passwordTxt.getText().equals("")) {
			this.incorrectCredentialsMessage.setText("Please Input All Credentials And Try Again");
		} else if (this.newLogin.verifyLoginCredentials()) {
			this.openLandingPage();
		} else {
			this.incorrectCredentialsMessage.setText("Incorrect Credentials. Please Try Again");
		} 
		
	}

	private void openLandingPage() {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/" + Scenes.LANDINGPAGE + ".fxml"));
			Parent parent = loader.load();

			Scene landingPageScene = new Scene(parent);

			LandingPageController controller = loader.getController();
			controller.setLogin(new UserLogin(this.usernameTxt.getText(), this.passwordTxt.getText()));

			Stage window = (Stage) this.loginButton.getScene().getWindow();
			window.setScene(landingPageScene);
			window.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}