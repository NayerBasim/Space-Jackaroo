package model.player;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Random;

import exception.CannotDiscardException;
import exception.GameException;
import exception.InvalidCardException;
import exception.InvalidMarbleException;
import model.Colour;
import model.card.Card;
import model.card.standard.King;
import model.card.standard.Queen;
import model.card.standard.Ten;

@SuppressWarnings("unused")
public class Player {
    private final String name;
    private final Colour colour;
    private ArrayList<Card> hand;
    private final ArrayList<Marble> marbles;
    private Card selectedCard;
	private final ArrayList<Marble> selectedMarbles;

    public Player(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
        this.hand = new ArrayList<>();
        this.selectedMarbles = new ArrayList<>();
        this.marbles = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            this.marbles.add(new Marble(colour));
        }
        
        //default value
        this.selectedCard = null;
    }

    public String getName() {
        return name;
    }

    public Colour getColour() {
        return colour;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
    
    public ArrayList<Marble> getMarbles() {
		return marbles;
	}
 
    public Card getSelectedCard() { 
        return selectedCard;
    }
    
    public void removeRandomCard() throws CannotDiscardException {
    	if(hand.size()==0){
    		throw new CannotDiscardException();
    	}
    	else{
    		Random rand = new Random();
    		int cardIndex = rand.nextInt(4);
    		hand.remove(cardIndex);
    	}
    	
    }
    
    public void regainMarble(Marble marble){
    	marbles.add(marble);
    }
    
    public Marble getOneMarble() {
    	
    	if(marbles.isEmpty()) {return null;}
    	else { return marbles.get(0); }
    	
    }
    
    public void selectCard(Card card) throws InvalidCardException {
    	
    	if(hand.contains(card)) {selectedCard=card;}
    	else {throw new InvalidCardException();}
    	
    }
    
    public void selectMarble(Marble marble) throws InvalidMarbleException{
    	
    	if(selectedMarbles.size()==2) {throw new InvalidMarbleException();}
    	else{selectedMarbles.add(marble);}
    	
    	
    }
    
    public void deselectAll() {
    	
    	selectedCard=null;
    	selectedMarbles.clear();
    	
    }
    
    public void play() throws GameException{
    	
    	if(selectedCard==null) {throw new InvalidCardException();}
    	else if(!selectedCard.validateMarbleColours(selectedMarbles)){ 
    		throw new InvalidMarbleException("Invalid Colours!");
    	}else if(!selectedCard.validateMarbleSize(selectedMarbles)) {
    		throw new InvalidMarbleException("Invalid Size!");
    	}else {
    		selectedCard.act(selectedMarbles);
    	}
    	
    	
    	
    }

}
