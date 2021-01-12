package com.juliekevin.model;

import utils.GameUtils;

import java.util.List;

import com.juliekevin.Character;

public class EventGenerator {	
	Character player;
	
	public EventGenerator(Character player) {
		this.player = player;
	}
	
	public void checkForEvents() {
		checkLoanStatus();
		int rand = GameUtils.getRand(1, 100);
		if(rand < 10) {
			generateRandomEvent();
		}
		if(rand % 3 == 0) {
			generateRandomEvent();
		}
	}
	
	private void checkLoanStatus() {
		List<Loan> overdueLoans = player.getOverdueLoans();
		if(overdueLoans.size() > 0) {
			System.out.println("The following loans are overdue: ");
			for(Loan loan : overdueLoans) {
				Gang gang = loan.getGangOwed();
				System.out.println("\t- " + loan.getAmtOwed() + " from " + gang.getName());
				gang.loseReputation(1);
			}
		}
	}
	
	private void generateRandomEvent() {
		int rand = GameUtils.getRand(1, 100);
		if(rand < 10) {
			policePatrolEvent();
		} else {
			meetGangEvent();
		}
	}
	
	private void policePatrolEvent() {
		System.out.println("A police cruiser rides by, and the cop looks directly at you.");
		int heat = player.getCopHeat();
		if(heat < 10) {
			System.out.println("Luckily, they don't recognize a petty thug like you.");
			return;
		}
		if(heat > 100) {
			System.out.println("He chokes on his sugar-free donut, instantly recognizing you from the Most Wanted list.");
			player.getArea().getPoliceForce().streetArrest();
			return;
		}
		
		int rand = GameUtils.getRand(heat, 100);		
		if(rand > 80) {
			System.out.println("The car screeches to a halt! They've recognized you!");
			player.getArea().getPoliceForce().streetArrest();
		} else {
			System.out.println("His eyes glide past you, and he takes a bite of his sugar-free donut.");
		}
	}
	
	private void meetGangEvent() {
		this.player.getArea().meetNewGang();
	}

}
