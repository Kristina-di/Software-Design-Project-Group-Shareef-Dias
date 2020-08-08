/*
 * Student is Shareef Aldahhan
 * Student ID is 991598634
 */
package project.uno;

import javax.swing.JLabel;

public class InvalidColorSubmissionException extends Exception{
    private Cards.Color expected;
    private Cards.Color actual;
    
    public InvalidColorSubmissionException (String message,Cards.Color actual,Cards.Color expected){
        this.actual = actual;
        this.expected = expected;
    }

    InvalidColorSubmissionException(JLabel message, Cards.Color color, Cards.Color validColor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
