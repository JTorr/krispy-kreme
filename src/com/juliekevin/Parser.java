package com.juliekevin;

import com.juliekevin.model.Command;
import com.juliekevin.model.Verb;

public class Parser {

    public Command parse(String input) throws Exception {
    	String [] words = input.trim().split(" ");
    	String parsedVerb;
    	try {
    		parsedVerb = parseVerb(words[0].trim());
    	} catch(Exception e) {
    		throw new Exception(e.getMessage());
    	}

    	
    	
    	if(words.length == 1) {
    		return new Command(parsedVerb);
    	} else if(words.length == 2){
    		String noun = words[1].trim();
    	    return new Command(parsedVerb, noun);
    	} else {
    		return parseWithQty(words[0], words[1], words[2]);
    	}
    }
    
    private String parseVerb(String word) throws Exception {
    	try {
    		return Verb.valueOf(word.toUpperCase()).toString();
    	} catch(Exception e) {
    		throw new Exception("Invalid command. Verb not recognized.");
    	}
    }
    
    
    private Boolean isNumeric(String word) {
    	try {
    		Integer.parseInt(word);
    	} catch(NumberFormatException nfe) {
    		return false;
    	}
    	return true;
    }
    
    private Command parseWithQty(String w1, String w2, String w3) throws Exception {
    	if(isNumeric(w2)) {
    		return new Command(w1, w3, Integer.parseInt(w2));
    	} else if(isNumeric(w3)) {
    		return new Command(w1, w2, Integer.parseInt(w3));
    	} else {
    		throw new Exception("Input should match format: verb + noun + quantity");
    	}
    }

}
