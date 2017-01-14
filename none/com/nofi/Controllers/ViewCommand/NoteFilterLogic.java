package com.nofi.Controllers.ViewCommand;

import com.nofi.Models.Note;

import java.util.ArrayList;

/**
 * Created by neil on 03/04/16.
 */
public class NoteFilterLogic {

    public NoteFilterLogic(){}

    public boolean checkQueryAgainstNote(ArrayList<String> searchTerms, Note note, String TAGorCONTENT){
        boolean noteContainsQuery = true;
        for (String seachQuery: searchTerms) {
            if (!note.containsString(TAGorCONTENT, seachQuery)) {
                noteContainsQuery = false;
                break;
            }
        }
        return noteContainsQuery;
    }
    public ArrayList<String> checkQueryNOTThere(ArrayList<String> searchTerms, Note note, String TAGorCONTENT){
        ArrayList<String> searchTermsWithoutNot = new ArrayList<>();
        for (int i = 0; i < searchTerms.size(); i++) {
            if (searchTerms.get(i).equals("!|")){
                if (i <= searchTerms.size()-2){
                    i++;
                    if (note.containsString(TAGorCONTENT, searchTerms.get(i))) return null;
                }
            }
            else searchTermsWithoutNot.add(searchTerms.get(i));
        }
        return searchTermsWithoutNot;
    }
    public ArrayList<String> checkQueryORThere(ArrayList<String> searchTerms, Note note, String TAGorCONTENT){
        ArrayList<String> searchTermsWithoutOr = new ArrayList<>();
        for (int i = 0; i < searchTerms.size(); i++) {
            if (searchTerms.get(i).equals("||")){
                boolean orExists = false;
                if (i > 0){
                    if (!note.containsString(TAGorCONTENT, searchTerms.get(i-1))) searchTermsWithoutOr.remove(searchTerms.get(i-1));
                    else orExists = true;
                }
                if (i <= searchTerms.size()-2){
                    i++;
                    if (!orExists) if (note.containsString(TAGorCONTENT, searchTerms.get(i))) searchTermsWithoutOr.add(searchTerms.get(i));
                    else return null;
                }
            }
            else searchTermsWithoutOr.add(searchTerms.get(i));
        }
        return searchTermsWithoutOr;
    }
}
