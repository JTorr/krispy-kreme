package com.juliekevin;

public class Sweet {
	String name;
	int quantity;
	String price;
	
	public Sweet(String name, int qty, String price) {
		this.name = name;
		this.quantity = qty;
		this.price = price;
	}
	
	public void setQty(int qty) {
		this.quantity = qty;
	}
	
	public String getPrice() {
		return this.price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getQty() {
		return Integer.toString(this.quantity);
	}
}
