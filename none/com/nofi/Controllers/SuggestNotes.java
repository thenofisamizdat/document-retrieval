package com.nofi.Controllers;

import com.nofi.Models.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Neil on 26/11/2015.
 */
public class SuggestNotes {

    public SuggestNotes(){}

    private HashMap<Note, Integer> newSuggestions;


    public HashMap<Note, Integer> getNewSuggestions(Story story){

        newSuggestions = new HashMap<Note, Integer>();
        // here is the algorithm to weigh notes based on hash tag relevance to the story
        @SuppressWarnings("unchecked")
        Iterator<Note> it = (Iterator<Note>) Storage.getInstance().getNotes().values().iterator();
        // for each note element
        while (it.hasNext()){
            int contentWeight = 0;
            Note note = it.next(); // note is note element being examined

            Iterator<String> it2 = note.getHashTags().iterator(); // iterating through list of these tags for feed element
            while(it2.hasNext()){
                String tag = it2.next(); // examining tag

                Iterator<HashTagObject> it3 = story.getProfile().iterator(); // looking at story's list of hashtags
                int userHashIndex = 0;
                while(it3.hasNext()){ // tag by tag
                    HashTagObject tagObject = it3.next(); // viewing tag object
                    if (tagObject.getName().compareToIgnoreCase(tag) == 0){ // if tagname from user hashtag object = tag from feed element
                        contentWeight += tagObject.getWeight(); // then we increase content weight by the amount
                    }
                    userHashIndex ++;
                }
            }
            newSuggestions.put(note, contentWeight); //add the noet to new suggestions with its total weight
        }
        Map<Note, Integer> sortedMap = sortByComparator(newSuggestions);

        return (HashMap<Note, Integer>) sortedMap;
    }

    private Map<Note, Integer> sortByComparator(HashMap<Note, Integer> newExploreOrder2){
        // Convert Map to List
        List<Map.Entry<Note, Integer>> list =
                new LinkedList<Map.Entry<Note, Integer>>(newExploreOrder2.entrySet());

        // Sort list with comparator, to compare the Map values
        Collections.sort(list, new Comparator<Map.Entry<Note, Integer>>() {
            public int compare(Map.Entry<Note, Integer> o1,
                               Map.Entry<Note, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // Convert sorted map back to a Map
        Map<Note, Integer> sortedMap = new LinkedHashMap<Note, Integer>();
        for (Iterator<Map.Entry<Note, Integer>> it = list.iterator(); it.hasNext();) {
            Map.Entry<Note, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
