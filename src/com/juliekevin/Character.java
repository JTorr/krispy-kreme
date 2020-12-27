package com.juliekevin;

import com.juliekevin.model.CoinPurse;

public class Character {
	String name;
	Location location;
	CoinPurse wallet = new CoinPurse();
	Stash stash;
	
	public Character(String name, String location) {
		this.name = name;
		this.location = new Location(location, 1);
		this.stash = new Stash(Game.getSweetList());
	}
	
	public Location getLocation() {
		return this.location;
	}

	
	public String getInventory() {
		StringBuilder sb = new StringBuilder("Your inventory currently contains: ");
		sb.append(this.getStash().toString());
	    sb.append("\nYou have $");
	    sb.append(wallet.getMoney());
	    sb.append(".");
	    return sb.toString();
	}
	
	public void setLocation(String location) {
		this.location = new Location(location, 0);
	}

	public Stash getStash() {
		return stash;
	}	
}
