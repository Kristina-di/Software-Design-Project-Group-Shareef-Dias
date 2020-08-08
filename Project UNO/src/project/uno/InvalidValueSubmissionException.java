/*
 * Student is Shareef Aldahhan
 * Student ID is 991598634
 */
package project.uno;

import javax.swing.JLabel;

public class InvalidValueSubmissionException extends Exception {
      private Cards.Value expected;
    private Cards.Value actual;
    
    public InvalidValueSubmissionException (String message,Cards.Value actual,Cards.Value expected){
        this.actual = actual;
        this.expected = expected;
    }

    InvalidValueSubmissionException(JLabel message2, Cards.Value value, Cards.Value validValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
