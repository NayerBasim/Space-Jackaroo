package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.Colour;
import model.player.Marble;

public class Ten extends Standard {

    public Ten(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 10, suit, boardManager, gameManager);
    }
    
    
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
    	
    	return super.validateMarbleSize(marbles) || marbles.size()==0;
    	
    }
    
	public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
		
		if(marbles.size()==0) {
			Colour nextPlayer=gameManager.getNextPlayerColour();
			this.gameManager.discardCard(nextPlayer);
			
		}else {
			super.act(marbles);
		}
		
		
	}

}
