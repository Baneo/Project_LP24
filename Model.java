package lp24.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;


public class Model{

	// number of pixel between the tiles
	private static final int FRAME_THICKNESS = 16;

	
	/*
	 * globalHighestScore = highest score reached 
	 * globalHighestTile = highest value reached
	 * localScore = current score
	 * localHighestTile = current game highest tile
	 */
	private int globalHighestScore;
	private int globalHighestTile;
	private int localScore;
	private int localHighestTile;
	
	/*
	 * 'color'Wins = number of game won with that color
	 * gameWon = boolean variable, false if the game is not won, true if it is
	 * paused = boolean value, true if a game is saved when the user start the game
	 */
	private int redWins;
	private int blueWins;
	private boolean gameWon;
	private boolean paused;
	
	/*
	 * grid = matrix of 8x8 tiles
	 */
	private Tile[][] grid;
	
	/*
	 * random : Random-type variable, will be use ffor the new tile generation
	 */
	private Random random;
	
	/*
	 * TileColor : enum, value = blue, red, green, black;
	 * winColor's value is black until a 2048 tile is created with blue or red color. At this moment, winColor takes this color
	 */
	private TileColor winColor; 
	
	/*
	 * arrow = boolean variable, true when the user hit an arrowor zqsd key, false otherwise 
	 */
	private boolean arrow;
	
	public Model()
	{
		// initialization of the 8x8 grid
		this.grid = new Tile[8][8];
		initialization();
		
		this.random = new Random();
		this.arrow = false;
		
		// setting all highscores at 0
		this.globalHighestScore = 0;
        this.globalHighestTile = 0;
        this.localScore = 0;
        this.localHighestTile = 0;
        
        // setting the winColor to black
        this.winColor=TileColor.black;
        // putting the game in pause
        this.paused = true;
	}
	
	public void initialization()
	{
		//creating a space above the grid
		int tileLocationX = FRAME_THICKNESS;
		for(int i = 0; i < 8; i++) // for every column
		{
			int tileLocationY = FRAME_THICKNESS; // creating a space on the left of the grid
			for(int j = 0 ; j < 8 ; j++) // for every tile in the current column
			{
				/*
				 * the value is set to 0, the color to black and the location to the corresponding (x,y) coordinates in pixels
				 */
				Tile tile = new Tile(0);
				tile.setTileColor(TileColor.black);
				tile.setTileLocation(tileLocationX, tileLocationY);
				grid[i][j] = tile;
				// adding the length of a tile + a space to the y coordinate
				tileLocationY = tileLocationY + FRAME_THICKNESS + Tile.getTileWidth();
			}
			// adding the length of a tile + a scape to the x coordinate
			tileLocationX = tileLocationX + FRAME_THICKNESS + Tile.getTileWidth();
		}
	}
	
	public void setHighScores(boolean newGame)
	{
		if(localScore > globalHighestScore) // if the new local score if greater than the previous global high score
		{
			globalHighestScore = localScore;
		}
		
		if(localHighestTile > globalHighestTile) // if the new local highest tile value is greater thanthe global older one
		{
			globalHighestTile = localHighestTile;
		}
		
		if(isGameOver() || newGame){ // if the game is over or if this is a new game
        	localScore = 0;
        	localHighestTile = 0;
        }    
	}
	
	public void setGlobalHighestScore(int highScore) 
	{
        this.globalHighestScore = highScore;
    }
	
	public int getGlobalHighestScore()
	{
		return globalHighestScore;
	}
	
	public boolean isArrow() {
		return arrow;
	}

	public void setArrow(boolean arrow) {
		this.arrow = arrow;
	}

	public void setGlobalHighestTile(int higherTile) 
	{
        this.globalHighestTile = higherTile;
    }
	
	public int getGlobalHighestTile()
	{
		return globalHighestTile;
	}
	
	public int getLocalScore() {
		return localScore;
	}

	public void setLocalScore(int localScore) {
		this.localScore = localScore;
	}

	public int getLocalHighestTile() {
		return localHighestTile;
	}

	public void setLocalHighestTile(int localHighestTile) {
		this.localHighestTile = localHighestTile;
	}

	public int getRedWins() {
		return redWins;
	}

	public void setRedWins(int gameWonRed) {
		this.redWins = gameWonRed;
	}

	public int getBlueWins() {
		return blueWins;
	}

	public void setBlueWins(int gameWonBlue) {
		this.blueWins = gameWonBlue;
	}

	public Tile[][] getGrid() {
		return grid;
	}

	public void setGrid(Tile[][] grid) {
		this.grid = grid;
	}
	
	public TileColor getWinColor() {
		return winColor;
	}

