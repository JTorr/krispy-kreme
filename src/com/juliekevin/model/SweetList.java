package com.juliekevin.model;

import java.util.ArrayList;
import java.util.List;

import com.juliekevin.Sweet;

public class SweetList {
	List<Sweet> allSweets = new ArrayList<>();

	public SweetList() {
		this.allSweets.add(new Sweet("caramel", 0, "20.00"));
		this.allSweets.add(new Sweet("donut", 0, "5.00"));
		this.allSweets.add(new Sweet("lollipop", 0, "10.00"));
		this.allSweets.add(new Sweet("icecream", 0, "25.00"));
		this.allSweets.add(new Sweet("chocolate", 0, "30.00"));
		this.allSweets.add(new Sweet("cake", 0, "15.00"));
		this.allSweets.add(new Sweet("gumdrop", 0, "2.00"));
		this.allSweets.add(new Sweet("cookie", 0, "4.00"));
	}
	
	public Sweet findByName(String name) {
		for(Sweet s: allSweets) {
			if(s.getName() == name.toLowerCase()) {
				return s;
			}
		}
		return null;
	}

}
