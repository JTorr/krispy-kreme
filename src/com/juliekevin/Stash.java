package com.juliekevin;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Stash {
	List<Sweet> stashList = new ArrayList<>();
	
	public Stash() {
		this.stashList.add(new Sweet("caramel", 0, new BigDecimal("20.00")));
		this.stashList.add(new Sweet("donut", 0, new BigDecimal("5.00")));
	}
	
	public void buySweet(String name, int quantity, String location, Character self) {
		if(this.getSweetIndex(name) > -1) {
			int index = this.getSweetIndex(name);
			Sweet sweet = this.stashList.get(index);
		
			BigDecimal price = sweet.getPrice().multiply(new BigDecimal(quantity));
			if(self.getMoney().compareTo(price) >= 0) {
				Sweet newQty = this.stashList.get(index);
				newQty.setQty(quantity);
				this.stashList.set(index, newQty);
				
				self.setMoney(self.getMoney().subtract(price));
				System.out.println("You now have " + quantity + " of " + sweet.getName() + ".");
				// TODO: format money properly
				System.out.println("You have $" + self.getMoney() + ".");
			} else {
				System.out.println("Insufficient funds!!!");
			}
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
			BigDecimal price = sweet.getPrice().multiply(new BigDecimal(quantity));
			self.setMoney(self.money.add(price));
			Sweet newQty = this.stashList.get(index);
			newQty.setQty(sweet.quantity - quantity);
			this.stashList.set(index, newQty);
			
			System.out.println("You sold " + quantity + " of " + sweet.getName());
			System.out.println("You earned $" + price.toString() + " and now have $" + self.money);
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
