package com.juliekevin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CharacterTest {

	Character player = new Character("Player", "Default location");
	String defaultStash = "Your inventory currently contains: caramel: 0\ndonut: 0\nYou have $1000.00.";
	
	@Test
	void getLocation() {
		assertEquals("Default location", player.getLocation());
	}

	@Test
	void getInventory() {
		assertTrue(player.getInventory().contains("Your inventory"));
	}
	
	@Test
	void setLocation() {
		Character newPlayer = new Character("New", "Default location");
		newPlayer.setLocation("Brand new location!");
		assertEquals("Brand new location!", newPlayer.getLocation());
	}
}
