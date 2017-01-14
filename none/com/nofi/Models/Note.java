package com.nofi.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * NoteContent contains all data for each note content item that is created.
 */
public class Note implements Serializable {

    private String note, noteID;

    private String tags;

    public ArrayList<String> getHashTags() {
        return hashTags;
    }

    public void setHashTags(ArrayList<String> hashTags) {
        this.hashTags = hashTags;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    private ArrayList<String> hashTags;

    public Note(){}

    public Note(String _note, String _hashTags){
        note = _note;
        noteID = createUniqueContentID(_note, Storage.getInstance().getUid());
        tags = _hashTags;
        hashTags = new ArrayList<String>( Arrays.asList(_hashTags.toLowerCase().split(" ")));
    }

    private String createUniqueContentID(String _description, String _uid){
        Long unixTime = System.currentTimeMillis() / 1000L;
        String id = unixTime.toString() + _description + _uid;
        return id;
    }

    public boolean containsTag(String query){
        if (hashTags.contains(query)) return true;
        return false;
    }

    public boolean containsString(String TAGorCONTENT, String query){
        if (TAGorCONTENT == "TAG"){
            if (hashTags.contains(query)) return true;
            return false;
        }
        if (TAGorCONTENT == "CONTENT"){
            if (note.toLowerCase().indexOf(query.toLowerCase())>=0) return true;
            return false;
        }
        return false;
    }

    public String getNoteID() {
        return noteID;
    }

    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String toString(){
        return "\nID: " + getNoteID() + "\nNote: " + getNote() + "\nTags: " + getTags() + "\n\n";
    }

}

