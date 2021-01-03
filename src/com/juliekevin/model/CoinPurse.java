package com.juliekevin.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CoinPurse {
	String money;
	
	public CoinPurse() {
		this.money = "1000.00";
	}
	
	public void earnMoney(String amount) {
		this.money = new BigDecimal(this.money).add(new BigDecimal(amount)).toString();
	}
	
	public void spendMoney(String amount) throws Exception {
		BigDecimal current = new BigDecimal(this.money);
		BigDecimal amt = new BigDecimal(amount);
		if(current.compareTo(amt) >= 0) {
			this.money = current.subtract(amt).toString();
		} else {
			throw new Exception("Purchase price greater than available funds.");
		}
	}
	
	public static String getLocalPrice(String price, String priceMod) {
		return new BigDecimal(price).multiply(new BigDecimal(priceMod)).setScale(2, RoundingMode.HALF_UP).toString();
	}

	public static String getTotalPrice(String price, int quantity) {
		return new BigDecimal(price).multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP).toString();
	}
	
	public String getMoney() {
		return this.money;
	}
	
	public void deductFine(String amount) {
		BigDecimal current = new BigDecimal(this.money);
		BigDecimal amt = new BigDecimal(amount);
		this.money = current.subtract(amt).toString();
	}
}
