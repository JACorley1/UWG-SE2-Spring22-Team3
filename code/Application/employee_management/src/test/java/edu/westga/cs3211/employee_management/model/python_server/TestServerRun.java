package edu.westga.cs3211.employee_management.model.python_server;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.PythonServer;

class TestServerRun {
	private static final String SERVER_PATH = "../../server/main.py";

	@Test
	void testServerRun() {
		PythonServer server = new PythonServer(SERVER_PATH);
		server.start();
		this.waitTest();

		boolean serverRan = server.isAlive();
		server.exit();

		this.waitTest();

		assertAll(() -> {
			assertTrue(serverRan);
			assertFalse(server.isAlive());
		});

	}

	private void waitTest() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
