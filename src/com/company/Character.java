package com.company;


import java.util.ArrayList;

public class Character {
	private String siteId;
	private char value;
	public int counter;
	private ArrayList<Integer> position;

	public Character(String siteId, char value, int counter, ArrayList<Integer> position) {
		this.siteId = siteId;
		this.value = value;
		this.counter = counter;
		this.position = position;
	}

	public Character(String siteId, char value, ArrayList<Integer> position){
		this.siteId = siteId;
		this.value = value;
		this.position = position;
	}

	public String getSiteId() { return siteId; }

	public void setSiteId(String siteId) { this.siteId = siteId; }

	public char getValue() {
		return value;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
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
		int sizePosition = this.getPosition().size();
		int sizeOtherPosition = c.getPosition().size();
		ArrayList<Integer> position = this.getPosition();
		ArrayList<Integer> otherPosition = c.getPosition();
		if(sizePosition < sizeOtherPosition){
//			System.out.println("other");
			for(int i = 0 ; i < sizePosition;i++){
				if(!position.get(i).equals(otherPosition.get(i))){
					return Integer.compare(position.get(i),otherPosition.get(i));
				}
			}
			return -1;
		}else if(sizePosition > sizeOtherPosition){
//			System.out.println("self");
			for(int i = 0 ; i < sizeOtherPosition;i++){
				if(!position.get(i).equals(otherPosition.get(i)) ){
					return Integer.compare(position.get(i),otherPosition.get(i));
				}
			}
			return 1;
		}else{
//			System.out.println("equal");
			for(int i = 0 ; i < sizeOtherPosition;i++){
				if(!position.get(i).equals(otherPosition.get(i))){

					return Integer.compare(position.get(i),otherPosition.get(i));
				}
			}
			return 0;
		}
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

	public static void main(String[] args) {
		ArrayList<Integer> t1 = new ArrayList<>();
		ArrayList<Integer> t2 = new ArrayList<>();
		t1.add(0);
		t1.add(999);
		t1.add(1);
		Character c = new Character("a",'c',0,t1);
		t2.add(0);
		t2.add(999);
		Character c2 = new Character("a",'c',0,t2);
		System.out.println(t1.size() + " " + t2.size());
		System.out.println(c.compareTo(c2));
	}
}
