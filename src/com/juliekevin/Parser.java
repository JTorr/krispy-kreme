package com.juliekevin;

import com.juliekevin.model.Command;
import com.juliekevin.model.Verb;

public class Parser {

    public Command parse(String input) throws Exception {
    	String [] words = input.trim().split(" ");
    	Verb parsedVerb;
    	try {
    		parsedVerb = parseVerb(words[0].trim());
    	} catch(Exception e) {
    		throw new Exception(e.getMessage());
    	}

    	
    	
    	if(parsedVerb.getType() == "single") {
    		return new Command(parsedVerb.toString());
    	} else if(parsedVerb.getType() == "move" && words.length >= 2){
    		String noun = words[1].trim();
    	    return new Command(parsedVerb.toString(), noun);
    	} else if(parsedVerb.getType() == "move" && words.length < 2) {
    		throw new Exception("Move commands must include location.");
    	}else if(parsedVerb.getType() == "sale" && words.length == 3){
    		return parseWithQty(words[0], words[1], words[2]);
    	} else if(parsedVerb.getType() == "sale" && words.length < 3) {
    		throw new Exception("Buy/sell commands must include item and quantity.");
    	} else {
    		throw new Exception("Command type could not be identified.");
    	}
    }
    
    private Verb parseVerb(String word) throws Exception {
    	try {
    		return Verb.valueOf(word.toUpperCase());
    	} catch(Exception e) {
    		throw new Exception("Invalid command. Verb not recognized.");
    	}
    }
    
    
    private Boolean isNumeric(String word) throws Exception {
    	try {
    		Integer.parseInt(word);
    	} catch(NumberFormatException nfe) {
    		return false;
    	}
    	return true;
    }
    
    private Command parseWithQty(String w1, String w2, String w3) throws Exception {
    	try {
    		if(isNumeric(w2)) {
        		return new Command(w1, w3, Integer.parseInt(w2));
        	} else if(isNumeric(w3)) {
        		return new Command(w1, w2, Integer.parseInt(w3));
        	} else {
        		throw new Exception("Input should match format: verb + noun + quantity");
        	}
    	} catch(Exception e) {
    		throw new Exception("Re-enter command with quantity. Input should match format: verb + noun + quantity");

    	}
    	
    }

}
