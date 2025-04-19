package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.Colour;
import model.player.Marble;

public class Seven extends Standard {

    public Seven(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 7, suit, boardManager, gameManager);
    }
    
    
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
    	
    	if(marbles.size()==1 || marbles.size()==2) {return true;}
    	else {return false;}
    	
    }
    
    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
    	
    	if(marbles.size()==2) {return true;}
    	else {return super.validateMarbleColours(marbles);}
    		
    }
    
	public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
		
		if(marbles.size()==2) {
			
			Marble marble1=marbles.get(0);
			Marble marble2=marbles.get(1);
			int splitDist= boardManager.getSplitDistance();
			
			boardManager.moveBy(marble1, splitDist, false);
			boardManager.moveBy(marble2, 7-splitDist, false);
			
		}
		
		else{
			
			super.act(marbles);
		}
	}

}
