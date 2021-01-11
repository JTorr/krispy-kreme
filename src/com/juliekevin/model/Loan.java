package com.juliekevin.model;

public class Loan {
	int amt;
	int originalAmt;
	int daysSinceLastPayment;
	int minPayment;
	Gang gangOwed;

	public Loan(Gang gang, int amt) {
		this.daysSinceLastPayment = 0;
		this.amt = amt;
		this.originalAmt = amt;
		this.gangOwed = gang;
		this.minPayment = this.originalAmt / 10;
	}
	
	public void makePayment(int amt) {
		if(amt < this.minPayment) {
			System.out.println(getGangOwed().getName() + " doesn't want your chump change!");
			System.out.println("The minimum payment is " + minPayment);
			return;
		} 
		this.amt -= amt;
		if(this.amt > 0) {
			System.out.println(getGangOwed().getName() + " thanks you for your payment of $" + Integer.toString(amt));
			System.out.println("You still owe $" + this.amt + " but they think maybe you can keep your kneecaps today.");
		} else {
			System.out.println("Congratulations on paying off your loan. Your reward is that you don't die.");
			this.getGangOwed().payOffLoan();
		}
	}
	
	public int getMinPayment() {
		return this.minPayment;
	}

	public Gang getGangOwed() {
		return gangOwed;
	}
	
	public int getAmtOwed() {
		return amt;
	}

}
