package com.juliekevin.model;

public enum Verb {
	GO("go"), BUY("buy"), SELL("sell"), VIEW("view"), INVENTORY("inventory"), QUIT("quit"), HELP("help");
	
	private String name;
	
	Verb(String name) {
		this.name = name;
	}
	
	@Override
	public
	String toString() {
		return name;
	}
}
