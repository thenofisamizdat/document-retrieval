package com.nofi.Controllers;

import com.nofi.Models.*;
import java.io.*;
import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * Neil Byrne - 2015
 * This class handles data persistence on the user's device.
 * It loads and saves the app#s serialized data structures
 */
public class LoadSave implements Serializable, Callable<String> {

    private static final long serialVersionUID = -3625400529225626533L;

    public LoadSave(){}

    public void loadData(){
        loadNotes();
        loadStories();
    }
    private Storage loadNotes() {
        HashMap<String, Note> notes = null;
        //deserailize from file to object
        try {
            File file = new File("notes.ser");
            ObjectInput in = new ObjectInputStream(new FileInputStream(file));
            try {
                notes = (HashMap<String, Note>) in.readObject();
                Storage.getInstance().setNotes(notes);
            }
            catch ( FileNotFoundException fnf) {
                fnf.printStackTrace();
            } catch (ClassNotFoundException e) {

                e.printStackTrace();
            }
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Storage loadStories() {
        HashMap<String, Story> stories = null;
        //deserailize from file to object
        try {
            File file = new File("stories.ser");
            ObjectInput in = new ObjectInputStream(new FileInputStream(file));
            try {
                stories = (HashMap<String, Story>) in.readObject();
                Storage.getInstance().setStories(stories);
            }
            catch ( FileNotFoundException fnf) {
                fnf.printStackTrace();
            } catch (ClassNotFoundException e) {

                e.printStackTrace();
            }
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void saveData(){
        saveNotes();
        saveStories();
    }
    private String saveStories() {
        Storage stories = Storage.getInstance();
        try {
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(
                    "stories.ser"));
            out.writeObject(stories.getStories());
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }
    private String saveNotes() {
        Storage notes = Storage.getInstance();
        try {
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(
                    "notes.ser"));
            out.writeObject(notes.getStories());
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }


    public String call() throws Exception {
        this.saveData();
        this.loadData();
        return "AutoSave Complete.";
    }
}