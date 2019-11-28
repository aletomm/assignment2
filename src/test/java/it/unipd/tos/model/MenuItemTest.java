package it.unipd.tos.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class MenuItemTest {

	@Test
	public void testPrezziNegativi() { 
	
		MenuItem i = new MenuItem(MenuItem.Prodotti.Panini, "PaninoDepresso", -12.2);
		
		assertEquals(12.2, i.getPrice(), 0.0);
		
	}
	//Se il prezzo e' negativo allora  ne viene cambiato di segno (si assume un errore di battitura)
}
