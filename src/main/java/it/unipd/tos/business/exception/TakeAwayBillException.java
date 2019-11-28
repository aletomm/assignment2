////////////////////////////////////////////////////////////////////
// [Alessandro] [Tommasin] [1189293]
////////////////////////////////////////////////////////////////////


package it.unipd.tos.business.exception;


import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class TakeAwayBillException extends RuntimeException {

    private static final long serialVersionUID = -2265154439188606779L;

    public TakeAwayBillException() {
        
        JOptionPane.showMessageDialog(new JFrame(), "Hai inserito troppe ordinazioni!");
    }

}
