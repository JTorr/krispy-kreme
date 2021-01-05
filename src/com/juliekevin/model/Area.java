package com.juliekevin.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.juliekevin.Location;

public class Area {
	String name;
	HashMap<String, Integer> locationLookup = new HashMap<>();
	Jail jail = new Jail();
	
	List<Location> visited = new ArrayList<>();
	int currentIndex = 0;
	Location currentLoc;
	
	public Area(String name, String startLoc) {
		this.name = name;
		if(startLoc == null) {
			startLoc = "Secret Candy Factory";
		}
		this.currentLoc = addLocation(startLoc, 1);
	}
	
	public void visitLocation(String name) {
		Location loc = findOrCreateLoc(name);
		loc.printLocationDetails();
		currentLoc = loc;
	}
	
	public Location findOrCreateLoc(String name) {
		if(locationLookup.containsKey(name)) {
			int index = locationLookup.get(name);
			return visited.get(index);
		} else {
			return addLocation(name, 0);
		}
	}
	
	public void setCurrentLocation(String name) {
		Location loc = findOrCreateLoc(name);
		this.currentLoc = loc;
	}
	
	public Location getCurrentLocation() {
		return this.currentLoc;
	}
	
	public Jail getJail() {
		return this.jail;
	}
	
	private Location addLocation(String name, int level) {
		locationLookup.put(name, currentIndex);
		Location loc = new Location(name, level);
		visited.add(currentIndex, loc);
		currentIndex += 1;
		return loc;
	}
	
}
