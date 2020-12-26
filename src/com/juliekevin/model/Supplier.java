package com.juliekevin.model;

import java.util.ArrayList;
import java.util.List;

import com.juliekevin.Game;
import com.juliekevin.Sweet;

import utils.GameUtils;

public class Supplier {
	List<Sweet> wares = new ArrayList<>();
	String name;
	SweetList allSweets = Game.getSweetList();

	public Supplier(String name, List<Sweet> wares) {
		this.name = name;
		this.wares = wares;
		if(this.wares == null) {
			int waresQty = GameUtils.getRand(1, 3);
			this.wares = allSweets.selectRandom(waresQty);
		}
	}
	
	public List<Sweet> getInventory() {
		return this.wares;
	}
	
	public void printWares() {
		for(Sweet s : wares) {
			System.out.println(s.getName() + ": " + s.getQty());
		}
	}
}