	public void setWinColor(TileColor winColor) {
		this.winColor = winColor;
	}

	public boolean isGamePaused()
	{
		return paused;
	}
	
	public void setPause(boolean pause)
	{
		this.paused = pause;
	}
	
	public void setWinColor(String winColor) {
		TileColor color=TileColor.black;
		switch (winColor) {
		case "blue" : color=TileColor.blue;
			break;
		case "red" : color=TileColor.red;
			break;
		case "breen" : color=TileColor.green;
			break;
		case "black" : color=TileColor.black;
			break;
		}
		
		this.winColor = color;
	}

	/*
	 * The isGameOver method checks if the grid is full and if the user can't move or merge tiles. In that case, the value true is returned. Otherwise, false is returned.
	 */
	public boolean isGameOver()
	{
		return (isGridFull() && isMoveImpossible());
	}
	
	/*
	 * the method isGridFull return false when it founds any tile with a value of zero. If it can't, true is returned.
	 */
	private boolean isGridFull()
	{
		for(int i = 0 ; i < 8 ; i++) // for every row
		{
			for(int j = 0 ; j < 8 ; j++) // for every tile in the current row
			{
				if(grid[i][j].isEmpty()) // if the tile have a value of 0
				{
					return false;
				}
			}
		}
		
		return true; // true if the method didn't find any tile with a value of 0
	}
	
	public boolean isMoveImpossible()
	{
		boolean noMove = true;
		
		for (int i = 0 ; i < 8 ; i++) {
            for (int j = 0 ; j < 7; j++) {
                // for every tile in the grid, the method checks if this tile can go at its right
            	if (grid[i][j].getValue() == grid[i][j + 1].getValue() && grid[i][j].getTileColor().equals(grid[i][j+1].getTileColor()) && !grid[i][j].getTileColor().equals(TileColor.black)) {
                    noMove = false;
                }
            }
        }
         
        for (int j = 0; j < 8; j++) {
            for (int i = 0 ; i < 7 ; i++) {
                // for every tile in the grid, the method checks if this tile can go on the tile bellow it
            	if (grid[i][j].getValue() == grid[i + 1][j].getValue() && grid[i][j].getTileColor().equals(grid[i + 1][j].getTileColor()) && !grid[i][j].getTileColor().equals(TileColor.black)) {
            		noMove = false;
                }
            }
        }
         
        return noMove; // true if none of the grid's tiles can move
	}
	
	/*
	 * the method addTile is the method which will add a tile on the board (for example, every time the user makes a move, a new tile is added)
	 */
	public void addTile()
	{
		int value;
		boolean rightLocation = false;
		TileColor color;
		
		
		if(random.nextInt(10) < 8) // 80% chance of adding a tile with a value of 2, and 20% of adding a tile with a value of 4
		{ value = 2; }
		else
		{ value = 4; }

		boolean notBlack = (random.nextInt(100) < 99) ? true : false; // 1% chance of making the new tile black.
        if(notBlack){
        	color = (random.nextInt(10000) < 5000) ? TileColor.blue : TileColor.red; // if the tile isn't black, 50% chance of making this tile blue, and 50% of making this tile red.
        }
        else{
        	color = TileColor.black;
        }
         
		while (rightLocation == false) // we select a new random point in the grid while the current RNG point is not empty
		{
			int x = random.nextInt(8);
			int y = random.nextInt(8);
			
			if(grid[x][y].isEmpty() == true) // if we've found an empty tile
			{
				
				// we display the creation in the console (DEBUGGING)
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("Tile added at[" + x + "][" + y + "]");
				System.out.println(stringBuilder.toString());
				
				// the method set the value and the color of the tile and the score is updated
				rightLocation = true;
				grid[x][y].setValue(value);
				grid[x][y].setTileColor(color);
				updateScore(value, color);
			}
			
		}
		
	}
	
	public Dimension getSize()
	{
		// return n*tileWidth + (n+1)*spaceWidth ,  with for an n*n grid (here, n=8)
		int dimension = 8 * Tile.getTileWidth() + FRAME_THICKNESS * 9;
		return new Dimension(dimension, dimension);
	}
	
	
	public void draw(Graphics g) {
        // draw the background of the grid in dark gray
		g.setColor(Color.DARK_GRAY);
        Dimension dimension = getSize();
        g.fillRect(0, 0, dimension.width, dimension.height);
         
        // draw each tile with the right color
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                grid[x][y].drawTile(g);
            }
        }
    }
	
	public void loadGrid(String loadedGrid){
		String[] rows = loadedGrid.split(";;");
		String[] cell;
		String[] data;
		int indexCell=0, indexRows=0;
		
		for(String tempCell : rows){	//for each row of the grid
			indexCell=0;
			
			cell=tempCell.split(";");	// we split each tile			
			for(String tempData : cell){	//pour chaque cellule de la ligne
				data=tempData.split("_");	//we split the data of the tile
				grid[indexRows][indexCell].setValue(Integer.parseInt(data[0])); // the tile's value is set
				
				switch (data[1]){
					case "red":	grid[indexRows][indexCell].setTileColor(TileColor.red);
						break;
					case "blue":grid[indexRows][indexCell].setTileColor(TileColor.blue);
						break;
					case "green":grid[indexRows][indexCell].setTileColor(TileColor.green);
						break;
					case "black":grid[indexRows][indexCell].setTileColor(TileColor.black);
						break;
				}// end of switch
				indexCell++;
			}// end  of for loop
			indexRows++;
		}// end of for loop
	}// end of loadGrid()
	
	
