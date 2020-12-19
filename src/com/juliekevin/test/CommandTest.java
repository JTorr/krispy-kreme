package com.juliekevin.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.juliekevin.model.Command;

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
		assertEquals(true, goNaperville.equals(new Command("go", "Naperville")));
		assertEquals(true, goNaperville.equals(goNaperville));
		assertEquals(false, goNaperville.equals(new Command("go", "Jupiter")));
		assertEquals(false, goNaperville.equals(new Command("sell", "Naperville")));
		assertEquals(false, goNaperville.equals(null));
		assertEquals(false, goNaperville.equals(new Object()));
	}
	
	@Test
	void getHashCode() {
		assertEquals(-1226717439, goNaperville.hashCode());
		assertEquals(1785927141, inventory.hashCode());
	}
}
