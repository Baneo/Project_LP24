package lp24.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;





public class Model{

	
	private static final int FRAME_THICKNESS = 16;

	private int globalHighestScore;
	private int globalHighestTile;
	private int localScore;
	private int localHighestTile;
	
	private int redWins;
	private int blueWins;
	private boolean gameWon;
	private boolean paused;
	
	private Tile[][] grid;
	
	private Random random;
	
	/*
	 * TileColor : enum, value = blue, red, green, black;
	 * winColor's value is black until a 2048 tile is created with blue or red color. At this moment, winColor takes this color
	 */
	private TileColor winColor; 
	
	private boolean arrow;
	
	public Model()
	{
		this.grid = new Tile[8][8];
		initialization();
		this.random = new Random();
		this.arrow = false;
		
		this.globalHighestScore = 0;
        this.globalHighestTile = 0;
        this.localScore = 0;
        this.localHighestTile = 0;
        
        this.winColor=TileColor.black;
        this.paused = true;
	}
	
	public void initialization()
	{
		int tileLocationX = FRAME_THICKNESS;
		for(int i = 0; i < 8; i++)
		{
			int tileLocationY = FRAME_THICKNESS;
			for(int j = 0 ; j < 8 ; j++)
			{
				Tile tile = new Tile(0);
				tile.setTileColor(TileColor.black);
				tile.setTileLocation(tileLocationX, tileLocationY);
				grid[i][j] = tile;
				tileLocationY = tileLocationY + FRAME_THICKNESS + Tile.getTileWidth();
			}
			tileLocationX = tileLocationX + FRAME_THICKNESS + Tile.getTileWidth();
		}
	}
	
	public void setHighScores(boolean newGame)
	{
		if(localScore > globalHighestScore)
		{
			globalHighestScore = localScore;
		}
		
		if(localHighestTile > globalHighestTile)
		{
			globalHighestTile = localHighestTile;
		}
		
		if(isGameOver() || newGame){
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

	public boolean isGameOver()
	{
		if(isGridFull() && isMoveImpossible())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean isGridFull()
	{
		for(int i = 0 ; i < 8 ; i++)
		{
			for(int j = 0 ; j < 8 ; j++)
			{
				if(grid[i][j].isEmpty())
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean isMoveImpossible()
	{
		boolean noMove = true;
		
		for (int i = 0 ; i < 8 ; i++) {
            for (int j = 0 ; j < 7; j++) {
                
                if (grid[i][j].getValue() == grid[i][j + 1].getValue() && grid[i][j].getTileColor().equals(grid[i][j+1].getTileColor()) && !grid[i][j].getTileColor().equals(TileColor.black)) {
                    noMove = false;
                }
            }
        }
         
        for (int j = 0; j < 8; j++) {
            for (int i = 0 ; i < 7 ; i++) {
                
            	if (grid[i][j].getValue() == grid[i + 1][j].getValue() && grid[i][j].getTileColor().equals(grid[i + 1][j].getTileColor()) && !grid[i][j].getTileColor().equals(TileColor.black)) {
            		noMove = false;
                }
            }
        }
         
        return noMove;
	}
	
	public void addTile()
	{
		int value;
		boolean rightLocation = false;
		TileColor color;
		
		if(random.nextInt(10) < 8)
		{ value = 2; }
		else
		{ value = 4; }

		boolean notBlack = (random.nextInt(10000) < 9900) ? true : false;
        if(notBlack){
        	color = (random.nextInt(10000) < 5000) ? TileColor.blue : TileColor.red;
        }
        else{
        	color = TileColor.black;
        }
         
		while (rightLocation == false)
		{
			int x = random.nextInt(8);
			int y = random.nextInt(8);
			
			if(grid[x][y].isEmpty() == true)
			{
				
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("Tile added at[" + x + "][" + y + "]");
				System.out.println(stringBuilder.toString());
				
				rightLocation = true;
				grid[x][y].setValue(value);
				grid[x][y].setTileColor(color);
				updateScore(value, color);
			}
			
		}
		
	}
	
	public Dimension getSize()
	{
		int dimension = 8 * Tile.getTileWidth() + FRAME_THICKNESS * 9;
		return new Dimension(dimension, dimension);
	}
	
	public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        Dimension dimension = getSize();
        g.fillRect(0, 0, dimension.width, dimension.height);
         
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
		
		for(String tempCell : rows){	//pour chaque ligne de la grid
			indexCell=0;
			cell=tempCell.split(";");	//on split les cellules
			
			for(String tempData : cell){	//pour chaque cellule de la ligne
				data=tempData.split("_");	//on split les data de la cellule
				grid[indexRows][indexCell].setValue(Integer.parseInt(data[0]));
				
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

	public boolean merge(int x1, int y1, int x2, int y2, boolean hasMoved){
		
		if(!grid[x1][y1].isEmpty()){
			
			int value1 = grid[x1][y1].getValue(), value2 = grid[x2][y2].getValue();
			TileColor color1 = grid[x1][y1].getTileColor(), color2 = grid[x2][y2].getTileColor();
			
			if( value1==value2  && color1.equals(color2) && !grid[x1][y1].isBlackTile()){
				grid[x2][y2].setValue(value2*2);
                grid[x2][y2].setTileColor(color1);
                grid[x1][y1].setValue(0);
                updateScore(value2*2, color1);
                hasMoved = true;
			}
		}
		return hasMoved;
	}
	
	private boolean moveTile(int x1, int y1, int x2, int y2) {
			
		boolean hasMoved=false;
			
		if(!grid[x1][y1].isEmpty() && grid[x2][y2].isEmpty()){
			grid[x2][y2].setValue(grid[x1][y1].getValue());
			grid[x2][y2].setTileColor(grid[x1][y1].getTileColor());
			grid[x1][y1].setValue(0);
			hasMoved=true;
		}
		return hasMoved;
	}
	
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
	
	public void isWin(TileColor color){
		if(!gameWon && localHighestTile>=2048){
			gameWon=true;
			if(color.equals(TileColor.blue)){
				blueWins++;
			}
			else{
				redWins++;
			}
		}
	}
	
	public boolean isGamePaused()
	{
		return paused;
	}
	
	public void setPause(boolean pause)
	{
		this.paused = pause;
	}
	
	
	
}
