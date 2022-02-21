package edu.westga.edu.employee_management;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

	private static Scene loadFXML(Scenes fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/" + fxml + ".fxml"));
        return new Scene(fxmlLoader.load());
    }

	/**
	 * Opens window for given fxml file
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param fxml
	 * @throws IOException
	 */
	public static void openWindow(Scenes fxml) throws IOException {
		Scene scene = SceneController.loadFXML(fxml);
		Stage newWindow = new Stage();
		newWindow.setScene(scene);

		newWindow.show();
	}

	/**
	 * Opens window for given fxml file
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param fxml
	 * @param title
	 * @throws IOException
	 */
	public static void openWindow(Scenes fxml, String title) throws IOException {
		Scene scene = SceneController.loadFXML(fxml);
		Stage newWindow = new Stage();
		newWindow.setTitle(title);
		newWindow.setScene(scene);

		newWindow.show();
	}

	/**
	 * Closes the given scene
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param stage
	 */
	public static void closeWindow(Stage stage) {
		stage.close();
	}

	/**
	 * Changes scene in current window
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param fxml  the fxml of the scene to load
	 * @param stage the stage to be changed
	 * @throws IOException
	 */
	public static void changeScene(Scenes fxml, Stage stage) throws IOException {
		Scene scene = SceneController.loadFXML(fxml);
		stage.setScene(scene);
	}
}
