package it.unipd.tos.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;


public class BillTest {
	
	List<MenuItem> ordini;
	Bill conto;
	
	
	@Before
	public void before() {
		ordini = new ArrayList<>();
		conto = new Bill();
	}
	
	//ISSUE 5
	@Test
	public void testOrdineInferioreDi10Euro() {
		
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoVegetariano", 8.5));
		assertEquals(9, conto.getOrderPrice(ordini), 0.0);
		//Aggiungo 0.5 al totale di 8.5 dell'ordine -> 8.5 + 0.5 = 9
	}
	//ISSUE 2
	@Test
	public void testOrdinePiuDi5Panini() {
		
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoVegetariano", 8.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoConCarne", 5.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "Toast", 4.0));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoSalame", 7.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoCipolle", 8.0));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoSalsiccia", 6.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Cola", 4.0));
		
		assertEquals(42, conto.getOrderPrice(ordini), 0.0);
		//Sconto del 50% sul panino meno costoso -> 44 - 0.4*4 = 42
	}
	//ISSUE 3
	@Test
	public void testOrdinePiuDi50Euro() {
		
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoVegetariano", 8.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoConCarne", 5.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "Toast", 4.0));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Aranciata", 7.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Acqua", 8.0));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Vino", 15.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Cola", 4.0));
		
		assertEquals(47.7, conto.getOrderPrice(ordini), 0.0);
		//Sconto del 10% sull'ordine -> 53 - (0.1*53) = 47.7
	}

	
    @org.junit.Rule
    public ExpectedException error= ExpectedException.none();
    
    //ISSUE 4
    @Test
    public void testNonPiuDi30Elementi() throws TakeAwayBillException
    {
        error.expect(TakeAwayBillException.class);
        
        for(int i=0;i<31;i++)
        {
            ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Cola", 3.5));
        }
        
        conto.getOrderPrice(ordini);
        //Aggiungo piu' di 30 elementi all'ordine ed ottenfo un'eccezione
        
    }
    
    //ISSUE 1 -> Comprende test con input misti o errati

}
