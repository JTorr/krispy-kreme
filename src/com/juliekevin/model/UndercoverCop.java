package com.juliekevin.model;

import com.juliekevin.Game;
import com.juliekevin.Character;

public class UndercoverCop extends Buyer {

	public UndercoverCop(String name, int incomeLevel) {
		super(name, incomeLevel);
	}
	
	public Boolean purchaseSweet(int qty) {
		Character player = Game.getPlayer();
		player.getArea().getJail().arrestSeller(this.name, this.incomeLevel);
		return false;
	}
}
