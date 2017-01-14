package com.nofi.Controllers.ViewCommand;

import com.nofi.Models.Note;
import com.nofi.Models.Storage;
import com.nofi.Models.Story;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nbyrne21 on 01/04/2016.
 */
public class ViewNoteFilter {

    public ViewNoteFilter(){}

    public ArrayList<Note> getNotesContainingQuery(String query, final String TAGorCONTENT){
        NoteFilterLogic filterLogic = new NoteFilterLogic();
        QueryParsingLogic parser = new QueryParsingLogic();
        ArrayList<String> searchTerms = parser.createSearchQuery(query);
        HashMap<String, Note> notes = Storage.getInstance().getNotes();
        ArrayList<Note> returnSetNotes = new ArrayList<>();

        for (Note note : notes.values()) {
            ArrayList<String> searchTermsParsed = searchTerms;
            if (searchTermsParsed.contains("!|")) searchTermsParsed = filterLogic.checkQueryNOTThere(searchTermsParsed, note, TAGorCONTENT);
            if ((searchTermsParsed!=null)&&(searchTermsParsed.contains("||"))) searchTermsParsed = filterLogic.checkQueryORThere(searchTermsParsed, note, TAGorCONTENT);

            if ((searchTermsParsed!=null)&&(!searchTermsParsed.contains("!|")) && (!searchTermsParsed.contains("||"))){
                if (filterLogic.checkQueryAgainstNote(searchTermsParsed, note, TAGorCONTENT)) returnSetNotes.add(note);
            }
        }
        return returnSetNotes;
    }

    public ArrayList<Note> getNotesInStory(String query){
        QueryParsingLogic parser = new QueryParsingLogic();
        ArrayList<String> searchTerms = parser.createSearchQuery(query);
        query = searchTerms.get(0);
        Story story = Storage.getInstance().getStories().get(query);
        ArrayList<Note> returnSetNotes = new ArrayList<>();

        if (story != null) {
            HashMap<String, Note> notes = (HashMap<String, Note>) story.getNotes();
            for (Note note : notes.values()) {
                returnSetNotes.add(note);
            }
        }
        return returnSetNotes;
    }

    public ArrayList<Note> getAllNotes(){
        HashMap<String, Note> notes = Storage.getInstance().getNotes();
        ArrayList<Note> returnSetNotes = new ArrayList<>();

        for (Note note : notes.values()) {
            returnSetNotes.add(note);
        }
        return returnSetNotes;
    }

    public ArrayList<Note> getNoteByID(String query){
        HashMap<String, Note> notes = Storage.getInstance().getNotes();
        ArrayList<Note> returnSetNotes = new ArrayList<>();

        returnSetNotes.add(notes.get(query));

        return returnSetNotes;
    }


}
