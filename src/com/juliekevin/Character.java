package com.juliekevin;

import java.math.BigDecimal;

public class Character {
	String name;
	String location;
	BigDecimal money = new BigDecimal("1000.00");
	Stash stash;
	
	public Character(String name, String location) {
		this.name = name;
		this.location = location;
		this.stash = new Stash();
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public void getInventory() {
	    System.out.println("Your inventory currently contains: ");
	    System.out.println(this.getStash().toString());
	    System.out.println("You have $" + this.money.toString() + ".");
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public BigDecimal getMoney() {
		return this.money;
	}
	
	public void setMoney(BigDecimal amount) {
		this.money = amount;
	}

	public Stash getStash() {
		return stash;
	}
	
}
