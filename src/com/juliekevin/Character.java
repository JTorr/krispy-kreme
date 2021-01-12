package com.juliekevin;

import java.util.ArrayList;
import java.util.List;

import com.juliekevin.model.Area;
import com.juliekevin.model.CoinPurse;
import com.juliekevin.model.Gang;
import com.juliekevin.model.Loan;

public class Character {
	String name;
	Area area;
	CoinPurse wallet = new CoinPurse();
	Stash stash;
	int crimeCount;
	int copHeat;
	List<Loan> loans = new ArrayList<>();
	
	public Character(String name, String areaName) {
		this.name = name;
		this.area = new Area(areaName, null);
		this.stash = new Stash();
		this.crimeCount = 0;
	}
	
	// Location Management methods
	
	public Area getArea() {
		return this.area;
	}
	
	public Location getLocation() {
		return this.area.getCurrentLocation();
	}
	
	// Crime Management methods
	
	
	public int getCrimeCount() {
		return this.crimeCount;
	}
	
	public void incrementCrimeCount() {
		this.crimeCount += 1;
	}
		
	public int getCopHeat() {
		return this.copHeat;
	}
	
	public void incrementCopHeat(int amt) {
		this.copHeat += amt;
	}
	
	// Inventory Management methods
	
	public String getInventory() {
		StringBuilder sb = new StringBuilder("Your inventory currently contains: \n");
		sb.append(this.getStash().toString());
	    sb.append("\nYou have $");
	    sb.append(wallet.getMoney());
	    sb.append(".");
	    return sb.toString();
	}

	public Stash getStash() {
		return stash;
	}	
	
	public CoinPurse getWallet() {
		return this.wallet;
	}
	
	// Loan Management methods
	
	public List<Loan> getLoans() {
		return this.loans;
	}
	
	public void addLoan(Loan loan) {
		this.loans.add(loan);
	}
	
	public void removeLoan(Gang gang) {
		Loan loan = findLoan(gang);
		this.loans.remove(loan);
	}
	
	public Loan findLoan(Gang gang) {
		for(Loan loan : this.loans) {
			if(gang == loan.getGangOwed()) {
				return loan;
			}
		}
		return null;
	}
	
	public List<Loan> getOverdueLoans() {
		List<Loan> overdue = new ArrayList<>();
		for(Loan loan : this.loans) {
			if(loan.loanOverdue()) {
				overdue.add(loan);
			}
		}
		return overdue;
	}
}
