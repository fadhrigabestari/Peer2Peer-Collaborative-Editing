package com.company;

import java.util.HashMap;

public class VersionVectorPacket {
    private HashMap<String, Integer> version;

    public VersionVectorPacket(){
        this.version = new HashMap<>();
    }

    public HashMap<String, Integer> getVersion() {
        return version;
    }

    public void setVersion(HashMap<String, Integer> version) {
        this.version = version;
    }

    public void add(String id, int counter){
        version.put(id, counter);
    }

    public int getCounter(String id){
        return this.version.get(id);
    }

    public void increment(String id){
        this.version.put(id, this.version.get(id) + 1);
    }

}