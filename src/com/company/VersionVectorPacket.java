package com.company;

public class VersionVectorPacket {
    private String id;
    private char value;
    private char op;
    private int counter;

    public VersionVectorPacket(String id, char value, char op, int counter){
        this.id = id;
        this.value = value;
        this.op = op;
        this.counter = counter;
    }

    public char getValue(){
        return this.value;
    }

    public char getOp(){
        return this.op;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCounter(){
        return this.counter;
    }

    public void setValue(char value){
        this.value= value;
    }

    public void setOp(char op){
        this.op = op;
    }

    public void setCounter(int counter){
        this.counter = counter;
    }
}