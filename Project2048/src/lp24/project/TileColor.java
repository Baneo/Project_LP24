package lp24.project;

public enum TileColor {
	// valeurs possibles
	red ("red"),
	blue ("blue"),
	green ("green"),
	black ("black");
	
	// string contenant la valeur de l'objet
	private String color = "";
	
	// constructeur
	TileColor(String color){
		this.color=color;
	}
	
	public String toString(){
		return color;
	}
}
