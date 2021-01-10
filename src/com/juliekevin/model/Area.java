package com.juliekevin.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.juliekevin.Location;

public class Area {
	String name;
	HashMap<String, Integer> locationLookup = new HashMap<>();
	PoliceForce popo = new PoliceForce();
	List<Location> visited = new ArrayList<>();
	
	String[] gangNames = {"Pixie Stix Gang", "Lollipop Gang", "Sweet Dixies", "Sugar Daddies"};
	int gangIndex = 0;
	List<Gang> gangs = new ArrayList<>();
	
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
	
	public PoliceForce getPoliceForce() {
		return this.popo;
	}
	
	public void meetNewGang() {
		Gang newGang = new Gang(this.gangNames[this.gangIndex]);
		this.gangIndex += 1;
		if(this.gangIndex > this.gangNames.length - 1) {
			this.gangIndex = 0;
		}
		this.gangs.add(newGang);
		newGang.meetGang();
	}
	
	public Gang findGangByName(String name) {
		for(Gang gang : gangs) {
			if(gang.getName().toLowerCase().contains(name.toLowerCase())) {
				return gang;
			}
		}
		return null;
	}
	
	public List<Gang> getGangs() {
		return this.gangs;
	}
	
	private Location addLocation(String name, int level) {
		locationLookup.put(name, currentIndex);
		Location loc = new Location(name, level);
		visited.add(currentIndex, loc);
		currentIndex += 1;
		return loc;
	}
	
}
