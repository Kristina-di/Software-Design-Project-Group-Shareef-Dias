/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.uno;

import java.util.Random;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class UnoDeck
{
  private Cards[] cards;
  private int cardsInDeck;

    /**
     *
     */
    public UnoDeck()
  {
    //initializes the cards array
    cards = new Cards[108];
    reset();
  }

    /**
     * pre-condition: none
     * post-condition: initializes the deck of Uno cards
     * activity: resets the deck
     */
    public void reset() {
    //creates an array of colors to hold the values of the enum Color
    Cards.Color[] colors = Cards.Color.values();
    //index of the cards array              
    cardsInDeck = 0;
    //traversing the array of colors. minus one because we are excluding the Wild Color
    for (int i = 0; i < colors.length-1; i++)
    {
      //the card color will be the current index of the card array
      Cards.Color color = colors[i];

      // Add 1 zero
      cards[cardsInDeck++] = new Cards(color, Cards.Value.getValue(0));
      // Add 2 cards for 1-9
      for (int j = 1; j < 10; j++)
      {
        cards[cardsInDeck++] = new Cards(color, Cards.Value.getValue(j));
        cards[cardsInDeck++] = new Cards(color, Cards.Value.getValue(j));
      }
      // Add Draw Two, Skip, and Reverse
      Cards.Value[] values = new Cards.Value[]{Cards.Value.DrawTwo, Cards.Value.Skip, Cards.Value.Revese};
      for (Cards.Value value : values)
      {
        cards[cardsInDeck++] = new Cards(color, value);
        cards[cardsInDeck++] = new Cards(color, value);
      }
    }

    // Add Wild Cards: Wild Wild and Wild DrawFour
    Cards.Value[] values = new Cards.Value[]{Cards.Value.Wild, Cards.Value.Wild_Four};
    for (Cards.Value value : values)
    {
      for (int i = 0; i < 4; i++)
      {
        cards[cardsInDeck++] = new Cards(Cards.Color.Wild, value);
      }
    }
  }


    /**
     *
     * @param cards (stockpile)
     * replaces the deck with an arraylist of UnoCards (the stockpile)
     */
    public void replaceDeckWith(ArrayList<Cards> cards) {
      this.cards = cards.toArray(new Cards[cards.size()]);
      this.cardsInDeck = this.cards.length;
    }

    /**
     *
     * @return true if there are no cards in the deck
     */
    public boolean isEmpty() {
      return cardsInDeck == 0;
    }

    public void shuffle() {
      int n = cards.length;
      Random random = new Random();

      for (int i = 0; i < cards.length; i++) {

        // Get a random index of the array past the current index
        // ... The argument is an exclusive bound
        // Swap the random element with the present element

        int randomValue = i + random.nextInt(n - i);
        Cards randomCard = cards[randomValue];
        cards[randomValue] = cards[i];
        cards[i] = randomCard;
      }

    }

    public Cards drawCard() throws IllegalArgumentException {
      if (isEmpty()) {
        throw new IllegalArgumentException("Cannot draw a card since there are no cards in the deck");
      }
      return cards[--cardsInDeck];
    }

    public ImageIcon drawCardImage() throws IllegalArgumentException {
      if(isEmpty()) {
        throw new IllegalArgumentException("Cannot draw a card since the deck is empty");
      }
      return new ImageIcon(cards[--cardsInDeck].toString() + ".png");
    }

    public Cards[] drawCard(int n) {
      if (n < 0) {
        throw new IllegalArgumentException("Must draw positiive cards but tried to draw " + n + " cards.");
      }

      if (n > cardsInDeck) {
        throw new IllegalArgumentException("Cannot draw " + n + " cards since there are only " + cardsInDeck + " cards.");
      }

      Cards[] ret = new Cards[n];

      for (int i = 0; i < n; i++) {
        ret[i] = cards[--cardsInDeck];
      }
      return ret;
    }
}
 
