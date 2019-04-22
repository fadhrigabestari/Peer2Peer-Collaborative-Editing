package com.company;

import java.util.ArrayList;

public class VersionVector {
    private String id;
    private ArrayList<VersionVectorPacket> vectorVersion;

    public VersionVector(String id, char value, char op, int counter){
        this.id = id;
        vectorVersion = new ArrayList<>();
        VersionVectorPacket v = new VersionVectorPacket(value, op, counter);
        vectorVersion.add(v);
    }

    public VersionVector(String id){
        this.id = id;
        vectorVersion = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "VersionVector{" +
                "id='" + id + '\'' +
                ", vectorVersion=" + vectorVersion +
                '}';
    }

    public void insert(char value, char op, int counter) {
        VersionVectorPacket v = new VersionVectorPacket(value, op, counter);
        vectorVersion.add(v);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVectorVersion (ArrayList<VersionVectorPacket> v){
        this.vectorVersion = v;
    }
}
