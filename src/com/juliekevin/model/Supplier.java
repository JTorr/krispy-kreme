package com.juliekevin.model;

import java.util.List;

import com.juliekevin.Game;
import com.juliekevin.Stash;
import com.juliekevin.Sweet;

import utils.GameUtils;

public class Supplier {
	Stash stash;
	List<Sweet> wares;
	String name;
	String priceMod;
	

	public Supplier(String name, List<Sweet> wares, String priceMod) {
		this.name = name;
		this.wares = wares;
		this.stash = new Stash();
		this.priceMod = priceMod;
		if(this.wares == null) {
			int waresQty = GameUtils.getRand(1, 3);
			this.wares = Game.getSweetList().selectRandom(waresQty);
		}
		for(Sweet sweet : this.wares) {
			int randQty = GameUtils.getRand(1, 100);
			stash.addNewItem(sweet.getName(), randQty);
		}
	}
	
	public Stash getInventory() {
		return this.stash;
	}
	
	public void printWares() {
		System.out.println(this.name + " has these items available for purchase: ");
		for(Sweet s : wares) {
			int qty = this.stash.getItemQty(s.getName());
			System.out.println(s.getName() + ": $" + getSupplierPrice(s) + " (" + qty + ")");
		}
		System.out.println("-------\n");
	}
	
	public String getSupplierPrice(Sweet sweet) {
		return CoinPurse.getLocalPrice(sweet.getPrice(), this.priceMod);
	}
	
	public String getSupplierName() {
		return this.name;
	}
	
	public int getAvailableQty(String sweetName) {
		if(this.stash.containsItem(sweetName)) {
			return this.stash.getItemQty(sweetName);
		} else {
			return 0;
		}
	}
}