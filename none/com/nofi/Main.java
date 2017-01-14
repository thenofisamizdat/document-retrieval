package com.nofi;

import com.nofi.Models.Note;
import com.nofi.Models.Storage;
import com.nofi.Models.Story;
import com.nofi.Views.GetInputs;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Note n1 = new Note("Following the Global Company Meeting yesterday originating in London, Tim Armstrong and other leaders headed to Dublin to visit with AOLers there before heading back to the U.S.  ",
                "yahoo data aws vr aol microsoft aol");
        Note n2 = new Note("The switch statement compares the String object in its expression with the expressions associated with each case label as if it were using the String.equals method; consequently, the comparison of String objects in switch statements is case sensitive. The Java compiler generates generally more efficient bytecode from switch statements that use String objects than from chained if-then-else statements. ",
                "aol java microsoft jdk switch");

        Story test = new Story("test", 1);
        test.addNote(n1);
        test.addNote(n2);

        Storage.getInstance().getNotes().put(n1.getNoteID(), n1);
        Storage.getInstance().getNotes().put(n2.getNoteID(), n2);

        Storage.getInstance().getStories().put(test.getTitle(), test);

        GetInputs inp = new GetInputs();
    }
}
