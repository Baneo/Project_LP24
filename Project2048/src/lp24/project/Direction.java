package lp24.project;

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
