package com.nofi.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Neil Byrne - 2015
 * Story contains all notes attached to it, a contextual profile
 */
public class Story implements Serializable {

    private String title;
    private String storyID;

    private List<HashTagObject> profile = new ArrayList<HashTagObject>();
    private Map<String, Note> notes = new HashMap<String, Note>();

    private int importanceOfStory;


    public Story(){}

    public Story(String _title, int _importance){
        title = _title;
        importanceOfStory = _importance;
        storyID = createUniqueContentID(_title, Storage.getInstance().getUid());
    }

    private String createUniqueContentID(String _description, String _uid){
        Long unixTime = System.currentTimeMillis() / 1000L;
        String id = unixTime.toString() + _description + _uid;
        return id;
    }

    public void editNote(Note note){
        // edit might be more complex as we will have to remove old hash tags from the story profile
        // and add them again to ensure accuracy. will improve later
        notes.put(note.getNoteID(), note);
    }

    public void addNote(Note note){
        notes.put(note.getNoteID(), note);
        //after adding the note we look through the note's hashtags
        for (int i = 0; i < note.getHashTags().size(); i++){
            int profileIndex = getHashTagIndex(note.getHashTags().get(i)); // for each tag we see if it exists in the story profile
            if (profileIndex>=0){ // if it does, we add to the weight of that tag in the profile
                profile.get(profileIndex).changeWeight(1);
            }
            else{ // if not we add it with a weight of 1
                profile.add(new HashTagObject(1, note.getHashTags().get(i)));
            }
        }
    }
    public int getHashTagIndex(String tag){ // looks through profile of hash tag objects and gives back index of search string
        for (int i = 0; i < this.profile.size(); i++){
            if (tag.compareToIgnoreCase(this.profile.get(i).getName()) == 0){
                return i;
            }
        }
        return -1;
    }
    public List<HashTagObject> getProfile() {
        return profile;
    }

    public void setProfile(List<HashTagObject> profile) {
        this.profile = profile;
    }

    public Map<String, Note> getNotes() {
        return notes;
    }

    public void setNotes(Map<String, Note> notes) {
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStoryID() {
        return storyID;
    }

    public void setStoryID(String storyID) {
        this.storyID = storyID;
    }

    public int getImportanceOfStory() {
        return importanceOfStory;
    }

    public void setImportanceOfStory(int importanceOfStory) {
        this.importanceOfStory = importanceOfStory;
    }

}
