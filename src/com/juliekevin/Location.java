package com.juliekevin;

import java.util.Random;

public class Location {
	String name;
	double priceModifier;
	
	public Location(String name) {
		this.name = name;
		Random rand = new Random();
		this.priceModifier = rand.nextDouble();
	}
}