public String toStringGrid(){
		
		String savedGrid="";
		int indexRows, indexCell;
		boolean firstRow=true;
		Tile activeCell;
		
		
		/*
		 *  this method's purpose is to transform the grid into a string that respect the following rules :
		 *		- each tile will be separated from each other by a ";"
		 *		- each row will be separated by ";;"
		 *		- each tile will be coded as follow : "value_color"
		 */
			
		for(indexRows=0;indexRows<8;indexRows++){ 
			if(firstRow){
				firstRow=false;
			}
			else{
				savedGrid += ";"; 
			}
			for(indexCell=0;indexCell<8;indexCell++){
				activeCell = grid[indexRows][indexCell];
				
				savedGrid += Integer.toString(activeCell.getValue())+"_"+activeCell.getTileColor().toString()+";";
			}
			
		}
		
		return savedGrid;
	}
		
		
	/*
	 * each move'Somewhere' method work the same algorithm
	 * 		1) it calls 'tryMove'Somewhere' and store the returned value in hasMoved.
	 * 		2) for each tile in the grid, it tries to merge it with the next tile in the corresponding direction.
	 * 		3) the boolean returned value is stored in 'hasMoved'.
	 * 		4) same has step 1).
	 * 		5) the method return 'hasMOved' : true if the tile has moved or has been merge with another, false otherwise
	 * 
	 * each tryMove'Somewhere' method work on the same algorithm
	 * 		1) hasMove is set to false.
	 * 		2) for each row/column (row for left/right move, column for up/down move), the method moves each tile and repeat this step if a tile has moved within the row/column.
	 * 		3) 'hasMoved' is returned (true if at least one tile has moved, false if none moved)
	 */
	
	public boolean moveUp() {
        boolean hasMoved = false;
         
        if (tryMoveUp())
        	hasMoved = true;
         
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 7; y++) {
                int yNext = y + 1;
                hasMoved = merge(x, yNext, x, y, hasMoved);
            }
        }
         
        if (tryMoveUp())  hasMoved = true;
         
        return hasMoved;
    }
     
    private boolean tryMoveUp() {
        boolean hasMoved = false;
         
        for (int x = 0; x < 8; x++) {
            boolean columnHasMoved = false;
            do {
                columnHasMoved = false;
                for (int y = 0; y < 7; y++) {
                    int yNext = y + 1;
                    boolean TileHasMoved = moveTile(x, yNext, x, y);
                    if (TileHasMoved) {
                        columnHasMoved = true;
                        hasMoved = true;
                    }
                }
            } while (columnHasMoved);      
        }
         
        return hasMoved;
    }
     
    public boolean moveDown() {
        boolean hasMoved = false;
         
        if (tryMoveDown())
        	hasMoved = true;
         
        for (int x = 0; x < 8; x++) {
            for (int y = 7; y > 0; y--) {
                int yNext = y - 1;
                hasMoved = merge(x, yNext, x, y, hasMoved);
            }
        }
         
        if (tryMoveDown())    hasMoved = true;
         
        return hasMoved;
    }
     
    private boolean tryMoveDown() {
        boolean hasMoved = false;
         
        for (int x = 0; x < 8; x++) {
            boolean columnHasMoved = false;
            do {
            	columnHasMoved = false;
                for (int y = 7; y > 0; y--) {
                    int yNext = y - 1;
                    boolean TileHasMoved = moveTile(x, yNext, x, y);
                    if (TileHasMoved) {
                        columnHasMoved = true;
                        hasMoved = true;
                    }
                }
            } while (columnHasMoved);      
        }
         
        return hasMoved;
    }
     
    public boolean moveLeft() {
        boolean hasMoved = false;
         
        if (tryMoveLeft())    hasMoved = true;
         
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < (8 - 1); x++) {
                int xNext = x + 1;
                hasMoved = merge(xNext, y, x, y, hasMoved);
            }
        }
         
        if (tryMoveLeft())    hasMoved = true;
         
        return hasMoved;
    }
     
    private boolean tryMoveLeft() {
        boolean hasMoved = false;
         
        for (int y = 0; y < 8; y++) {
            boolean rowHasMoved = false;
            do {
                rowHasMoved = false;
                for (int x = 0; x < (8 - 1); x++) {
                    int xNext = x + 1;
                    boolean tileHasMoved = moveTile(xNext, y, x, y);
                    if (tileHasMoved) {
                        rowHasMoved = true;
                        hasMoved = true;
                    }
                }
            } while (rowHasMoved);     
        }
         
        return hasMoved;
    }
     
    public boolean moveRight() {
        boolean hasMoved = false;
         
        if (tryMoveRight())   hasMoved = true;
         
        for (int y = 0; y < 8; y++) {
            for (int x = 7; x > 0; x--) {
                int xNext = x - 1;
                hasMoved = merge(xNext, y, x, y, hasMoved);
            }
        }
         
        if (tryMoveRight())   hasMoved = true;
         
        return hasMoved;
    }
 
    private boolean tryMoveRight() {
        boolean hasMoved = false;
         
        for (int y = 0; y < 8; y++) {
            boolean rowHasMoved = false;
            do {
                rowHasMoved = false;
                for (int x = 7; x > 0; x--) {
                    int xNext = x - 1;
                    boolean TileHasMoved = moveTile(xNext, y, x, y);
                    if (TileHasMoved) {
                        rowHasMoved = true;
                        hasMoved = true;
                    }
                }
            } while (rowHasMoved);     
        }
         
        return hasMoved;
    }

    /*
     * The merge method's purpose is to combine two tiles into one of the next 'rank' if these tiles share the same value and the same color, and only if these tiles are not black.
     */
	public boolean merge(int x1, int y1, int x2, int y2, boolean hasMoved){
		
		if(!grid[x1][y1].isEmpty()){
			
			int value1 = grid[x1][y1].getValue(), value2 = grid[x2][y2].getValue();
			TileColor color1 = grid[x1][y1].getTileColor(), color2 = grid[x2][y2].getTileColor();
			
			if( value1==value2  && color1.equals(color2) && !grid[x1][y1].isBlackTile()){
				// we double the value of the second tile and we set the first one's to zero.
				grid[x2][y2].setValue(value2*2);
                grid[x1][y1].setValue(0);
                // the score is updated, and hasMoved become true because 2 tiles were merge.
                updateScore(value2*2, color1);
                hasMoved = true;
			}
		}
		return hasMoved;
	}
	/*
	 *  The moveTile method's purpose is to move a tile to another position in the grid, if the tile hasn't a value of 0 and if the future position of this tile has a value of 0.
	 */
	private boolean moveTile(int x1, int y1, int x2, int y2) {
			
		boolean hasMoved=false;
			
		if(!grid[x1][y1].isEmpty() && grid[x2][y2].isEmpty()){ 
			// the second tile takes the value and the color of the first one, and the value of the tile n°1 become zero.
			grid[x2][y2].setValue(grid[x1][y1].getValue());
			grid[x2][y2].setTileColor(grid[x1][y1].getTileColor());
			grid[x1][y1].setValue(0);
			// hasMoved become true because a tile was move.
			hasMoved=true;
		}
		return hasMoved;
	}
	
	/*
	 * The updateScore method was made to handle all the values related to any global or local highScore. It works as follow :
	 * 		1) the value given in parameter, which is the value of a newly created or merged tile, is added to the local high score, aka the current game's score.
	 * 		2) if this value is greater than the value of the highest tile in this game, this value is updated.
	 * 		3) if the local high score is greater than the global highest score, which is the best score since the previous reboot of the save file, this value is updated.
	 * 		4) if the local highest tile value is greater than the global highest tile value, this value is updated.		
	 */
	public void updateScore(int value, TileColor color){
		localScore+=value;
		
		if(localHighestTile<value){
			localHighestTile =  value;
		}
		if(localScore>globalHighestScore){
			globalHighestScore = localScore;
			isWin(color);
		}
		if(localHighestTile>globalHighestTile){
			globalHighestTile = localHighestTile;
		}
		
	}
	
	/*
	 * The isWin method checks if the game is won, which means that the first 2048 tile has been made.
	 * When it happens, the methods checks the color of this tile and increments the counter of game won with this color.
	 */
	public void isWin(TileColor color){
		if(!gameWon && localHighestTile==2048){
			gameWon=true;
			if(color.equals(TileColor.blue)){
				blueWins++;
			}
			else{
				redWins++;
			}
		}
	}
	
	
}
