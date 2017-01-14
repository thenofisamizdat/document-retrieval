package com.nofi.Controllers.ViewCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by neil on 03/04/16.
 */
public class QueryParsingLogic {

    public  QueryParsingLogic(){}

    public ArrayList<String> createSearchQuery(String query){
        ArrayList<String> tokens = new ArrayList<>();
        ArrayList<String> inputParsed = extractPatterns(tokens, query);

        return buildQuery(tokens, inputParsed);

    }
    private ArrayList<String> buildQuery(ArrayList<String> stringLiterals, ArrayList<String> parsedQuery){

        ArrayList<String> cleanQuery = new ArrayList<>();
        int swapCount = 0;
        for (int i = 0; i < parsedQuery.size(); i++){
            if (parsedQuery.get(i).equals("********none********")){
                cleanQuery.add(stringLiterals.get(swapCount).replaceAll("\"", ""));
                swapCount++;
            }
            else {
                String token = parsedQuery.get(i).replaceAll(" ", "");
                if (!token.equals("")) cleanQuery.add(token);
            }

        }
        return cleanQuery;
    }
    private ArrayList<String> extractPatterns(ArrayList<String> tokens, String query){
        query = extractStringLiterals(tokens, query);
        return new ArrayList<String>(Arrays.asList(query.split(" ")));
    }

    private String extractStringLiterals(ArrayList<String> tokens, String query){
        String token = "";
        Pattern p = Pattern.compile("([\"'])(?:(?=(\\\\?))\\2.)*?\\1", Pattern.MULTILINE);
        //Pattern p = Pattern.compile("(?:^|\\s)\"([^']*?)\"(?:$|\\s)", Pattern.MULTILINE);
        Matcher m = p.matcher(query);
        if (m.find()) {
            token = m.group();
            tokens.add(token);
            query = query.replaceFirst(token, " ********none******** ");
            while (m.find()){
                token = m.group();
                tokens.add(token);
                query = query.replaceFirst(Pattern.quote(token), " ********none******** ");
            }
        }
        return query;
    }
}
