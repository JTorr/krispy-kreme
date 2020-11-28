package com.juliekevin.model;

public class Command {

    private String verb = null;
    private String noun = null;

    public Command(String verb, String noun) {
        this.verb = verb;
        this.noun = noun;
    }


    
    private Command(){
        //  Immutable
    }
}
