package com.company;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import static com.company.Controller.deleteRemote;
import static com.company.Controller.insertRemote;


public class Message implements Runnable{

	public static void broadcast(BroadcastPacket msg) throws IOException{
		sendUDPMessage(msg, "230.0.0.0", 4321);
	}
	public void receiveUDPMessage(String ip, int port) throws
	  IOException {
		byte[] buffer=new byte[1024];
		MulticastSocket socket=new MulticastSocket(port);
		InetAddress group=InetAddress.getByName(ip);
		socket.joinGroup(group);
		while(true){
			DatagramPacket packet=new DatagramPacket(buffer,
			  buffer.length);
			socket.receive(packet);
			try{
				ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
				BroadcastPacket messageClass = (BroadcastPacket) iStream.readObject();
				iStream.close();
				if(!messageClass.getId().equals(Controller.id) ){
					if(messageClass.getOperation() == 'i'){
						insertRemote(messageClass);
					}else if(messageClass.getOperation() == 'd'){
						deleteRemote(messageClass);
					}

				}


			}
			catch (Exception e){
				System.out.println(e);
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
}
