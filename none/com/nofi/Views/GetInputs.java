package com.nofi.Views;

import com.nofi.Controllers.ViewCommand.ViewNote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GetInputs {

	private ArrayList<String> commandTokens;

	private ViewNote viewNote;


	private final String VIEW = "view";
	private final String ADD = "add";
	private final String NOTE = "note";
	private final String STORY = "story";
	private final String ADDNEW = "new";
	
	Scanner in = new Scanner(System.in);



	public GetInputs(){
		welcomeMenu();
	}

	private void welcomeMenu(){
		System.out.println("***___***************___***");
		System.out.println("***___welcome to none___***");
		System.out.println("***___***************___***");
		System.out.println(" ");
		System.out.println(" -- for help type -h");

		parseCommand();
	}

	private void parseCommand(){
		in = new Scanner(System.in);
		String command = in.nextLine();

		String query = getQuery(command);
		String keyWords = parseKeyWords(command);

		commandTokens = new ArrayList<String>(Arrays.asList(keyWords.split(" ")));

		//query = query.replace("->", "");
        query = query.replaceFirst("->", "");

		commandTokens.add(query);

		if (commandTokens.get(0).equals(VIEW)){
			viewCommand();
		}
		else if (commandTokens.get(0).equals(ADD)){

		}
		else if (commandTokens.get(0).equals("-h")){

		}
		else {}

//		for(int i = 0; i<commandTokens.size(); i++){
//			System.out.println(i + " => " + commandTokens.get(i));
//		}
		parseCommand();
	}

	private String getQuery(String command){
		if (command.contains("->")) return command.substring(command.indexOf("->"), command.length());
        return "";
	}

	private String parseKeyWords(String command){
        if (command.contains("->")) return command.substring(0, command.indexOf("->"));
        return command;
	}

	private void viewCommand(){
		if (commandTokens.get(1).equals(NOTE)){
			viewNote = new ViewNote();
			System.out.print(viewNote.getFilter(commandTokens));
		}
		else if (commandTokens.get(1).equals(STORY)){

		}
		else{}
	}

}
