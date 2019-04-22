package com.company;


import javax.sound.sampled.Port;
import java.io.*;
import java.net.*;

import static com.company.Controller.deleteRemote;
import static com.company.Controller.insertRemote;


public class Message implements Runnable {

	public static void broadcast(BroadcastPacket msg) throws IOException {
		sendUDPMessage(msg, "230.0.0.0", 4321);
	}

	public void receiveUDPMessage(String ip, int port) throws	IOException {
		byte[] buffer = new byte[1024];
		MulticastSocket socket = new MulticastSocket(port);
		InetAddress group = InetAddress.getByName(ip);
		socket.joinGroup(group);
		while (true) {
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			socket.receive(packet);
			try {
				ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
				BroadcastPacket messageClass = (BroadcastPacket) iStream.readObject();
				iStream.close();
				if (!messageClass.getId().equals(Controller.id)) {
					if (messageClass.getOperation() == 'i') {
						insertRemote(messageClass);
					} else if (messageClass.getOperation() == 'd') {
						deleteRemote(messageClass);
					}
				}


			} catch (Exception e) {
				System.out.println("error");
				System.out.println(e.toString());
				break;
			}


//			String msg=new String(packet.getData(),
//			  packet.getOffset(),packet.getLength());
//			System.out.println("[Multicast UDP message received] >> "+msg);
//			if("OK".equals(msg)) {
//				System.out.println("No more message. Exiting : "+msg);
//				break;
//			}
		}
		socket.leaveGroup(group);
		socket.close();
	}

	@Override
	public void run() {
		try {
			receiveUDPMessage("230.0.0.0", 4321);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void sendUDPMessage(BroadcastPacket message,
									  String ipAddress, int port) throws IOException {
		DatagramSocket socket = new DatagramSocket();
		InetAddress group = InetAddress.getByName(ipAddress);

		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		ObjectOutput oo = new ObjectOutputStream(bStream);
		oo.writeObject(message);
		oo.close();

		byte[] msg = bStream.toByteArray();
		DatagramPacket packet = new DatagramPacket(msg, msg.length,
				group, port);
		socket.send(packet);
		socket.close();
	}

	public static void server(int port) {
		ServerSocket serverSocket = null;
		Socket s = null;
		System.out.println(" Wait !! ");

		try {
			//  Initialising the ServerSocket
			serverSocket = new ServerSocket(port);

			// Gives the Server Details Machine name,
			Port number;

			System.out.println("Server Started  :" + s);

			try {

				// makes a socket connection to particular client after
				// which two way communication take place

				s = serverSocket.accept();

				System.out.println("Client Connected  :" + s);

				// Receive message from client i.e Request from client

				DataInputStream ins = new DataInputStream(s.getInputStream());
				// Send message to the client i.e Response

				PrintStream ios = new PrintStream(s.getOutputStream());
				ios.println("Hello from server");
				ios.close();

				s.close();

			} catch (SocketException se) {
				System.out.println("Server Socket problem  " + se.getMessage());
			} catch (Exception e) {
				System.out.println("Couldn't start " + e.getMessage());
			}

			System.out.println(" Connection from :  " + s.getInetAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void client(String ipAddress, int port){
		Socket sock=null;
		DataInputStream dis=null;
		PrintStream ps=null;
		System.out.println(" Trying to connect");

		try
		{
			// to get the ip address of the server by the name

			InetAddress ip =InetAddress.getByName (ipAddress);

			// Connecting to the port 1025 declared in the Serverclass
			// Creates a socket with the server bind to it.

			sock = new Socket(ip, port);
			ps= new PrintStream(sock.getOutputStream());
			ps.println(" Hi from client");
			DataInputStream is = new DataInputStream(sock.getInputStream());
			System.out.println(is.readLine());

		} catch(SocketException e) {
			System.out.println("SocketException " + e);
		} catch(IOException e)	{
			System.out.println("IOException " + e);
		}

		// Finally closing the socket from the client side
//		finally	{
//			try {
//				sock.close();
//			} catch(IOException ie) {
//				System.out.println(" Close Error   :" + ie.getMessage());
//			}
//		}
	}
}