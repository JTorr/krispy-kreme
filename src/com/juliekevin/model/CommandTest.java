package com.juliekevin.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommandTest {

	private final Command goNaperville = new Command("go", "Naperville");
	
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
	}
}
