package lp24.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;


public class Model {

	
	private static final int FRAME_THICKNESS = 16;

	private int globalHighestScore;
	private int globalHighestTile;
	private int localScore;
	private int localHighestTile;
	private boolean gameWon;
	
	private Tile[][] grid;
	
	private Random random;
	
	private boolean arrow;
	
	public Model()
	{
		this.grid = new Tile[8][8];
		this.random = new Random();
		this.arrow = false;
		initialization();
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
				tile.setTileLocation(tileLocationX, tileLocationY);
				grid[i][j] = tile;
				tileLocationY = tileLocationY + FRAME_THICKNESS + Tile.getTileWidth();
			}
			tileLocationX = tileLocationX + FRAME_THICKNESS + Tile.getTileWidth();
		}
	}
	
	public void setHighScores()
	{
		if(localScore > globalHighestScore)
		{
			globalHighestScore = localScore;
		}
		
		if(localHighestTile > globalHighestTile)
		{
			globalHighestTile = localHighestTile;
		}
		
		localScore = 0;
		localHighestTile = 0;
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
		for (int i = 0 ; i < 8 ; i++) {
            for (int j = 0 ; j < 7; j++) {
                
                if (grid[i][j].getValue() == grid[i][j + 1].getValue()) {
                    return false;
                }
            }
        }
         
        for (int j = 0; j < 8; j++) {
            for (int i = 0 ; i < 7 ; i++) {
                
                if (grid[i][j].getValue() == grid[i + 1][j].getValue()) {
                    return false;
                }
            }
        }
         
        return true;
	}
	
	public void addTile()
	{
		int value;
		
		if(random.nextInt(10) < 8)
		{ value = 2; }
		else
		{ value = 4; }
		
		boolean rightLocation = false;
		
		while (rightLocation == false)
		{
			int x = random.nextInt(8);
			int y = random.nextInt(8);
			
			if(grid[x][y].isEmpty() == true)
			{
				rightLocation = true;
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("Tile added at[" + x + "][" + y + "]");
				System.out.println(stringBuilder.toString());
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
		String[] cell, data;
		int indexCell=0, indexRows=0;
		
		for(String tempCell : rows){
			cell=rows[indexRows].split(";");
			
			for(String tempData : cell){
				data=cell[indexCell].split("%");
				grid[indexRows][indexCell]=new Tile(Integer.parseInt(data[0]));
				
				switch (data[1]){
					case "red":	grid[indexRows][indexCell].setTileColor(TileColor.red);
						break;
					case "blue":grid[indexRows][indexCell].setTileColor(TileColor.blue);
						break;
					case "green":grid[indexRows][indexCell].setTileColor(TileColor.green);
						break;
					case "black":grid[indexRows][indexCell].setTileColor(TileColor.black);
						break;
				}
			
			}
		}
		
		
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
         
        if (tryMoveUp()) 
        	hasMoved = true;
         
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 7; x++) {
                int xNext = x + 1;
                hasMoved = merge(xNext, y, x, y, hasMoved);
            }
        }
         
        if (tryMoveLeft())
        	hasMoved = true;
         
        return hasMoved;
    }
     
    private boolean tryMoveLeft() {
        boolean hasMoved = false;
         
        for (int y = 0; y < 8; y++) {
            boolean rowHasMoved = false;
            do {
            	boolean columnHasMoved = false;
                for (int x = 0; x < 7; x++) {
                    int xNext = x + 1;
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
		if(localScore>globalHighestScore){
			globalHighestScore = localScore;
			isWin();
		}
		
	}
	
	public void isWin(){
		if(!gameWon && localHighestTile>=2048){
			gameWon=true;
		}
	}
	
}
