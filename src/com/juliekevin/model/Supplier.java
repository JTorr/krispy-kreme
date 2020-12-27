package com.juliekevin.model;

import java.util.List;

import com.juliekevin.Game;
import com.juliekevin.Stash;
import com.juliekevin.Sweet;

import utils.GameUtils;

public class Supplier {
	SweetList allSweets = Game.getSweetList();
	Stash stash = new Stash(allSweets);
	List<Sweet> wares;
	String name;
	

	public Supplier(String name, List<Sweet> wares) {
		this.name = name;
		this.wares = wares;
		if(this.wares == null) {
			int waresQty = GameUtils.getRand(1, 3);
			this.wares = allSweets.selectRandom(waresQty);
			for(Sweet sweet : this.wares) {
				int randQty = GameUtils.getRand(1, 100);
				stash.addNewItem(sweet.getName(), randQty);
			}
		}
	}
	
	public List<Sweet> getInventory() {
		return this.wares;
	}
	
	public void printWares() {
		for(Sweet s : wares) {
			System.out.println(s.getName() + ": " + stash.getItemQty(s.getName()));
		}
	}
	
	public int getAvailableQty(String sweetName) {
		int qty = 0;
		for(Sweet s : wares) {
			if(s.getName().equalsIgnoreCase(sweetName)) {
				qty = stash.getItemQty(sweetName);
			}
		}
		return qty;
	}
}