/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.uno;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author share
 */
public class Deck 
{
    private Cards[] cards;
    private int Cardsnum;
    
    public Deck()
    {
        cards = new Cards[100];
    }
    public void reset()
    {
        Cards.Color[] colors = Cards.Color.values();    
        Cardsnum = 0;
        for (int i = 0; i < colors.length-1; i++) 
        {
            Cards.Color color = colors[i];
            cards[Cardsnum++] = new Cards(color , Cards.Value.getValue(0));
            
            for (int j = 0; j < 10; j++) {
                cards[Cardsnum++] = new Cards(color , Cards.Value.getValue(j));
                cards[Cardsnum++] = new Cards(color , Cards.Value.getValue(j));
            }
            Cards.Value[] values = new Cards.Value[]{ Cards.Value.DrawTwo,Cards.Value.Skip,Cards.Value.Revese};
            for(Cards.Value value : values)
            {
                cards[Cardsnum++] = new Cards(color , value);
                cards[Cardsnum++] = new Cards(color , value);
            }         
        }
        Cards.Value[] values = new Cards.Value[]{ Cards.Value.Wild,Cards.Value.WildFour};
        for(Cards.Value value : values)
            {
               for (int i = 0; i < 4; i++)
            {
               cards[Cardsnum++] = new Cards(Cards.Color.Wild, value);
            }
            
        } 
    }
    public void replaceD(ArrayList <Cards> cards){
        this.cards = Cards.toArray(new Cards[(cards.size())]);
        this.Cardsnum = this.cards.length;
    }
    public boolean isEmpty()
    {
        return Cardsnum == 0;
    }
    public void shuffle()
    {
        int n = cards.length;
        Random random = new Random();
        for (int i = 0; i < cards.length; i++) 
        {
            int randomValue = i + random.nextInt(n - 1);
            Cards randomCard = cards[randomValue];
            cards[randomValue] = cards[i];
            cards[i] = randomCard;
        }
    }

    /**
     *
     * @return
     */
    public Cards drawCard() {
        if (isEmpty())
        {
            throw new IllegalArgumentException("Can't draw a card, the deck is empty"); 
        }
            return cards[--Cardsnum];
    }  
    int av;
    public Cards[] drawcard(int a)
    {
        if (a < 0)
        {
            throw new IllegalArgumentException("Must draw positive, you draw "+a+"Cards");   
        }
        if (a > Cardsnum)
        {
            throw new IllegalArgumentException("Can't draw " + a +"the deck only have " + Cardsnum + "Cards");
        }
        Cards[] ret = new Cards[a];
        for (int i = 0; i < a; i++) 
        {
           ret[i] = cards[--Cardsnum];
        }
        return ret;
    }
}    
