package com.juliekevin;

import com.juliekevin.model.Supplier;

import utils.GameUtils;

public class Location {
	String name;
	String priceModifier;
	int incomeLevel;
	Supplier supplier;
	String priceModifiers[] = new String[] {"0.75", "0.80", "0.85", "0.90", "0.95", "1.00", "1.05", "1.15", "1.25", "1.35"};
	
	public Location(String name, int incomeLevel) {
		this.name = name;
		// Set random income level if not set explicitly
		if(incomeLevel == 0) {
			this.incomeLevel = GameUtils.getRand(1,10);
		} else {
			this.incomeLevel = incomeLevel;
		}
		
		this.supplier = new Supplier("Supplier", null);
		// Set price modifier based on income level
		this.priceModifier = priceModifiers[this.incomeLevel -1];
	}
	
	public void printLocationDetails() {
		System.out.println("Welcome to " + this.name);
		System.out.println("This area is " + this.locationType());
		System.out.println("The supplier at this location has the following available: ");
		this.supplier.printWares();
	}
	
	private String locationType() {
		if(this.incomeLevel < 3) {
			return "very low income";
		} else if(this.incomeLevel < 5) {
			return "low income";
		} else if(this.incomeLevel < 6) {
			return "middle income";
		} else if(this.incomeLevel < 8) {
			return "high income";
		} else {
			return "very high income";
		}
	}
	
	public String getPriceMod() {
		return this.priceModifier;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Supplier getSupplier() {
		return this.supplier;
	}
}
