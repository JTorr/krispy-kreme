package com.juliekevin.model;

import com.juliekevin.Game;
import com.juliekevin.Character;

public class Gang {
	String name;
	int reputation;
	Boolean loansOutstanding;
	Boolean hitOrdered;

	public Gang(String name) {
		this.name = name;
		this.reputation = 0;
		this.loansOutstanding = false;
		this.hitOrdered = false;
	}
	
	public int getReputation() {
		return this.reputation;
	}
	
	public int loseReputation(int amt) {
		this.reputation -= amt;
		System.out.println("Your reputation with " + this.getName() + " has fallen to " + this.getReputation());
		reviewHitStatus();
		return this.reputation;
	}
	
	public int gainReputation(int amt) {
		this.reputation += amt;
		System.out.println("Your reputation with " + this.getName() + " has risen to " + this.getReputation());
		reviewHitStatus();
		return this.reputation;
	}
	
	public void meetGang() {
		System.out.print("You've met a new gang - ");
		System.out.print(this.getName());
		System.out.println(". ");
		System.out.println("Your reputation with " + this.getName() + " is " +  + this.reputation + ".");
	}
	
	public Boolean createNewLoan(int amt) {
		if(willMakeLoan() && !loansOutstanding) {
			Character player = Game.getPlayer();
			CoinPurse wallet = player.getWallet();
			Loan loan = new Loan(this, amt);
			player.addLoan(loan);
			wallet.earnMoney(Integer.toString(amt));
			this.loansOutstanding = true;
			return true;
		} else {
			System.out.println("Your reputation is too low, or you owe too much to this gang.");
			return false;
		}
	}
	
	public void payOffLoan() {
		this.loansOutstanding = false;
		this.gainReputation(2);
		Game.getPlayer().removeLoan(this);
	}
	
	private Boolean willMakeLoan() {
		if(this.reputation >= 0) {
			return true;
		} else {
			return false;
		}
	}

	public String getName() {
		return name;
	}
	
	public void reviewHitStatus() {
		Boolean currentHit = this.hitOrdered;
		if(this.reputation <= -5) {
			this.hitOrdered = true;
			System.out.println(this.name + " has ordered a hit on you.");
		} else if (currentHit){
			this.hitOrdered = false;
			System.out.println(this.name + " has cancelled the hit on you.");
		} else {
			this.hitOrdered = false;
		}
	}

}
