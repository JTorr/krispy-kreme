package com.juliekevin;

import com.juliekevin.model.Command;

public class Parser {

    public Command parse(String input) throws Exception {
    	String [] words = input.trim().split(" ");
    	
    	if(words.length == 1) {
    		return new Command(words[0].trim());
    	} else if(words.length == 2){
        	String verb = words[0].trim();
    	    String noun = words[1].trim();
    	    return new Command(verb, noun);
    	} else {
    		return parseWithQty(words[0], words[1], words[2]);
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
    		throw new Exception("Input should match format: verb + noun + QTY");
    	}
    }

}
