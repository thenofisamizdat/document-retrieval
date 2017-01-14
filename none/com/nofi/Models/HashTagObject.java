package com.nofi.Models;

/**
 * Created by Neil on 26/11/2015.
 */
public class HashTagObject{
    private int weight;
    private String tag;

    public HashTagObject(int _weight, String _tag){
        weight = _weight;
        tag = _tag;
    }

    public int getWeight(){
        return weight;
    }
    public String getName(){
        return tag;
    }

    public void changeWeight(int change){
        this.weight += change;
    }
}
