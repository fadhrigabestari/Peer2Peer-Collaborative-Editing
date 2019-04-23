package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class BroadcastPacket implements Serializable {
	private String id;
	private String siteId;
	private char value;
	private char operation; // 'i' for insert 'd' for delete
	private ArrayList<Integer> position;

	public BroadcastPacket(String id, String siteId, char value, char operation, ArrayList<Integer>  position) {
		this.id = id;
		this.siteId = siteId;
		this.value = value;
		this.operation = operation;
		this.position = position;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSiteId() { return siteId; }

	public void setSiteId(String siteId) { this.siteId = siteId; }

	public char getValue() {
		return value;
	}

	public void setValue(char value) {
		this.value = value;
	}

	public char getOperation() {
		return operation;
	}

	public void setOperation(char operation) {
		this.operation = operation;
	}

	public ArrayList<Integer>  getPosition() {
		return position;
	}

	public void setPosition(ArrayList<Integer>  position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "BroadcastPacket{" +
		  "id='" + id + '\'' +
		  ", siteId=" + siteId +
		  ", value=" + value +
		  ", operation=" + operation +
		  ", position=" + position +
		  '}';
	}
}
