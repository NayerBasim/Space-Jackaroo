package engine.board;
import java.util.ArrayList;
import model.Colour;


public class SafeZone {

	final private Colour colour; // read only
	final private ArrayList<Cell> cells; // read only
	
	public SafeZone(Colour colour){
		this.colour = colour;
		for(int i =0;i<4;i++){
			cells.add(Cell(CellType.SAFE)); // (to be debugged) 
		}
		
	}
	public Colour getColour(){
		return colour;
	}
	public ArrayList<Cell> getCells(){
		return cells;
	}
}
