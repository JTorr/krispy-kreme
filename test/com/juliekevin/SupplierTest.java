package com.juliekevin;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.juliekevin.model.Supplier;

class SupplierTest {
	Sweet donut = new Sweet("donut", "10.00");
	Sweet lolly = new Sweet("lolly", "5.00");
	
	List<Sweet> wares = new ArrayList<Sweet>();
	

	@Test
	void getInventory() {
		wares.add(donut);
		wares.add(lolly);
		Supplier supplier = new Supplier("Mike", wares, "1.0");
		
		assertTrue(supplier.getInventory().containsItem("donut"));
	}

}
