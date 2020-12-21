package com.juliekevin.model;

import java.util.Objects;

public class Command {

    private String verb = null;
    private String noun = null;
    private int qty = 0;

    public Command(String verb, String noun) {
        this.verb = verb;
        this.noun = noun;
    }
    
    public Command(String verb) {
    	this.verb = verb;
    }
    
    public Command(String verb, String noun, int qty) {
    	this.verb = verb;
    	this.noun = noun;
    	this.qty = qty;
    }

    public String getVerb() {
        return verb;
    }

    public String getNoun() {
        return noun;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(verb, command.verb) &&
                Objects.equals(noun, command.noun);
    }

	public int getQty() {
		return qty;
	}
    
    @Override
    public int hashCode() {

        return Objects.hash(verb, noun);
    }

    private Command(){
        //  Immutable
    }

}
