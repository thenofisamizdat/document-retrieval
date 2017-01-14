package com.nofi.Controllers.ViewCommand;

import com.nofi.Models.Note;
import com.nofi.Models.Storage;

import java.util.ArrayList;

/**
 * Created by nbyrne21 on 01/04/2016.
 */
public class ViewNote {

    public ViewNote(){}

    private ArrayList<Note> filteredNotes;
    private ViewNoteFilter viewNoteFilter;

    String filter, query;

    public ArrayList<Note> getFilter(ArrayList<String> command){
        if (command.size() > 2) filter = command.get(2);
        if (command.size() > 3) query = command.get(3);

        switch (filter){
            case "-t":
                viewNoteFilter = new ViewNoteFilter();
                filteredNotes = viewNoteFilter.getNotesContainingQuery(query, "TAG");
                break;
            case "-c":
                viewNoteFilter = new ViewNoteFilter();
                filteredNotes = viewNoteFilter.getNotesContainingQuery(query, "CONTENT");
                break;
            case "-s":
                viewNoteFilter = new ViewNoteFilter();
                filteredNotes = viewNoteFilter.getNotesInStory(query);
                break;
            case "-a":
                viewNoteFilter = new ViewNoteFilter();
                filteredNotes = viewNoteFilter.getAllNotes();
                break;
            default:
                filteredNotes = viewNoteFilter.getNoteByID(query);
        }
        return filteredNotes;
    }
}
