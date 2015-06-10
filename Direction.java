package lp24.project;

/*
 * This enum was created to combine all the listeners corresponding to the movement of tiles in a single class.
 */

public enum Direction {
	// valeurs possibles
	left ("left"),
	right ("right"),
	up ("up"),
	down ("down");
	
	private String direction = "";
	
	Direction(String direction){
		this.direction=direction;
	}
	
	public String toString(){
		return direction;
	}
	
	
}
