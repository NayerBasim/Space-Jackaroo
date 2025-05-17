package controller;

import java.io.IOException;

import engine.Game;

public class GameController {
	Game game;
	String name;
	
	public GameController() throws IOException{
		this.game = new Game();
		this.name = "";
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	
}
