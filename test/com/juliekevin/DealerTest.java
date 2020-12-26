package com.juliekevin;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.juliekevin.model.Dealer;

class DealerTest {
	Sweet donut = new Sweet("donut", 5, "10.00");
	Sweet lolly = new Sweet("lolly", 10, "5.00");
	
	List<Sweet> wares = new ArrayList<Sweet>();
	

	@Test
	void getInventory() {
		wares.add(donut);
		wares.add(lolly);
		Dealer dealer = new Dealer("Mike", wares);
		
		assertEquals(wares, dealer.getInventory());
	}

}
