package com.juliekevin.model;

import utils.GameUtils;
import com.juliekevin.Character;

public class EventGenerator {	
	Character player;
	
	public EventGenerator(Character player) {
		this.player = player;
	}
	
	public void checkForEvents() {
		int rand = GameUtils.getRand(1, 100);
		if(rand < 10) {
			generateRandomEvent(rand);
		}
		if(rand % 3 == 0) {
			generateRandomEvent(rand);
		}
	}
	
	private void generateRandomEvent(int rand) {
		if(rand < 10) {
			policePatrolEvent();
		}
	}
	
	private void policePatrolEvent() {
		System.out.println("A police cruiser rides by, and the cop looks directly at you.");
		int heat = player.getCopHeat();
		if(heat < 10) {
			System.out.println("Luckily, they don't recognize a petty thug like you.");
		}
		if(heat > 100) {
			System.out.println("He chokes on his sugar-free donut, instantly recognizing you from the Most Wanted list.");
			player.getArea().getPoliceForce().streetArrest();
		}
		
		int rand = GameUtils.getRand(heat, 100);		
		if(rand > 80) {
			System.out.println("The car screeches to a halt! They've recognized you!");
			player.getArea().getPoliceForce().streetArrest();
		} else {
			System.out.println("His eyes glide past you, and he takes a bite of his sugar-free donut.");
		}
	}

}
