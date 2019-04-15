package com.company;

import java.io.IOException;

import static com.company.Message.broadcast;

public class Controller {
	private static CRDT crdt;
	public static String id;
	public Message message;
	public Controller(String id) {
		this.crdt = new CRDT();
		this.id = id;
		Thread t=new Thread(new Message());
		t.start();
	}
	public void insertLocal(char c, float position) throws IOException {
		crdt.insert(id,c,position);
		BroadcastPacket packet = new BroadcastPacket(id,c,'i',position);
		broadcast(packet);
	}
	public static void insertRemote(BroadcastPacket packet){
		crdt.insert(packet.getId(),packet.getValue(),packet.getPosition());
	}
	public void deleteLocal(float position){
		crdt.delete(id,position);
	}

	public static void deleteRemote(BroadcastPacket packet){
		crdt.delete(id,packet.getPosition());
	}
	public void printDocument(){
		crdt.print();
		System.out.println();
	}
}
