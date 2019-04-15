package com.company;

import java.util.ArrayList;

public class Character {
	private String siteId;
	private char value;
	private int counter;
	private ArrayList<Integer> position;

	public Character(String siteId, char value, int counter, ArrayList<Integer> position) {
		this.siteId = siteId;
		this.value = value;
		this.counter = counter;
		this.position = position;
	}

	public Character(String siteId, char value, ArrayList<Integer> position) {
		this.siteId = siteId;
		this.value = value;
		this.position = position;
	}

	public char getValue() {
		return value;
	}

	public ArrayList<Integer> getPosition() {
		return position;
	}

	public void setPosition(ArrayList<Integer> position) {
		this.position = position;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public int compareTo(Character c){
		return Float.compare(convertPositionToFloat(this.position), convertPositionToFloat(c.position));
	}
	public float convertPositionToFloat(ArrayList<Integer> position){
		float pos = 0;
		int divide = 1;
		for(int i = 0;i < position.size(); i++){
			pos += (position.get(i) / divide);
			divide *= 10;
		}
		return pos;
	}
}
