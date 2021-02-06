package com.juliekevin;

import com.juliekevin.model.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ParserTest {
	Parser p = new Parser();

	@Test
	void parseSingleString() throws Exception {
		Command cmd = p.parse("inventory");
		assertEquals(1785927141, cmd.hashCode());
	}
	
	@Test
	void parseInvalidVerb() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			p.parse("eat 5 donut");
		});
		
		String expectedMsg = "Invalid command. Verb not recognized.";
		String actualMsg = exception.getMessage();
		System.out.println(actualMsg);
		assertTrue(actualMsg.contains(expectedMsg));
	}
	
	@Test
	void parseMoveCommand() throws Exception {
		Command cmd = p.parse("go Naperville   ");
		assertEquals(-1226717439, cmd.hashCode());
	}
	
	@Test
	void parseIncompleteMoveCommand() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			p.parse("go");
		});
		
		String expectedMsg = "Move commands must include location.";
		String actualMsg = exception.getMessage();
		assertTrue(actualMsg.contains(expectedMsg));
	}
	
	@Test
	void parseBuyCommand() throws Exception {
		Command cmd = p.parse("buy 5 donut");
		assertEquals(98805021, cmd.hashCode());
	}
	
	@Test
	void parseSellCommand() throws Exception {
		Command cmd = p.parse("sell donut 5");
		assertEquals(205090257, cmd.hashCode());
	}
	
	@Test
	void parseIncompleteSaleCommand() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			p.parse("buy donut");
		});
		
		String expectedMsg = "Buy/sell/loan commands must include item and quantity.";
		String actualMsg = exception.getMessage();
		assertTrue(actualMsg.contains(expectedMsg));
	}
	
	@Test
	void parseInvalidSaleCommand() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> {
			p.parse("buy five donut");
		});
		
		String expectedMsg = "Re-enter command with quantity.";
		String actualMsg = exception.getMessage();
		System.out.println(actualMsg);
		assertTrue(actualMsg.contains(expectedMsg));
	}
}
