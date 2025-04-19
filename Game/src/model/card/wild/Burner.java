package model.card.wild;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.Colour;
import model.player.Marble;

public class Burner extends Wild {

    public Burner(String name, String description, BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager); 
    }
    
    public boolean validateMarbleColours(ArrayList<Marble> marbles){
    	Colour playerColour = gameManager.getActivePlayerColour();
    
    	for(int i=0 ; i<marbles.size() ; i++) {
    		if(marbles.get(i).getColour()==playerColour) {return false;}
    	}
    	return true;
    	
    }

	public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
		
		Marble curr=marbles.get(0);
		gameManager.sendHome(curr);
		
	}

}
