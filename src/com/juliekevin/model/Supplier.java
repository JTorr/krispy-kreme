package com.juliekevin.model;

import java.util.List;

import com.juliekevin.Game;
import com.juliekevin.Stash;
import com.juliekevin.Sweet;

import utils.GameUtils;

public class Supplier {
	SweetList allSweets;
	Stash stash;
	List<Sweet> wares;
	String name;
	

	public Supplier(String name, List<Sweet> wares) {
		this.name = name;
		this.wares = wares;
		this.stash = new Stash();
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
		for(Sweet s : wares) {
			System.out.println(s.getName() + ": " + this.stash.getItemQty(s.getName()));
		}
	}
	
	public int getAvailableQty(String sweetName) {
		if(this.stash.containsItem(sweetName)) {
			return this.stash.getItemQty(sweetName);
		} else {
			return 0;
		}
	}
}