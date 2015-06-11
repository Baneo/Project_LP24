package lp24.project;

/*
 * This Enum was created to handle tile's colors.
 */
public enum TileColor {
	// possible values
	red ("red"),
	blue ("blue"),
	green ("green"),
	black ("black");
	
	// string containing the object's values
	private String color = "";
	
	// constructor
	private TileColor(String color){
		this.color=color;
	}
	
	public String toString(){
		return color;
	}
}
