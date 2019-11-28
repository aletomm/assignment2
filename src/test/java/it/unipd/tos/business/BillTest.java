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
	public void testOrdineInferioreDi10Euro() throws TakeAwayBillException{
		
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoVegetariano", 8.5));
		assertEquals(9, conto.getOrderPrice(ordini), 0.0);
		//Aggiungo 0.5 al totale di 8.5 dell'ordine -> 8.5 + 0.5 = 9
	}
	//ISSUE 2
	@Test
	public void testOrdinePiuDi5Panini() throws TakeAwayBillException{
		
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
	public void testOrdinePiuDi50Euro() throws TakeAwayBillException{
		
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoVegetariano", 8.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoConCarne", 25.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "Toast", 24.0));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Aranciata", 7.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "AcquaDiCracco", 8.0));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Vino", 15.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Cola", 4.0));
		
		assertEquals(83.7, conto.getOrderPrice(ordini), 0.0);
		//Sconto del 10% sull'ordine -> 93 - (0.1*93) = 83.7
	}

	
    @org.junit.Rule
    public ExpectedException error = ExpectedException.none();
    
    //ISSUE 4
    @Test
    public void testNonPiuDi30Elementi() throws TakeAwayBillException
    {
        error.expect(TakeAwayBillException.class);
        error.expectMessage("Hai inserito troppe ordinazioni!");
        
        for(int i=0;i<31;i++)
        {
            ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Cola", 3.5));
        }
        
        conto.getOrderPrice(ordini);
        //Aggiungo piu' di 30 elementi all'ordine ed ottengo un'eccezione
        
    }
    
    //ISSUE 1 -> Comprende test con input misti o errati
    
    @Test
    public void testOrdinePiuDi50EuroPaniniEFrittiConPiuDi5Panini() throws TakeAwayBillException{
    	ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoVegetariano", 8.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Fritti, "FrittoMistico", 5.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "Toast", 4.0));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Aranciata", 7.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "AcquaDiCracco", 8.0));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "SuperPanino", 15.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoAllInvariante", 12.0));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoAnalisi", 12.0));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoLogica", 6.0));
		
		assertEquals(69.3, conto.getOrderPrice(ordini), 0.0);
    	//Applico lo sconto al panino meno costoso (-2 Euro) e poi sconto del 10%
		// -> 79 - 2 - 7.7 = 69.3
    }
    
    @Test
    public void testOrdinePiuDi50SoloBibite() throws TakeAwayBillException{
    	
    	for(int i=0;i<15;i++)
        {
            ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "AcquaDiCracco", 8.0));
        }
		
		assertEquals(120, conto.getOrderPrice(ordini), 0.0);
    	//Non applico nessuno sconto per l'importo superiore a 50 Euro
    }
    
    @Test
    public void testOrdinePiuDi50EuroPaniniEFrittiConMenoDi5Panini() throws TakeAwayBillException{
    	ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoVegetariano", 48.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Fritti, "FrittoMistico", 5.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "Toast", 4.0));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Aranciata", 7.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "AcquaDiCracco", 8.0));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "SuperPanino", 15.5));
		
		
		assertEquals(80.1, conto.getOrderPrice(ordini), 0.00001);
    	//Applico lo sconto del 10%
		// -> 89 - 8.9 = 80.1
    }
    
    @Test
    public void testOrdineVuoto() throws TakeAwayBillException{
    	
		assertEquals(0.0, conto.getOrderPrice(ordini), 0.0);

    }//Se effettuo un ordine senza nulla devo pagare 0 Euro
    
}
