package com.juliekevin;

import com.juliekevin.model.Area;
import com.juliekevin.model.CoinPurse;

public class Character {
	String name;
	Area area;
	CoinPurse wallet = new CoinPurse();
	Stash stash;
	
	public Character(String name, String areaName) {
		this.name = name;
		this.area = new Area(areaName, null);
		this.stash = new Stash();
	}
	
	public Location getLocation() {
		return this.area.getCurrentLocation();
	}
	
	public Area getArea() {
		return this.area;
	}

	
	public String getInventory() {
		StringBuilder sb = new StringBuilder("Your inventory currently contains: ");
		sb.append(this.getStash().toString());
	    sb.append("\nYou have $");
	    sb.append(wallet.getMoney());
	    sb.append(".");
	    return sb.toString();
	}

	public Stash getStash() {
		return stash;
	}	
}
