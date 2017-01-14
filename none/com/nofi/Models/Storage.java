package com.nofi.Models;

import com.nofi.Controllers.LoadSave;

import java.io.Serializable;
import java.util.HashMap;

/**
        * Neil Byrne - 2015
        * Storage uses the Singleton design pattern and acts as a static state machine and data store while the app is running.
        * It contains an instance of the LoadSave class and methods for accessing its functions, whichit will run on a separate thread.
        * Storage is Serializable and contains Hashmap data structures of
        * ImageContent, NoteContent, and StoryContent which are also Serializable.
        */
public class Storage implements Serializable {

    private String uid = "1";

    private static final long serialVersionUID = -7604766932017737115L;

    public static String storyID = "";

    private Storage(){}

    private HashMap<String, Note> notes;
    private HashMap<String, Story> stories;

    private Note tempNote = new Note();
    private Story tempStory = new Story();

    private LoadSave loadSave = new LoadSave();

    public void loadData(){
        Thread t = new Thread(new Runnable() {
            public void run() {
                loadSave.loadData();
            }
        });
        t.start();
    }
    public void saveData(){

        Thread t = new Thread(new Runnable() {
            public void run() {
                loadSave.saveData();
            }
        });
        t.start();
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public HashMap<String, Note> getNotes() {
        if (notes == null){
            notes = new HashMap<String, Note>();
        }
        return notes;
    }

    public HashMap<String, Story> getStories() {
        if (stories == null){
            stories = new HashMap<String, Story>();
        }
        return stories;
    }


    public void setNotes(HashMap<String, Note> notes) {
        this.notes = notes;
    }
    public void setStories(HashMap<String, Story> stories) {
        this.stories = stories;
    }

    public boolean isTemp() {
        return temp;
    }

    public void setTemp(boolean temp) {
        this.temp = temp;
    }

    private boolean temp = false;

    public Note getTempNote() {
        return tempNote;
    }

    public void setTempNote(Note tempNote) {
        this.tempNote = tempNote;
    }

    public Story getTempStory() {
        return tempStory;
    }

    public void setTempStory(Story tempStory) {
        this.tempStory = tempStory;
    }



    private static class SingletonHelper{
        private static final Storage instance = new Storage();
    }

    public static Storage getInstance(){
        return SingletonHelper.instance;
    }

    protected Object readResolve() {
        return getInstance();
    }


}