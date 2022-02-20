package edu.westga.edu.employee_management;
/*
Put header here


 */

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FXMLController {
    
    @FXML
    private Label lblOut;
    
    @FXML
    private void btnClickAction(ActionEvent event) {
		this.openLandingPage();
    }
    
	private void openLandingPage() {
		try {
			SceneController.changeScene(Scenes.LANDINGPAGE, (Stage) this.lblOut.getScene().getWindow());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
