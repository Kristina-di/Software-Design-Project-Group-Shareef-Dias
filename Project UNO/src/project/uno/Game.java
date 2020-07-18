/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.uno;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

/**
 *
 * @author share
 */
public class Game {
    
    private int currentUser;
    private String[] playerIDs;
    
    private Deck deck;
    private ArrayList <ArrayList<Cards>> playerHand;
    private ArrayList<Cards> stockPile;
    
    private Cards.Color validColor;
    private Cards.Value validValue;
    
    boolean gameD;
    
    public Game(String[] pids) {
        deck = new Deck();
        deck.shuffle();
        stockPile = new ArrayList<Cards>();
        
        playerIDs = pids;
        currentUser = 0;
        gameD = false;
        
        playerHand = new ArrayList<ArrayList<Cards>>();
        
        for (int i = 0; i < pids.length; i++) {
            //we created a hand and filed it with Deck Class and draw 7 cards
            ArrayList<Cards> hand = new ArrayList<Cards>(Arrays.asList(deck.drawcard(7)));
            playerHand.add(hand);            
        }
    }
    
    public void start (Game game){
        Cards card = deck.drawCard();
        validColor = card.getColor();
        validValue = card.getValue();
        
        if(card.getValue() == Cards.Value.Wild){
            start(game);
        }
        
        if (card.getValue() == Cards.Value.WildFour || card.getValue() == Cards.Value.DrawTwo){
            start(game);
        }
        if (card.getValue() == Cards.Value.Six){
            JLabel message = new JLabel(playerIDs[currentUser] = "was Skipped!");
            message.setFont(new Font("Arial",Font.BOLD, 48));
            JOptionPane.showMessageDialog(null,message);
            // if it false we will add one to te lenght
            // it will go throw the list of player and go to the right player
            if (gameD == false){
                currentUser = (currentUser + 1) % playerIDs.length;
            }
            // if its ture it will take one from the length
            else if (gameD == true){
                currentUser = (currentUser - 1) % playerIDs.length;
                if (currentUser == -1){
                    currentUser = playerIDs.length - 1;
                }
            }
        }
        if (card.getValue()== Cards.Value.Revese){
             JLabel message = new JLabel(playerIDs[currentUser] = "the Game direction has changed!");
            message.setFont(new Font("Arial",Font.BOLD, 48));
            JOptionPane.showMessageDialog(null,message);
            gameD ^= true;
            currentUser = playerIDs.length - 1;
        }
        stockPile.add(card);
    }
    public Cards getTopCard(){
        return new Cards(validColor, validValue);
    }
    public ImageIcon getTopCardImge(){
        return new ImageIcon(validColor + "-"+ validValue + ".png");
    }
    public boolean isGameOver(){
        for (String player : this.playerIDs){
            if (hasEmptyHand(player)){
                return true;
            }
        }
        return false;
    }
    //this method will return the current player
    public String getCurrentPlayer(){
        return this.playerIDs[this.currentUser];
    }
    public String getPreviousPlayuer(int i){
        int index = this.currentUser - i;
        if (index == -1){
            index = playerIDs.length -1;
        }
        //this will find the Prev player
        return this.playerIDs[index];
    }
    public String[] getplayer(String a){
        return playerIDs;
    }
    public ArrayList<Cards> getPlayerHand(String pid){
        int index = Arrays.asList(playerIDs).indexOf(pid);
        return playerHand.get(index);
    }
    
    //find size of player hand
    public int getPlayerHandSize(String pid){
        return getPlayerHand(pid).size();
    }
    
    public Cards getPlayerCard(String pid, int choice){
        ArrayList<Cards> hand = getPlayerHand(pid);
        return hand.get(choice);
    }
    
    public boolean hasEmptyHand (String pid){
        return getPlayerHand(pid).isEmpty();
    }
    
    public boolean validCardPlay(Cards card){
        return card.getColor() == validColor || card.getValue() == validValue;
    }
    
    public void checkPlayerTurn(String pid) throws InvalidPlayerTrunException {
        if (this.playerIDs[this.currentUser] != pid){
            throw new InvalidPlayerTrunException("It's not " + pid + " 's turn",pid);
        }
    }
        
        public void SubmitDraw(String pid) throws InvalidPlayerTrunException{
            checkPlayerTurn(pid);
            if (deck.isEmpty()){
                deck.replaceD(stockPile);
                deck.shuffle();
            }
            getPlayerHand(pid).add(deck.drawCard());
            if(gameD == false){
                currentUser = (currentUser + 1) % playerIDs.length;
            }
            else if (gameD == true){
                currentUser = (currentUser -1) % playerIDs.length;
                if (currentUser == -1){
                    currentUser = playerIDs.length -1 ;
                }
            }
        }
        public void setCardColor(Cards.Color color){
            validColor = color;
        }
    }
