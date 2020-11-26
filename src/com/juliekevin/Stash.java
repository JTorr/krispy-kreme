package com.juliekevin;
import java.util.*;

import com.juliekevin.*;

public class Stash {
	List<Drug> stash = new ArrayList<>();
	
	public Stash() {
		this.stash.add(new Drug("coke", 0, 20.00));
	}

	
	public void buyDrugs(String drugName, int quantity, String location, Character self) {
		Drug drug = this.stash.get(0);
		double price = drug.getPrice() * quantity;
		if(self.getMoney() >= price) {
			this.stash.get(0).setQty(quantity);
			self.setMoney(self.getMoney() - price);
			System.out.println("You now have " + quantity + " of " + drugName + ".");
			System.out.println("You have $" + self.getMoney() + ".");
		} else {
			System.out.println("Insufficient funds!!!");
		}
	}
	
	public String toString() {
		String s = "";
		for(Drug drug: stash) {
			s += drug.getName() + ": " + drug.getQty() + "\n";
		}
		return s;
	}
}
