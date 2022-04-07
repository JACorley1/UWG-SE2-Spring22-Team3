package edu.westga.edu.employee_management.model;

import org.json.JSONObject;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Client extends Thread {

	private RequestType type;
	private String request;
	private String response;
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

		if (request == null || request.isEmpty()) {
			throw new IllegalArgumentException("Request cannot be null or empty");
		}

		this.type = type;
		this.request = request;
	}

	@Override
	public void run() {
		// this.sendRequest();
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
		System.out.println("Connecting to hello world server");
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
	public String sendRequest() {
		JSONObject request = new JSONObject();
		request.put("requestType", this.type.toString());
		request.put("request", this.request);
		socket.send(request.toString().getBytes(ZMQ.CHARSET), 0);

		byte[] reply = socket.recv(0);
		String response = new String(reply, ZMQ.CHARSET);
		System.out.println("Client - Received " + response);

		this.responseRecevied = true;
		return response;
	}

	/**
	 * Gets the response
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the response
	 */
	public String getResponse() {

		return this.response;
	}

}
