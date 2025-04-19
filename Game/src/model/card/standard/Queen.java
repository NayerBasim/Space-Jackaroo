package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;

public class Queen extends Standard {

    public Queen(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 12, suit, boardManager, gameManager);
    }
    
    
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
    	
    	int size=0;
    	for(int i=0; i<marbles.size() ; i++) {
    		if(marbles.get(i)!=null) {size++;}
    	}
    	
    	return size==1 || size==0;
    	
    }
    
	public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {

		if(marbles.size()==0) {
			
			this.gameManager.discardCard();
			
		}else {
			
			super.act(marbles);
		}
		
	}

}
