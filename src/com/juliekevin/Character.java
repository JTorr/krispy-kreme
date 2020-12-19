package com.juliekevin;

import com.juliekevin.model.CoinPurse;

public class Character {
	String name;
	String location;
	CoinPurse wallet = new CoinPurse();
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
		StringBuilder sb = new StringBuilder("Your inventory currently contains: ");
		sb.append(this.getStash().toString());
	    sb.append("\nYou have $");
	    sb.append(wallet.getMoney());
	    sb.append(".");
	    System.out.println(sb.toString());
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public Stash getStash() {
		return stash;
	}	
}
