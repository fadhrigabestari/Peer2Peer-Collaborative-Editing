package com.company;

public class VersionVectorPacket {
    private char value;
    private char op;
    private int counter;

    public VersionVectorPacket(char value, char op, int counter){
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