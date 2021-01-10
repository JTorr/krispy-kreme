package com.juliekevin.model;

import com.juliekevin.Game;

public class Gang {
	String name;
	int reputation;
	int loanAmt;

	public Gang(String name) {
		this.name = name;
		this.reputation = 0;
		this.loanAmt = 0;
	}
	
	public int getReputation() {
		return this.reputation;
	}
	
	public int loseReputation(int amt) {
		this.reputation -= amt;
		return this.reputation;
	}
	
	public int gainReputation(int amt) {
		this.reputation += amt;
		return this.reputation;
	}
	
	public void meetGang() {
		System.out.print("You've met a new gang - ");
		System.out.print(this.getName());
		System.out.println(". ");
		System.out.println("Your reputation with " + this.getName() + " is " +  + this.reputation + ".");
	}
	
	public void createNewLoan(int amt) {
		if(willMakeLoan()) {
			CoinPurse wallet = Game.getPlayer().getWallet();
			this.loanAmt += amt;
			wallet.earnMoney(Integer.toString(amt));
		} else {
			System.out.println("Your reputation is too low, or you owe too much to this gang.");
		}
	}
	
	public void makePayment(int amt) {
		this.loanAmt -= amt;
		System.out.println(this.getName() + " thanks you for your payment of " + Integer.toString(amt));
		if(this.loanAmt > 0) {
			System.out.println("You still owe $" + this.loanAmt + " but they think maybe you can keep your kneecaps today.");
		} else {
			System.out.println("Congratulations on paying off your loan. Your reward is that you don't die.");
			this.reputation += 2;
		}
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
}
