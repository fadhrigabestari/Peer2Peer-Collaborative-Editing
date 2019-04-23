package com.company;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.util.ArrayList;

import static com.company.Message.broadcast;

public class Controller {
	private static CRDT crdt;
	public static String id;
	public Message message;
	private static TextEditor textEditor;
	public static ArrayList<VersionVectorPacket> vectorVersion;
	public static ArrayList<DeletionBufferPacket> delBuffer;

	public Controller(String id) {
		this.crdt = new CRDT();
		this.id = id;
		Thread t=new Thread(new Message());
		t.start();
		textEditor = new TextEditor();
		vectorVersion = new ArrayList<>();
		delBuffer = new ArrayList<>();
	}

	public static void insertLocal(char c, int index) throws IOException {
		ArrayList<Integer> position = createPosition(index);
		System.out.println(position);
		crdt.insert(id,c,position);

		VersionVectorPacket v = new VersionVectorPacket(id, c ,'i', crdt.counter);
		vectorVersion.add(v);

		System.out.println(vectorVersion.toString());
		BroadcastPacket packet = new BroadcastPacket(id,c,'i',position);
		broadcast(packet);
		printDocument();
	}

	private static ArrayList<Integer> createPosition(int index) {
		crdt.sort();
		ArrayList<Integer> position = new ArrayList<>();
		if(index == 0){
//			System.out.println("first");
			if(crdt.getDocument().size() == 0){
				position.add(1);
			}else{
				ArrayList<Integer> nextPosition = crdt.getDocument().get(index).getPosition();
				for(int i = 0 ; i < nextPosition.size(); i++){
					position.add(nextPosition.get(i));
				}
				int lastIdx = nextPosition.size() - 1;
				position.set(lastIdx, nextPosition.get(lastIdx) - 1 );
				position.add(999);
			}

		}else if(crdt.getDocument().size() == index){
//			System.out.println("last");
			position.add(index + 1);
		}else{
//			System.out.println("between");
			ArrayList<Integer> prevPosition = crdt.getDocument().get(index - 1).getPosition();
			ArrayList<Integer> nextPosition = crdt.getDocument().get(index).getPosition();

			if(prevPosition.size() == nextPosition.size()){
				for(int i = 0 ; i < prevPosition.size(); i++){
					position.add(prevPosition.get(i));
				}
				position.add(1);
			}else if(prevPosition.size() < nextPosition.size()){
				for(int i = 0 ; i < nextPosition.size(); i++){
					position.add(nextPosition.get(i));
				}
				int lastIdx = nextPosition.size() - 1;
				position.set(lastIdx, nextPosition.get(lastIdx) - 1 );
				position.add(999);
			}
			else{
				for(int i = 0 ; i < prevPosition.size(); i++){
					position.add(prevPosition.get(i));
				}
				int lastIdx = prevPosition.size() - 1;
				position.set(lastIdx, prevPosition.get(lastIdx) + 1 );
			}

		}
		return  position;
	}

	public static void insertRemote(BroadcastPacket packet){
		System.out.println(packet);
		crdt.insert(packet.getId(),packet.getValue(),packet.getPosition());

		VersionVectorPacket v = new VersionVectorPacket(id, packet.getValue(), 'i', crdt.counter);
		vectorVersion.add(v);

		updateTextEditor();
		printDocument();
	}
	public static void deleteLocal(int idx) throws IOException{
		Character c = crdt.get(idx);
		DeletionBufferPacket d = new DeletionBufferPacket(id, c.getValue(), crdt.counter, c.getPosition());
		delBuffer.add(d);

//		crdt.delete(idx);
//		BroadcastPacket packet = new BroadcastPacket(id,c.getValue(),'d',c.getPosition());
//		System.out.println(packet);
//		broadcast(packet);
//		System.out.println(packet);
//		printDocument();
	}

	public static void deleteRemote(BroadcastPacket packet){
		System.out.println(packet);
		DeletionBufferPacket d = new DeletionBufferPacket(id, packet.getValue(), crdt.counter, packet.getPosition());
		delBuffer.add(d);
//		int idx = crdt.find(packet.getPosition());
//		System.out.println(idx);
//		crdt.delete(idx);
//		updateTextEditor();
//		printDocument();
	}

	public void deleteCRDT(){

	}

	public static void printDocument(){
//		crdt.print();
//		System.out.println();
	}

	public static void updateTextEditor(){
		crdt.sort();
		textEditor.update(crdt.getDocument());
	}
}
