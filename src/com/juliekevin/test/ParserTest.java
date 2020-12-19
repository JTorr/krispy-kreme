package com.juliekevin.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.juliekevin.Parser;
import com.juliekevin.model.Command;

class ParserTest {
	

	@Test
	void parseSingleString() {
		Command cmd = Parser.parse("inventory");
		assertEquals(1785927141, cmd.hashCode());
	}
	
	@Test
	void parseVerbNoun() {
		Command cmd = Parser.parse("go Naperville   ");
		assertEquals(-1226717439, cmd.hashCode());
	}
}
