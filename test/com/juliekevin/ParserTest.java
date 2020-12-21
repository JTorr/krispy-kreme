package com.juliekevin;

import com.juliekevin.model.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ParserTest {
	Parser p = new Parser();

	@Test
	void parseSingleString() throws Exception {
		Command cmd = p.parse("inventory");
		assertEquals(1785927141, cmd.hashCode());
	}
	
	@Test
	void parseVerbNoun() throws Exception {
		Command cmd = p.parse("go Naperville   ");
		assertEquals(-1226717439, cmd.hashCode());
	}
}
