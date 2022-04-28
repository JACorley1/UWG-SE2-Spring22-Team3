package edu.westga.edu.employee_management.model.server_communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Manages the data for the PythonServer object
 * 
 * @author Team 3
 * @version Sprint 2
 */
public class PythonServer extends Thread {
	private String path;
	private volatile boolean exit;
	private BufferedReader stdInput;
	private BufferedReader stdError;

	/**
	 * Creates new instance of PythonServer
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param path
	 */
	public PythonServer(String path) {
		if (path == null || path.isEmpty()) {
			throw new IllegalArgumentException("Path cannot be null");
		}
		this.path = path;
	}
	
	/**
	 * Runs the PythonServer
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 */
	@Override
	public void run() {
		try {
			Process process = Runtime.getRuntime().exec("py " + "\"" + this.path + "\"");
			Client.connectToSocket();

			this.stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

			this.stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			String response = this.stdInput.readLine();
			// read the output from the command
			System.out.println("---SERVER RESPONSES--:\n");
			while (!this.exit) {
				System.out.println(response);
				response = this.stdInput.readLine();
				if (response == null) {
					break;
				}
			}

			// read any errors from the attempted command
			System.out.println("---SERVER ERROR-- (if any):\n");
			while ((response = this.stdError.readLine()) != null && !this.exit) {
				System.err.println(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Exits server
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 */
	public void exit() {
		Client.disconnectFromSocket(this);
		this.exit = true;
	}
}
