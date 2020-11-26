package com.juliekevin;
import java.util.*;

import com.juliekevin.*;
import com.juliekevin.Character;

public class Stash {
	List<Drug> stashList = new ArrayList<>();
	
	public Stash() {
		this.stashList.add(new Drug("coke", 0, 20.00));
		this.stashList.add(new Drug("weed", 0, 5.00));
	}
	
	public List buyDrug(String name, int quantity, String location, Character self) {
		if(this.getDrugIndex(name) > -1) {
			int index = this.getDrugIndex(name);
			Drug drug = this.stashList.get(index);
		
			double price = drug.getPrice() * quantity;
			if(self.getMoney() >= price) {
				Drug newQty = this.stashList.get(index);
				newQty.setQty(quantity);
				this.stashList.set(index, newQty);
				
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
		return stashList;
	}
	
	public String toString() {
		String s = "";
		for(Drug drug: stashList) {
			s += drug.getName() + ": " + drug.getQty() + "\n";
		}
		return s;
	}
	
	private int getDrugIndex(String name) {
		for(int i = 0; i < stashList.size(); i++) {
			Drug testDrug = stashList.get(i);
			if(name.equals(testDrug.getName())) {
				return i;
			}
		}
		return -1;
	}	
}
