package ui.console;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ConsoleReaderTest {
	//this test is manual
	//test with: 0, 2, 4, "asd", 1 , 3
	
	private static final boolean run = false;
	
	@Test
	void testReadMenu() {
		if(!run) {
			return;
		}
		
		ConsoleReader reader = new ConsoleReader();
		
		ArrayList<Integer> availableCommands = new ArrayList<Integer>();
		availableCommands.add(1);
		availableCommands.add(3);
		
		System.out.print("Choose between 1 or 3: ");
		
		int res = reader.readMenu(availableCommands);
		
		assertTrue(availableCommands.contains(res));

		System.out.print("Alright! Now do it again!");
		System.out.print("Choose between 1 or 3: ");
		
		res = reader.readMenu(availableCommands);
		
		assertTrue(availableCommands.contains(res));
	}
	
	@Test
	void testRead() {
		if(!run) {
			return;
		}
		
		ConsoleReader reader = new ConsoleReader();
		
		String expected = "asd";
		System.out.print("Type in \"" + expected + "\": ");
		String res = reader.read();
		assertEquals(expected, res);
		
		expected = "1 2 3";
		System.out.print("Type in \"" + expected + "\": ");
		res = reader.read();
		assertEquals(expected, res);
		
		expected = " 1 2 3 ";
		System.out.print("Type in \"" + expected + "\": ");
		res = reader.read();
		assertEquals(expected, res);
	}

}
