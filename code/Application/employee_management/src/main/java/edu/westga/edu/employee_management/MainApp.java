package edu.westga.edu.employee_management;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;


public class MainApp extends Application {
	private static final String APPLICATION_TITLE = "Employee Management Application";

    @Override
	public void start(Stage arg) throws IOException {
		setRoot(Scenes.LOGIN, APPLICATION_TITLE);
    }

	static void setRoot(Scenes fxml, String title) throws IOException {
		SceneController.openWindow(fxml, title);
    }


	/**
	 * Entry Point into the application
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param args
	 */
    public static void main(String[] args) {
        launch(args);
    }

}
