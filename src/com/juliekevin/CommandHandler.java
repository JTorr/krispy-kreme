package com.juliekevin;

import com.juliekevin.model.CoinPurse;
import com.juliekevin.model.Supplier;

public class CommandHandler {
	Character self;

	public CommandHandler(Character self) {
		this.self = self;
	}

	public void buySweet(String name, int quantity){
		Location location = this.self.getLocation();
		Supplier sup = location.getSupplier();
		Stash stashList = this.self.getStash();
		
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
			Sweet sweet = Game.sweetList.findByName(name);
			String price = CoinPurse.getLocalPrice(sweet.getPrice(), location.getPriceMod());
		
			String total = CoinPurse.getTotalPrice(price, quantity);
				try {
					self.wallet.spendMoney(total);
					int currentQty = 0;
					if(stashList.containsItem(name)) {
						currentQty = stashList.getItemQty(name);
					}
					supplierStash.setQty(name, supplierQty - quantity);
					stashList.addNewItem(name, currentQty + quantity);
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
	
	public void sellSweet(String name, int quantity) {
		Location location = this.self.getLocation();
		Stash stashList = this.self.getStash();
		
		if(stashList.containsItem(name)) {
			Sweet sweet = Game.sweetList.findByName(name);
			if(stashList.getItemQty(name) < quantity) {
				System.out.println("Insufficient quantity of " + sweet.getName() + ".");
				System.out.println("You have " + stashList.getItemQty(name) + " of " + sweet.getName() + ".");
				return;
			}
			String price = CoinPurse.getLocalPrice(sweet.getPrice(), location.getPriceMod());
			String total = CoinPurse.getTotalPrice(price, quantity);
			self.wallet.earnMoney(total);

			int newQty = stashList.getItemQty(name) - quantity;
			stashList.addNewItem(name, newQty);
			
			System.out.println("You sold " + quantity + " of " + sweet.getName());
			System.out.println("You earned $" + total.toString() + " and now have $" + self.wallet.getMoney());
		} else {
			System.out.println("Sweet does not exist in your inventory.");
		}
	}
}
