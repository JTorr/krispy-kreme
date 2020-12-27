package com.juliekevin;
import java.util.ArrayList;
import java.util.List;

import com.juliekevin.model.CoinPurse;
import com.juliekevin.model.Supplier;

public class Stash {
	List<Sweet> stashList = new ArrayList<>();
	
	public Stash() {
		this.stashList.add(new Sweet("caramel", 0, "20.00"));
		this.stashList.add(new Sweet("donut", 0, "5.00"));
	}
	
	public void buySweet(String name, int quantity, Location location, Character self) {
		Supplier sup = location.getSupplier();
		int maxQty = sup.getAvailableQty(name);
		
		if(maxQty == 0) {
			System.out.println("That Sweet is not available from this supplier.");
			return;
		}
		
		if(quantity > maxQty) {
			System.out.println("Supplier only has " + maxQty + " available.");
			return;
		}
		
		int index = this.getSweetIndex(name, this.stashList);
		
		if(index > -1) {			
			Sweet sweet = this.stashList.get(index);
			String price = CoinPurse.getLocalPrice(sweet.getPrice(), location.getPriceMod());
		
			String total = CoinPurse.getTotalPrice(price, quantity);
				Sweet newQty = this.stashList.get(index);
				try {
					self.wallet.spendMoney(total);
					newQty.setQty(quantity);
					this.stashList.set(index, newQty);
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
		int index = this.getSweetIndex(name, this.stashList);
		if(index > -1) {
			Sweet sweet = this.stashList.get(index);
			if(sweet.quantity < quantity) {
				System.out.println("Insufficient quantity of " + sweet.getName() + ".");
				System.out.println("You have " + sweet.getQtyString() + " of " + sweet.getName() + ".");
				return;
			}
			String price = CoinPurse.getLocalPrice(sweet.getPrice(), location.getPriceMod());
			String total = CoinPurse.getTotalPrice(price, quantity);
			self.wallet.earnMoney(total);

			Sweet newQty = this.stashList.get(index);
			newQty.setQty(sweet.quantity - quantity);
			this.stashList.set(index, newQty);
			
			System.out.println("You sold " + quantity + " of " + sweet.getName());
			System.out.println("You earned $" + total.toString() + " and now have $" + self.wallet.getMoney());
		}
	}
	
	public String toString() {
		String s = "";
		for(Sweet sweet: stashList) {
			s += sweet.getName() + ": " + sweet.getQtyString() + "\n";
		}
		return s;
	}
	
	private int getSweetIndex(String name, List<Sweet> wares) {
		for(int i = 0; i < wares.size(); i++) {
			Sweet testSweet = wares.get(i);
			if(name.equals(testSweet.getName())) {
				return i;
			}
		}
		return -1;
	}	
}
