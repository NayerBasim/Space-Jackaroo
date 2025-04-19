package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.Colour;
import model.player.Marble;

public class Jack extends Standard {

    public Jack(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 11, suit, boardManager, gameManager);
    }
    
    
    
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
    	
    	if(marbles.size()==1 || marbles.size()==2) {return true;}
    	else {return false;}
    	
    }
    
    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
		 
    	Colour playerColour = gameManager.getActivePlayerColour();
	    	
		if(marbles.size()==2) {
			Colour marble1 = marbles.getFirst().getColour();
			Colour marble2 = marbles.getLast().getColour();
			
			if((marble1==playerColour && marble2!=playerColour) || (marble2==playerColour && marble1!=playerColour)) {return true;}
			else {return false;}
		}else{
			return super.validateMarbleColours(marbles);
		}
	    	
    }
    
	public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {

		if(marbles.size()==2) {
			Marble marble1 = marbles.get(0);
			Marble marble2 = marbles.get(1);
			boardManager.swap(marble1, marble2);
		}else {
			super.act(marbles);
		}
		
	}

}
