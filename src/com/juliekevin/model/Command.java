package com.juliekevin.model;

public class Command {

    private final String verb = null;
    private final String noun = null;

    public Command(String verb, String noun) {
        this.verb = verb;
        this.noun = noun;
    }

    
    private Command(){
        //  Immutable
    }
}
