package com.company;

import java.io.*;
import java.util.*;

public class DeletionBufferPacket {
    private String id;
    private char value;
    private int counter;
    private ArrayList<Integer> position;

    public DeletionBufferPacket(String id, char value, int counter, ArrayList<Integer> position) {
        this.id = id;
        this.value = value;
        this.counter = counter;
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

    public int getCounter() {
        return counter;
    }

    public void setCounter(char counter) {
        this.counter = counter;
    }

    public ArrayList<Integer> getPosition() {
        return position;
    }

    public void setPosition(ArrayList<Integer> position) {
        this.position = position;
    }
}
