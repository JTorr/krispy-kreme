package com.juliekevin;

import com.juliekevin.model.Junkie;
import com.juliekevin.model.Supplier;
import com.juliekevin.model.UndercoverCop;
import com.juliekevin.model.Buyer;

import utils.GameUtils;

public class Location {
	String name;
	String priceModifier;
	int incomeLevel;
	Supplier supplier;
	Buyer junkie;
	String priceModifiers[] = new String[] {"0.75", "0.80", "0.85", "0.90", "0.95", "1.00", "1.05", "1.15", "1.25", "1.35"};
	
	public Location(String name, int incomeLevel) {
		this.name = name;
		// Set random income level if not set explicitly
		if(incomeLevel == 0) {
			this.incomeLevel = GameUtils.getRand(1,10);
		} else {
			this.incomeLevel = incomeLevel;
		}
		
		// Set price modifier based on income level
		this.priceModifier = priceModifiers[this.incomeLevel -1];
		this.supplier = new Supplier("Don " + GameUtils.getRandName(), null, this.priceModifier);
		
		// Determine if buyer is an Undercover Cop or normal Junkie
		int rand = GameUtils.getRand(1, 10);

		if(rand == 7) {
			this.junkie = new UndercoverCop(GameUtils.getRandName() + " the Sweet Junkie", this.incomeLevel);
		} else {
			this.junkie = new Junkie(GameUtils.getRandName() + " the Sweet Junkie", this.incomeLevel);
		}
	}
	
	public void printLocationDetails() {
		System.out.println("You are currently at: " + this.name);
		System.out.println("This area is " + this.locationType());
		this.supplier.printWares();
		this.junkie.printBuyRequest();
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
	
	public void checkItemsForSale() {
		this.supplier.printWares();
	}
	
	public Boolean junkieWillBuy(String sweetName, int qty) {
		return this.junkie.willAcceptPurchase(sweetName, qty);
	}
}
