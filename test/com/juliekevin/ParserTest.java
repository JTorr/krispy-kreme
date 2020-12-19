package com.juliekevin;

import com.juliekevin.model.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
