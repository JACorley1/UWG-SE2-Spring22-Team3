package edu.westga.cs3211.employee_management.model.python_server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.Client;
import edu.westga.edu.employee_management.model.PythonServer;
import edu.westga.edu.employee_management.model.RequestType;

class TestServerRequest {
	private static final String SERVER_PATH = "../../server/main.py";

	@Test
	void testServerRequest() {
		PythonServer server = new PythonServer(SERVER_PATH);
		Client client = new Client(RequestType.PING, "");
		server.start();
		this.waitTest();

		JSONObject response = new JSONObject();
		try {
			response = new JSONObject(client.sendRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		server.exit();

		String successCode = "successCode";

		assertEquals(1, response.getInt(successCode));

	}

	private void waitTest() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
