package com.juliekevin.model;

import com.juliekevin.Game;
import com.juliekevin.Character;

public class UndercoverCop extends Buyer {

	public UndercoverCop(String name, int incomeLevel) {
		super(name, incomeLevel);
	}
	
	public Boolean purchaseSweet(int qty) {
		System.out.println("Oh no! " + this.name + " is an Undercover Cop!");
		Character player = Game.getPlayer();
		player.getArea().getPoliceForce().arrestCharacter(this.incomeLevel, true);
		return false;
	}
}
