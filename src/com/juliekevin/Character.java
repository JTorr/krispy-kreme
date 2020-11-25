package com.juliekevin;

import java.util.ArrayList;

public class Character {
	String name;
	String location;
	ArrayList<String> inventory = new ArrayList<String>();
	
	public Character(String name, String location) {
		this.name = name;
		this.location = location;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public void getInventory() {
		if(inventory.size() > 0) {
	        System.out.println("Your inventory currently contains: ");
			System.out.println(inventory.toString());
		} else {
			System.out.println("You inventory contains...not a damn thing!!!");
		}
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void addItem(String item) {
		this.inventory.add(item);
	}
}
