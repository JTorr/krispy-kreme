package com.juliekevin.model;

import com.juliekevin.Character;
import com.juliekevin.Game;

import utils.GameUtils;

public class PoliceForce {

	public void arrestCharacter(int locationIncomeLevel, Boolean fleePermitted) {
		Character player = Game.getPlayer();
		String fineAmt = CoinPurse.getTotalPrice("150.00", player.getCrimeCount());
		String lawyerFee = CoinPurse.getTotalPrice("200.00", player.getCrimeCount() + locationIncomeLevel);
		System.out.println("You're hauled off to jail, and they seize your stash.\n");
		System.out.println("What do you want to do?");
		System.out.println("1 - pay $" + fineAmt + " bail (get released but increase Cop Heat & future bail)");
		System.out.println("2 - pay $" + lawyerFee + " lawyer fee (get released without increasing Cop Heat or future bail");
		if(fleePermitted) {
			System.out.println("3 - flee (possibly escape with your stash and money; dramatically increase Cop Heat");
		}
		try {
    		System.out.print(" > ");
            String input = Game.scanner.nextLine();
            respondToArrest(input, player, fineAmt, lawyerFee, fleePermitted, locationIncomeLevel);
		} catch(Exception e) {
        	System.out.println(e.getMessage());
        }
	}
	
	public void streetArrest() {
		Character player = Game.getPlayer();
		System.out.println("What will you do?!");
		System.out.println("1- freeze (don't fight the arrest)");
		System.out.println("2- run (escape OR get in MORE trouble");
		try {
    		System.out.print(" > ");
            String input = Game.scanner.nextLine();
            freezeOrFlee(input, player);
		} catch(Exception e) {
        	System.out.println(e.getMessage());
        }
	}
	
	private void freezeOrFlee(String input, Character player) {
		if(input.matches("1|freeze")) {
			arrestCharacter(1, false);
		} else if(input.matches("2|run")) {
			fleePolice(player, 1);
		} else {
			System.out.println("Response not understood.");
			arrestCharacter(1, false);
		}
	}

	
	private void respondToArrest(String input, Character player, String fineAmt, String lawyerFee, Boolean fleePermitted, int incomeLevel) {
		if(input.matches("1|bail")) {
			payBail(player, fineAmt);
		} else if(input.matches("2|lawyer")) {
			payLawyer(player, lawyerFee);
		} else if(input.matches("3|flee") && fleePermitted) {
			fleePolice(player, incomeLevel);
		} else {
			System.out.println("Response not understood.");
			payBail(player, fineAmt);
		}
	}
	
	private void payBail(Character player, String fineAmt) {
		System.out.println("You paid the bail and accepted the mark on your record.");
		player.getStash().resetInventory();	
		player.incrementCrimeCount();
		player.getWallet().deductFine(fineAmt);
		player.incrementCopHeat(player.getCrimeCount());
		System.out.println("You have " + player.getCrimeCount() + " crime(s) on your record.");
		System.out.println("Your Cop Heat has risen to " + player.getCopHeat());
	}
	
	private void payLawyer(Character player, String lawyerFee) {
		System.out.println("The lawyer wasn't cheap, but at least he kept this off your record.");
		player.getStash().resetInventory();
		player.getWallet().deductFine(lawyerFee);
	}
	
	private void fleePolice(Character player, int incomeLevel) {
		player.incrementCopHeat(10);
		System.out.println("Fleeing the cops, huh? That'll get you major heat.");
		System.out.println("Your Cop Heat has risen to " + player.getCopHeat());
		
		int rand = GameUtils.getRand(1, 3);
		if(rand == 1) {
			System.out.println("You try to run, but you've been eating too many of your own donuts.");
			System.out.println("The cops laugh as they put you in handcuffs.");
			arrestCharacter(incomeLevel, false);
		} else {
			System.out.println("You flee the police! They'll be looking for you...");
		}		
	}
}
