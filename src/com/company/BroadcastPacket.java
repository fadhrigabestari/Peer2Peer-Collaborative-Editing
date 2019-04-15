package com.company;

import java.io.Serializable;

public class BroadcastPacket implements Serializable {
	private String id;
	private char value;
	private char operation; // 'i' for insert 'd' for delete
	private float position;

	public BroadcastPacket(String id, char value, char operation, float position) {
		this.id = id;
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

	public float getPosition() {
		return position;
	}

	public void setPosition(float position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "BroadcastPacket{" +
		  "id='" + id + '\'' +
		  ", value=" + value +
		  ", operation=" + operation +
		  ", position=" + position +
		  '}';
	}
}
