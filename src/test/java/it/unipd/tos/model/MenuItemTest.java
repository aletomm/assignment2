package it.unipd.tos.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import it.unipd.tos.business.exception.TakeAwayBillException;

public class MenuItemTest {

	@org.junit.Rule
    public ExpectedException error = ExpectedException.none();
	
	@Test
	public void testPrezziNegativi() throws TakeAwayBillException { 
	
		error.expect(TakeAwayBillException.class);
        error.expectMessage("Hai inserito un prezzo negativo!");
		
		MenuItem i = new MenuItem(MenuItem.Prodotti.Panini, "PaninoDepresso", -12.2);
		
		
		
	}
	//Se il prezzo e' negativo lancio un'eccezione
}
