package com.juliekevin.model;

import com.juliekevin.Game;
import com.juliekevin.Character;

public class UndercoverCop extends Buyer {

	public UndercoverCop(String name, int incomeLevel) {
		super(name, incomeLevel);
	}
	
	public Boolean purchaseSweet(int qty) {
		arrestSeller();
		return false;
	}
	
	private void arrestSeller() {
		Character player = Game.getPlayer();
		String fineAmt = CoinPurse.getTotalPrice("150.00", player.getCrimeCount());
		String lawyerFee = CoinPurse.getTotalPrice("200.00", player.getCrimeCount() + super.incomeLevel);
		System.out.println("Oh no! " + this.name + " was an Undercover Cop!");
		System.out.println("You're hauled off to jail, and they seize your stash.\n");
		System.out.println("What do you want to do?");
		System.out.println("1 - pay $" + fineAmt + " bail (get released but increase Cop Heat & future bail)");
		System.out.println("2 - pay $" + lawyerFee + " lawyer fee (get released without increasing Cop Heat or future bail");
		System.out.println("3 - flee (escape with your stash and money, but dramatically increase Cop Heat");
		try {
    		System.out.print(" > ");
            String input = Game.scanner.nextLine();
            handleResponse(input, player, fineAmt, lawyerFee);
		} catch(Exception e) {
        	System.out.println(e.getMessage());
        }
	}
	
	private void handleResponse(String input, Character player, String fineAmt, String lawyerFee) {
		if(input.matches("1|bail")) {
			payBail(player, fineAmt);
		} else if(input.matches("2|lawyer")) {
			payLawyer(player, lawyerFee);
		} else if(input.matches("3|flee")) {
			fleePolice(player);
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
	}
	
	private void payLawyer(Character player, String lawyerFee) {
		System.out.println("The lawyer wasn't cheap, but at least he kept this off your record.");
		player.getStash().resetInventory();
		player.getWallet().deductFine(lawyerFee);
	}
	
	private void fleePolice(Character player) {
		System.out.println("You flee the police! They'll be looking for you...");
		// TODO: implement CopHeat
	}

}
