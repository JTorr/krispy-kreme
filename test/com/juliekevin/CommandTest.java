package com.juliekevin;

import com.juliekevin.model.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

	private final Command goNaperville = new Command("go", "Naperville");
	private final Command inventory = new Command("inventory");
	
	@Test
	void getVerb() {
		assertEquals("go", goNaperville.getVerb());
	}
	
	@Test
	void getNoun() {
		assertEquals("Naperville", goNaperville.getNoun());
	}

	@Test
	void equals() {
		assertTrue(goNaperville.equals(new Command("go", "Naperville")));
		assertTrue(goNaperville.equals(goNaperville));
		assertFalse(goNaperville.equals(new Command("go", "Jupiter")));
		assertFalse(goNaperville.equals(new Command("sell", "Naperville")));
		assertFalse(goNaperville.equals(null));
		assertFalse(goNaperville.equals(new Object()));
	}
	
	@Test
	void getHashCode() {
		assertEquals(-1226717439, goNaperville.hashCode());
		assertEquals(1785927141, inventory.hashCode());
	}
}
