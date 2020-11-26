package com.juliekevin;

import java.util.ArrayList;

public class Character {
	String name;
	String location;
	double money = 1000.00;
	Stash stash = new Stash();
	
	public Character(String name, String location) {
		this.name = name;
		this.location = location;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public void getInventory() {
	    System.out.println("Your inventory currently contains: ");
	    System.out.println(stash.toString());
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
	
}
