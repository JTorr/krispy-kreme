package com.juliekevin;
import java.util.ArrayList;
import java.util.List;

import com.juliekevin.model.CoinPurse;

public class Stash {
	List<Sweet> stashList = new ArrayList<>();
	
	public Stash() {
		this.stashList.add(new Sweet("caramel", 0, "20.00"));
		this.stashList.add(new Sweet("donut", 0, "5.00"));
	}
	
	public void buySweet(String name, int quantity, String location, Character self) {
		if(this.getSweetIndex(name) > -1) {
			int index = this.getSweetIndex(name);
			Sweet sweet = this.stashList.get(index);
		
			String price = CoinPurse.getTotalPrice(sweet.getPrice(), quantity);
				Sweet newQty = this.stashList.get(index);
				try {
					self.wallet.spendMoney(price);
					newQty.setQty(quantity);
					this.stashList.set(index, newQty);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("Price is " + price);
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
	
	public void sellSweet(String name, int quantity, String location, Character self) {
		int index = this.getSweetIndex(name);
		if(index > -1) {
			Sweet sweet = this.stashList.get(index);
			if(sweet.quantity < quantity) {
				System.out.println("Insufficient quantity of " + sweet.getName() + ".");
				System.out.println("You have " + sweet.getQty() + " of " + sweet.getName() + ".");
				return;
			}
			String price = CoinPurse.getTotalPrice(sweet.getPrice(), quantity);
			self.wallet.earnMoney(price);

			Sweet newQty = this.stashList.get(index);
			newQty.setQty(sweet.quantity - quantity);
			this.stashList.set(index, newQty);
			
			System.out.println("You sold " + quantity + " of " + sweet.getName());
			System.out.println("You earned $" + price.toString() + " and now have $" + self.wallet.getMoney());
		}
	}
	
	public String toString() {
		String s = "";
		for(Sweet sweet: stashList) {
			s += sweet.getName() + ": " + sweet.getQty() + "\n";
		}
		return s;
	}
	
	private int getSweetIndex(String name) {
		for(int i = 0; i < stashList.size(); i++) {
			Sweet testSweet = stashList.get(i);
			if(name.equals(testSweet.getName())) {
				return i;
			}
		}
		return -1;
	}	
}
