package edu.westga.edu.employee_management;

public enum Scenes {
	LOGIN("primary"), LANDINGPAGE("LandingPage");

	private String filename;

	Scenes(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return this.filename;
	}

}
