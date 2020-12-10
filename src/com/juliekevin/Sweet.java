package com.juliekevin;

import java.math.BigDecimal;

public class Sweet {
	String name;
	int quantity;
	BigDecimal price;
	
	public Sweet(String name, int qty, BigDecimal price) {
		this.name = name;
		this.quantity = qty;
		this.price = price;
	}
	
	public void setQty(int qty) {
		this.quantity = qty;
	}
	
	public BigDecimal getPrice() {
		return this.price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getQty() {
		return Integer.toString(this.quantity);
	}
}
