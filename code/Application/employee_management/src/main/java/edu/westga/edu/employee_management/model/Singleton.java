package edu.westga.edu.employee_management.model;

public class Singleton {
	private static Singleton single_instance = null;

	private EmployeeRequestManager requestManager;

	private Singleton() {
		this.requestManager = new EmployeeRequestManager();
	}

	public static Singleton getInstance() {
		if (single_instance == null)
			single_instance = new Singleton();

		return single_instance;
	}

	public EmployeeRequestManager getRequestManager() {
		return requestManager;
	}

}
