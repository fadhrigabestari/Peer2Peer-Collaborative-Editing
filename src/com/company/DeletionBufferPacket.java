package com.company;

import java.io.*;
import java.util.*;

public class DeletionBufferPacket {
    private String id;
    private int counter;
    private Character character;

    public DeletionBufferPacket(String id, int counter, Character character) {
        this.id = id;
        this.counter = counter;
        this.character = character;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(char counter) {
        this.counter = counter;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
