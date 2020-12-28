package com.juliekevin;
import java.util.HashMap;

import com.juliekevin.model.CoinPurse;
import com.juliekevin.model.Supplier;
import com.juliekevin.model.SweetList;

public class Stash {
	HashMap<String, Integer> stashList = new HashMap<>();
	SweetList allSweets;
	
	public Stash(SweetList allSweets) {
		this.stashList.put("caramel", 5);
		this.stashList.put("donut", 10);
		this.allSweets = allSweets;
	}
	
	public void buySweet(String name, int quantity, Location location, Character self) {
		Supplier sup = location.getSupplier();
		int supplierQty = sup.getAvailableQty(name);
		
		if(supplierQty == 0) {
			System.out.println("That Sweet is not available from this supplier.");
			return;
		}
		
		if(quantity > supplierQty) {
			System.out.println("Supplier only has " + supplierQty + " available.");
			return;
		}
		
		Stash supplierStash = sup.getInventory();
		
		if(supplierStash.containsItem(name)) {			
			Sweet sweet = allSweets.findByName(name);
			String price = CoinPurse.getLocalPrice(sweet.getPrice(), location.getPriceMod());
		
			String total = CoinPurse.getTotalPrice(price, quantity);
				try {
					self.wallet.spendMoney(total);
					int currentQty = 0;
					if(stashList.containsKey(name)) {
						currentQty = this.stashList.get(name);
					}
					supplierStash.setQty(name, supplierQty - quantity);
					this.stashList.put(name, currentQty + quantity);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("Price is " + total);
					System.out.println("Your funds: " + self.wallet.getMoney());
					System.out.println("Insufficient funds!!!");
					return;
				}
				System.out.println("You now have " + quantity + " of " + sweet.getName() + ".");
				
				System.out.println("You have $" + self.wallet.getMoney() + ".");
			} else {
			System.out.println("Sweet name not found.");
		}
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

