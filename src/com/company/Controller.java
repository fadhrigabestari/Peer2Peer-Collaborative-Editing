package com.company;

import java.io.IOException;
import java.util.ArrayList;

import static com.company.Message.broadcast;

public class Controller {
	private static CRDT crdt;
	public static String id;
	public Message message;
	private static TextEditor textEditor;
	public Controller(String id) {
		this.crdt = new CRDT();
		this.id = id;
		Thread t=new Thread(new Message());
		t.start();
		textEditor = new TextEditor();
	}
	public static void insertLocal(char c, int index) throws IOException {
		ArrayList<Integer> position = createPosition(index);
		crdt.insert(id,c,position);
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
			for(int i = 0 ; i < prevPosition.size(); i++){
				position.add(prevPosition.get(i));
			}
			if(prevPosition.size() == nextPosition.size()){
				position.add(1);
			}else{
				int lastIdx = prevPosition.size() - 1;
				position.set(lastIdx, prevPosition.get(lastIdx) + 1 );
			}

		}
		return  position;
	}

	public static void insertRemote(BroadcastPacket packet){
		crdt.insert(packet.getId(),packet.getValue(),packet.getPosition());
		updateTextEditor();
		printDocument();
	}
	public static void deleteLocal(int idx) throws IOException{
		Character c = crdt.get(idx);
		crdt.delete(idx);
		BroadcastPacket packet = new BroadcastPacket(id,c.getValue(),'d',c.getPosition());
		broadcast(packet);
		printDocument();
	}

	public static void deleteRemote(BroadcastPacket packet){
		int idx = crdt.find(packet.getPosition());
		crdt.delete(idx);
		updateTextEditor();
		printDocument();
	}
	public static void printDocument(){
		crdt.print();
		System.out.println();
	}
	public static void updateTextEditor(){
		crdt.sort();
		textEditor.update(crdt.getDocument());
	}
}
