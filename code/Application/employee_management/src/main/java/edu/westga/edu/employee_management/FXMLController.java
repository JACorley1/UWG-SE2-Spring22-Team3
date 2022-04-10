package edu.westga.edu.employee_management;

import java.io.IOException;

import edu.westga.edu.employee_management.controller.LandingPageController;
import edu.westga.edu.employee_management.model.UserLogin;
import edu.westga.edu.employee_management.viewmodel.LoginPageViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXMLController {
	private LoginPageViewModel viewModel;
	
	@FXML
    private Text incorrectCredentialsMessage;

	@FXML
	private TextField passwordTxt;

	@FXML
	private TextField usernameTxt;

	@FXML
	private void btnClickAction(ActionEvent event) {
		boolean isValid = this.viewModel.verifyLoginInfo();
		if (isValid) {
			this.openLandingPage();
		}
	}

	private void openLandingPage() {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/" + Scenes.LANDINGPAGE + ".fxml"));
			Parent parent = loader.load();

			Scene landingPageScene = new Scene(parent);

			LandingPageController controller = loader.getController();
			controller.setLogin(new UserLogin(this.usernameTxt.getText(), this.passwordTxt.getText()));

			Stage window = (Stage) this.usernameTxt.getScene().getWindow();
			window.setScene(landingPageScene);
			window.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Initializes Landing Page
	 *
	 * Preconditions: none 
	 * Postconditions: none
	 *
	 */
	public void initialize() {
		this.viewModel = new LoginPageViewModel();
		this.viewModel.passwordProperty.bind(this.passwordTxt.textProperty());
		this.viewModel.usernameProperty.bind(this.usernameTxt.textProperty());
		this.incorrectCredentialsMessage.textProperty().bind(this.viewModel.incorrectCredentialsMessageProperty);
	}

}