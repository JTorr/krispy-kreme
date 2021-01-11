package com.juliekevin.model;

public enum Verb {
	GO("go", "move"), BUY("buy", "money"), SELL("sell", "money"), INVENTORY("inventory", "single"), QUIT("quit", "single"), HELP("help", "single"), VIEW("view", "view"), GET("get", "money"), PAY("pay", "money");
	
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
