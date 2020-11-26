package com.juliekevin;

public class Drug {
	String name;
	int quantity;
	double price;
	
	public Drug(String name, int qty, double price) {
		this.name = name;
		this.quantity = qty;
		this.price = price;
	}
	
	public void setQty(int qty) {
		this.quantity = qty;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getQty() {
		return Integer.toString(this.quantity);
	}
}
