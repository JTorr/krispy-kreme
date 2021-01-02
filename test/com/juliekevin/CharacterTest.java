package com.juliekevin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CharacterTest {

	Character player = new Character("Player", "Default area");
	String defaultStash = "Your inventory currently contains: caramel: 0\ndonut: 0\nYou have $1000.00.";

	@Test
	void getInventory() {
		assertTrue(player.getInventory().contains("Your inventory"));
	}
}
