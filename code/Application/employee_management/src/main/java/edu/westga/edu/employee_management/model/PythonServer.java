package edu.westga.edu.employee_management.model;

public class PythonServer {
	private String path;

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
	 * Runs python server
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 */
	public void run() {
		try {
			Process process = Runtime.getRuntime().exec("py " + "\"" + this.path + "\"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
