package com.juliekevin.model;

import java.util.ArrayList;
import java.util.List;

import com.juliekevin.Sweet;

import utils.GameUtils;

public class SweetList {
	List<Sweet> allSweets = new ArrayList<>();

	public SweetList() {
		this.allSweets.add(new Sweet("caramel","20.00"));
		this.allSweets.add(new Sweet("donut", "5.00"));
		this.allSweets.add(new Sweet("lollipop", "10.00"));
		this.allSweets.add(new Sweet("icecream", "25.00"));
		this.allSweets.add(new Sweet("chocolate", "30.00"));
		this.allSweets.add(new Sweet("cake", "15.00"));
		this.allSweets.add(new Sweet("gumdrop", "2.00"));
		this.allSweets.add(new Sweet("cookie", "4.00"));
	}
	
	public Sweet findByName(String name) {
		for(Sweet s: allSweets) {
			if(s.getName() == name.toLowerCase()) {
				return s;
			}
		}
		return null;
	}

	public List<Sweet> selectRandom(int qty) {
		List<Sweet> available = new ArrayList<>(allSweets);
		List<Sweet> sweetList = new ArrayList<>();
		for(int i=0; i < qty; i++) {
			int rand = GameUtils.getRand(0, available.size() - 1);
//			int randQty = GameUtils.getRand(1, 100);
			Sweet selected = available.get(rand);
//			selected.setQty(randQty);
			sweetList.add(selected);
			available.remove(rand);
		}
		return sweetList;
	}

}
