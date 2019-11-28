package it.unipd.tos.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;


public class RestaurantBillTest {
	
	List<MenuItem> ordini;
	RestaurantBill conto;
	
	
	@Before
	public void before() {
		ordini = new ArrayList<>();
		conto = new RestaurantBill();
	}
	
	
	@Test
	public void testOrdineInferioreDi10Euro() {
		
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoVegetariano", 8.5));
		assertEquals(9, conto.getOrderPrice(ordini), 0.0);
	
	}
	
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
	}
	
	@Test
	public void testOrdinePiuDi50Euro() {
		
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoVegetariano", 8.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "PaninoConCarne", 5.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Panini, "Toast", 4.0));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Aranciata", 7.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Acqua", 8.0));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Vino", 15.5));
		ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Cola", 4.0));
		
		assertEquals(53-5.3, conto.getOrderPrice(ordini), 0.0);
	}

	
    @org.junit.Rule
    public ExpectedException error= ExpectedException.none();
    
    @Test
    public void testNonPiuDi30Elementi() throws RestaurantBillException
    {
        error.expect(RestaurantBillException.class);
        
        for(int i=0;i<31;i++)
        {
            ordini.add(new MenuItem(MenuItem.Prodotti.Bevande, "Cola", 3.5));
        }
        
        conto.getOrderPrice(ordini);
        
    }

}
