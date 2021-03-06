////////////////////////////////////////////////////////////////////
// [Alessandro] [Tommasin] [1189293]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.Iterator;
import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;

public class Bill implements TakeAwayBill {
    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException{
        int paniniCounter = 0;
        double billPaniniAndFritti = 0.0;
        double billBibite = 0.0;
        double cheapestPanino = Double.MAX_VALUE ;
        //E' stata inserita una lista nulla 
        if(itemsOrdered == null) {
            throw new TakeAwayBillException("Hai inserito una lista nulla!");
        }
        //E' stata inserita una lista senza elementi
        if(itemsOrdered.isEmpty()) {
             return 0.0;
        }
        //E' stata inserita una lista con più di 30 elementi
        if(itemsOrdered.size()>30) {
            throw new TakeAwayBillException("Hai inserito troppe ordinazioni!");
        }
        //Calcolo il totale 
        Iterator<MenuItem> iter = itemsOrdered.iterator();
        while (iter.hasNext()) {

            MenuItem item = iter.next();

            if(item.getItemType()=="Bevande") {
 
            billBibite = billBibite + item.getPrice();

        } else if (item.getItemType()=="Panini") {

            if(item.getPrice()<cheapestPanino) { 

                cheapestPanino = item.getPrice(); 
            }

            billPaniniAndFritti = billPaniniAndFritti + item.getPrice();
            paniniCounter++;

        } else {

            billPaniniAndFritti = billPaniniAndFritti + item.getPrice();

           }
        }

    double finalBill = billPaniniAndFritti + billBibite;
    //Applico gli sconti
    if(paniniCounter > 5) {

        finalBill = finalBill - cheapestPanino*0.5;

    }

    if(billPaniniAndFritti > 50 ) {
        return finalBill*0.9;
    }

    if (finalBill < 10) {
        return finalBill + 0.50;
    }

    return finalBill;

   }
}