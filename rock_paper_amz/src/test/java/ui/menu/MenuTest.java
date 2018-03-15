package ui.menu;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class MenuTest {

	Menu menu;

	@Before
	public void setup() {

		Selectable menuItem1 = new Selectable() {

			@Override
			public String getName() {
				return "nameA";
			}

			@Override
			public String getCommand() {
				return "commA";
			}
		};

		Selectable menuItem2 = new Selectable() {

			@Override
			public String getName() {
				return "nameB";
			}

			@Override
			public String getCommand() {
				return "commB";
			}
		};

		menu = new Menu(new ArrayList<Selectable>(Arrays.asList(menuItem1, menuItem2)));
	}

	@Test
	public void testMenuSize() {
		assertEquals(2, menu.getAllItems().size());

	}

	@Test
	public void testGetItemByCommand() {
		assertEquals("nameA", menu.getItemByCommand("commA").getName());

	}

}
