package com.company;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Math.round;

public class CRDT {


	private ArrayList<Character> document;
	private static int counter = 0;

	public CRDT() {
		this.document = new ArrayList<Character>();
	}

	public ArrayList<Character> getDocument() {
		return document;
	}

	public void setDocument(ArrayList<Character> document) {
		this.document = document;
	}
	public void insert(String id, char value, ArrayList<Integer> position){
		counter++;
		document.add(new Character(id,value,position));
	}
	public void delete( int idx){
		//Character c = new Character(id,value,position);
		//document.remove(c);
//		ArrayList<Integer> pos = convertPositionToList(position);
//		int i;
//		for(i = 0; i < document.size(); i++){
//			if(this.document.get(i).getPosition().equals(pos)){
//				break;
//			}
//		}
		document.remove(idx);
	}
	public void update(){}

	public void print(){
		sort();
		for(int i = 0; i < document.size(); i++){
			System.out.println(document.get(i).getValue());
			System.out.println(document.get(i).getPosition());
		}
	}
	public ArrayList<Integer> convertPositionToList(Float pos){
		ArrayList<Integer> position= new ArrayList<Integer>();
		if(pos == Math.round(pos)){
			position.add(Math.round(pos));
		}else{
			position.add((int)Math.floor(pos));
			position.add(Math.round(pos%1) * 10);
		}

		return position;
	}
	public void sort(){
		Collections.sort(this.document, new Comparator<Character>() {
			@Override
			public int compare(Character c1, Character c2) {
				return c1.compareTo(c2);
			}
		});
	}
	public Character get(int index){
		return document.get(index);
	}
	public int find(ArrayList<Integer> pos){
		int i;
		for(i = 0; i < document.size(); i++){
			if(this.document.get(i).getPosition().equals(pos)){
				break;
			}
		}
		System.out.println(i);
		return  i;
	}

}
