////////////////////////////////////////////////////////////////////
// [Alessandro] [Tommasin] [1189293]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import it.unipd.tos.business.exception.TakeAwayBillException;

public class MenuItem {
   public enum Prodotti{Panini, Fritti, Bevande}
    private Prodotti itemType;
    private String name;
    private double price;

    public MenuItem(Prodotti prod, String nome, Double prezzo) throws TakeAwayBillException  {

        itemType = prod;
        name = nome;
        if(prezzo < 0) {
            throw new TakeAwayBillException("Hai inserito un prezzo negativo!");
        }else {
             price = prezzo;
        }
    }

    public String getItemType() { return itemType.toString(); }

    public double getPrice() { return price;}
}
