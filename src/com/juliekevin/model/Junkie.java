package com.juliekevin.model;

import com.juliekevin.Game;
import com.juliekevin.Sweet;

import utils.GameUtils;

public class Junkie {
	Sweet desiredSweet;
	String name;
	int desiredQty;
	int budgetLevel;
	String priceMod;
	String priceModifiers[] = new String[] {"0.85", "0.90", "0.95", "1.00", "1.05", "1.10", "1.15", "1.25", "1.35", "1.45"};
	

	public Junkie(String name, int incomeLevel) {
		this.name = name;
		this.desiredSweet = Game.getSweetList().selectRandom(1).get(0);
		this.desiredQty = GameUtils.getRand(1, 10);
		this.budgetLevel = GameUtils.getRand(incomeLevel - 1, incomeLevel + 2);
		setPriceMod();
	}
	
	public Sweet getSweet() {
		return this.desiredSweet;
	}
	
	public int getMaxQty() {
		return this.desiredQty;
	}
	
	public String getMaxPrice() {
		return CoinPurse.getLocalPrice(this.desiredSweet.getPrice(), this.priceMod);
	}
	
	public void printBuyRequest() {
		System.out.print(this.name + " is looking to buy " + this.desiredQty + " of " + this.desiredSweet.getName());
		System.out.println("and is willing to pay $" + getMaxPrice());
	}
	
	private void setPriceMod() {
		if(this.budgetLevel < 0) {
			this.budgetLevel = 0;
		}
		if(this.budgetLevel >= this.priceModifiers.length) {
			this.budgetLevel = this.priceModifiers.length - 1;
		}
		this.priceMod = this.priceModifiers[this.budgetLevel];
	}

}
