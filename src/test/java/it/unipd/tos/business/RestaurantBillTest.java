package it.unipd.tos.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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


}
