package com.juliekevin;
import java.util.HashMap;

public class Stash {
	HashMap<String, Integer> stashList = new HashMap<>();
	HashMap<String, String> purchaseHistory = new HashMap<>();
	
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		for(String sweet : stashList.keySet()) {
			sb.append(sweet);
			sb.append( ": ");
			sb.append(stashList.get(sweet));
			if(purchaseHistory.containsKey(sweet)) {
				sb.append(" (last purchased for $");
				sb.append(purchaseHistory.get(sweet));
				sb.append(")");
			}
			sb.append("\n");
		}
		return sb.toString();
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
	
	public void resetInventory() {
		this.stashList = new HashMap<>();
	}
	
	public void setLastPurchasePrice(String sweet, String price) {
		this.purchaseHistory.put(sweet, price);
	}
}

