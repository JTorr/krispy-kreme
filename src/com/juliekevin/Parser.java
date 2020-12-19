package com.juliekevin;

import com.juliekevin.model.Command;

public class Parser {

    public static Command parse(String input) {
    	String [] words = input.trim().split(" ");
    	
    	if(words.length == 1) {
    		return new Command(words[0].trim());
    	} else {
        	String verb = words[0].trim();
    	    String noun = words[1].trim();
    	    return new Command(verb, noun);
    	}       
    }

}
