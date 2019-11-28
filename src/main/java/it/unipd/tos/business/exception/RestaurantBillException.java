////////////////////////////////////////////////////////////////////
// [Alessandro] [Tommasin] [1189293]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RestaurantBillException extends TakeAwayBillException {
    private static final long serialVersionUID = 478997781609191804L;
    public RestaurantBillException() {
        
        JOptionPane.showMessageDialog(new JFrame(), "Hai inserito troppe ordinazioni!");
    }
}
