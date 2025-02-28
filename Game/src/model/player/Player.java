package model.player;

import model.Colour;
import model.card.Card;
import java.util.ArrayList;


public class Player {
	
	
	private final String name;
	private final Colour colour;
	private ArrayList<Card> hand;
	private final ArrayList<Marble> marbles;
	private Card selectedCard;
	private final ArrayList<Marble> selectedMarbles;
	
	
	
	public Player(String name, Colour colour) {
		this.name=name;
		this.colour=colour;
		hand=new ArrayList<Card>();
		marbles= new ArrayList<Marble>();
		selectedCard=null;
		selectedMarbles=new ArrayList<Marble>();
		
		Marble m1=new Marble(colour);
		Marble m2=new Marble(colour);
		Marble m3=new Marble(colour);
		Marble m4=new Marble(colour);
		
		marbles.add(m1);
		marbles.add(m2);
		marbles.add(m3);
		marbles.add(m4);
		
	}
	
	

	public ArrayList<Card> getHand() {
		return hand;
	}





	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}





	public String getName() {
		return name;
	}





	public Colour getColour() {
		return colour;
	}





	public ArrayList<Marble> getMarbles() {
		return marbles;
	}





	public Card getSelectedCard() {
		return selectedCard;
	}





	public static void main(String[] args) {
		
		

	}

}
