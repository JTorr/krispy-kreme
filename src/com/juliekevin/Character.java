package com.juliekevin;

public class Character {
	String name;
	String location;
	double money = 1000.00;
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
	    System.out.println("You have $" + this.money + ".");
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public double getMoney() {
		return this.money;
	}
	
	public void setMoney(double amount) {
		this.money = amount;
	}

	public Stash getStash() {
		return stash;
	}
	
}
