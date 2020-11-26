package com.juliekevin;
import java.util.*;

import com.juliekevin.*;
import com.juliekevin.Character;

public class Stash {
	List<Drug> stash = new ArrayList<>();
	
	public Stash() {
		this.stash.add(new Drug("coke", 0, 20.00));
		this.stash.add(new Drug("weed", 0, 5.00));
	}
	
	public List buyDrug(String name, int quantity, String location, Character self) {
		if(this.getDrugIndex(name) > -1) {
			int index = this.getDrugIndex(name);
			Drug drug = this.stash.get(index);
		
			double price = drug.getPrice() * quantity;
			if(self.getMoney() >= price) {
				//TODO: Fix update sash
				Drug newQty = this.stash.get(index);
				newQty.setQty(quantity);
				this.stash.set(index, newQty);
				
				self.setMoney(self.getMoney() - price);
				System.out.println("You now have " + quantity + " of " + drug.getName() + ".");
				// TODO: format money properly
				System.out.println("You have $" + self.getMoney() + ".");
			} else {
				System.out.println("Insufficient funds!!!");
			}
		} else {
			System.out.println("Drug name not found.");
		}
		return stash;
	}
	
	public String toString() {
		String s = "";
		for(Drug drug: stash) {
			s += drug.getName() + ": " + drug.getQty() + "\n";
		}
		return s;
	}
	
	private int getDrugIndex(String name) {
		for(int i = 0; i < stash.size(); i++) {
			Drug testDrug = stash.get(i);
			if(name.equals(testDrug.getName())) {
				return i;
			}
		}
		return -1;
	}	
}
