package com.juliekevin;
import java.util.HashMap;
import com.juliekevin.model.SweetList;

public class Stash {
	HashMap<String, Integer> stashList = new HashMap<>();
	SweetList allSweets;
	
	public Stash(SweetList allSweets) {
		this.allSweets = allSweets;
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

