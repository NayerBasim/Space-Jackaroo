package model.card.standard;

import engine.GameManager;
import engine.board.BoardManager;
import model.card.Suit;

public class Queen extends Standard{
	
	public Queen(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager){
		super(name, description,12, suit, boardManager, gameManager);
		
	}
}
