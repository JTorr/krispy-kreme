package com.juliekevin.model;

import java.util.ArrayList;
import java.util.List;

import com.juliekevin.Sweet;

public class Dealer {
	List<Sweet> wares = new ArrayList<>();
	String name;

	public Dealer(String name, List<Sweet> wares) {
		this.name = name;
		this.wares = wares;
	}
	
	public List<Sweet> getInventory() {
		return this.wares;
	}
}
