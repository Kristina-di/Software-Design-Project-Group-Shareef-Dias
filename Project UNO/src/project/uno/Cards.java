/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.uno;


public class Cards
{

    static Cards[] toArray(Cards[] cards) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public enum Color
    {
        Red,Blue,Green,Yellow,Wild;
        
        private static final Color[] color = Color.values();
        public static Color getColor(int i)
        {
            return Color.color[i];
        }
    }
    public enum Value
    {
        Zero,One,Two,Three,Four,Five,Six,Seve,Eight,Neine,Ten,DrawTwo,Skip,Revese,Wild,Wild_Four;
        private static final Value[] values = Value.values();
        public static Value getValue(int i)
        {
            return Value.values[i];
        }
    }    
    
    private final Color color;
    private final Value value;
    
    public Cards (final Color color, final Value value)
    {
        this.color=color;
        this.value=value;
    }
    public Color getColor()
    {
        return this.color;
    }
    public Value getValue()
    {
        return this.value;
    }
    public String toString()
    {
        return color+ " " + value;
    }
}
