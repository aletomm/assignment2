////////////////////////////////////////////////////////////////////
// [Alessandro] [Tommasin] [1189293]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.Iterator;
import java.util.List;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;

public class RestaurantBill implements TakeAwayBill {
    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered) throws RestaurantBillException{
        int paniniCounter = 0;
        double billPaniniAndFritti = 0.0;
        double billBibite = 0.0;
        double cheapestPanino = Double.MAX_VALUE ;
        
        if(itemsOrdered.size()>30) {
            throw new RestaurantBillException();
        }

        Iterator<MenuItem> iter = itemsOrdered.iterator();
        while (iter.hasNext()) {

            MenuItem item = iter.next();

            if(item.getItemType()=="Bibite") {

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