package com.juliekevin;

import com.juliekevin.model.Area;
import com.juliekevin.model.CoinPurse;

public class Character {
	String name;
	Area area;
	CoinPurse wallet = new CoinPurse();
	Stash stash;
	int crimeCount;
	
	public Character(String name, String areaName) {
		this.name = name;
		this.area = new Area(areaName, null);
		this.stash = new Stash();
		this.crimeCount = 0;
	}
	
	public Location getLocation() {
		return this.area.getCurrentLocation();
	}
	
	public Area getArea() {
		return this.area;
	}
	
	public int getCrimeCount() {
		return this.crimeCount;
	}
	
	public void incrementCrimeCount() {
		this.crimeCount += 1;
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
	
	public CoinPurse getWallet() {
		return this.wallet;
	}
}
