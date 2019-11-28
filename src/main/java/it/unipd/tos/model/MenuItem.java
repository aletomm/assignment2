////////////////////////////////////////////////////////////////////
// [Alessandro] [Tommasin] [1189293]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

public class MenuItem {
    enum Prodotti{Panini, Fritti, Bevande}
    private Prodotti itemType;
    private String name;
    private double price;

    MenuItem(Prodotti prod, String nome, Double prezzo) {

        itemType = prod;
        name = nome;
        price = prezzo;

    }

    public String getItemType() { return itemType.toString(); }
    
    public String getName() { return name; }

    public double getPrice() { return price;}
}
