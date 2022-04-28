package edu.westga.edu.employee_management.model.server_communication;

import org.json.JSONObject;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import edu.westga.edu.employee_management.model.RequestType;

/**
 * Manages the data for a Response object
 * 
 * @author Team 3
 * @version Sprint 3
 */
public class Client extends Thread {

	private RequestType type;
	private String request;
	private static Context context = ZMQ.context(1);
	private static Socket socket = context.socket(ZMQ.REQ);
	private boolean responseRecevied;

	/**
	 * 
	 * Creates new instance of Client
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param type
	 * @param request
	 */
	public Client(RequestType type, String request) {
		if (type == null) {
			throw new IllegalArgumentException("Type cannot be null");
		}

		if (request == null) {
			throw new IllegalArgumentException("Request cannot be null");
		}

		this.type = type;
		this.request = request;
	}

	/**
	 * Disconnects from python server socket
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 * 
	 * @param server
	 *
	 */
	public static void disconnectFromSocket(PythonServer server) {
		if (!server.isAlive()) {
			return;
		}

		disconnectFromSocket();
	}

	/**
	 * Disconnects from server socket
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 */
	public static void disconnectFromSocket() {
		JSONObject request = new JSONObject();
		request.put("request", "exit");
		System.out.println("Client - Sending exit");
		socket.send(request.toString().getBytes(ZMQ.CHARSET), 0);

		socket.close();
		context.term();
	}

	/**
	 * Connects to server socket
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 */
	public static void connectToSocket() {
		context = ZMQ.context(1);
		socket = context.socket(ZMQ.REQ);
		System.out.println("Connecting to Employee Management Server");
		socket.connect("tcp://127.0.0.1:5555");
	}

	/**
	 * Send Client's request to server
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the server's response
	 */
	public String sendRequest() throws Exception {
		JSONObject request = new JSONObject();
		request.put("requestType", this.type.toString());
		request.put("request", this.request);
		socket.setReceiveTimeOut(3000);
		socket.send(request.toString().getBytes(ZMQ.CHARSET), 0);

		byte[] reply = socket.recv(0);
		String response = new String(reply, ZMQ.CHARSET);
		System.out.println("Client - Received " + response);

		this.responseRecevied = true;
		return response;
	}
}
