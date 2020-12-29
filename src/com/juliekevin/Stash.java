package com.juliekevin;
import java.util.HashMap;

import com.juliekevin.model.CoinPurse;
import com.juliekevin.model.SweetList;

public class Stash {
	HashMap<String, Integer> stashList = new HashMap<>();
	SweetList allSweets;
	
	public Stash(SweetList allSweets) {
		this.allSweets = allSweets;
	}
	
	public void sellSweet(String name, int quantity, Location location, Character self) {
		if(stashList.containsKey(name)) {
			Sweet sweet = allSweets.findByName(name);
			if(stashList.get(name) < quantity) {
				System.out.println("Insufficient quantity of " + sweet.getName() + ".");
				System.out.println("You have " + this.stashList.get(name) + " of " + sweet.getName() + ".");
				return;
			}
			String price = CoinPurse.getLocalPrice(sweet.getPrice(), location.getPriceMod());
			String total = CoinPurse.getTotalPrice(price, quantity);
			self.wallet.earnMoney(total);

			int newQty = stashList.get(name) - quantity;
			this.stashList.put(name, newQty);
			
			System.out.println("You sold " + quantity + " of " + sweet.getName());
			System.out.println("You earned $" + total.toString() + " and now have $" + self.wallet.getMoney());
		} else {
			System.out.println("Sweet does not exist in your inventory.");
		}
	}
	
	public String toString() {
		return stashList.toString();
	}
	
	public void addNewItem(String name, int qty) {
		this.stashList.put(name, qty);
	}
	
	public int getItemQty(String name) {
		return this.stashList.get(name);
	}
	
	public Boolean containsItem(String sweetName) {
		return stashList.containsKey(sweetName);
	}
	
	public void setQty(String sweetName, int qty) {
		this.stashList.put(sweetName, qty);
	}

}

