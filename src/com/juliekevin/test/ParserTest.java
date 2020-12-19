package com.juliekevin.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.juliekevin.Parser;
import com.juliekevin.model.Command;

class ParserTest {
	
	private final Parser parser = new Parser();

	@Test
	void parseSingleString() {
		Command cmd = parser.parse("inventory");
		assertEquals(1785927141, cmd.hashCode());
	}
	
	@Test
	void parseVerbNoun() {
		Command cmd = parser.parse("go Naperville   ");
		assertEquals(-1226717439, cmd.hashCode());
	}
}
