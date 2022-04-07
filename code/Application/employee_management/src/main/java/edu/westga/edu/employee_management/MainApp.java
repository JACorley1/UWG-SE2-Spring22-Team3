package edu.westga.edu.employee_management;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import edu.westga.edu.employee_management.model.Client;
import edu.westga.edu.employee_management.model.PythonServer;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
	private static final String SERVER_PATH = "../../../server/main.py";
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
		Path appDirectory = Paths.get("").toAbsolutePath();
		String path = appDirectory.normalize().toString() + SERVER_PATH;

		PythonServer server = new PythonServer(path);
		server.run();

		Client.connectToSocket();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> Client.disconnectFromSocket()));
		launch(args);

    }


}
