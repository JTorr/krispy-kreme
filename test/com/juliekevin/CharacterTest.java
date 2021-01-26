package com.juliekevin;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import utils.GameUtils;


class CharacterTest {

	Character player = new Character("Player", "Default area");
	String defaultStash = "Your inventory currently contains: caramel: 0\ndonut: 0\nYou have $1000.00.";

	@Test
	void getInventory() {
		try (MockedStatic<GameUtils> theMock = Mockito.mockStatic(GameUtils.class)) {
		    theMock.when(GameUtils::getRandName).thenReturn("Rafael");
		    assertTrue(player.getInventory().contains("Your inventory"));
		  }
	}
}
