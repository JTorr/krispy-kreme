package com.juliekevin.model;

public enum Verb {
	GO("go", "move"), BUY("buy", "sale"), SELL("sell", "sale"), INVENTORY("inventory", "single"), QUIT("quit", "single"), HELP("help", "single");
	
	private String name;
	private String type;
	
	Verb(String name, String commandType) {
		this.name = name;
		this.type = commandType;
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public
	String toString() {
		return name;
	}
}
