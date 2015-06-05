package lp24.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

public class Model {

	
	private static final int FRAME_THICKNESS = 16;
	private int highScore;
	private int higherTile;
	private int currentHighScore;
	private int currentHigherCell;
	private Tile[][] grid;
	private Random random;
	private boolean arrow;
	
	public Model()
	{
		this.grid = new Tile[8][8];
		this.random = new Random();
		this.highScore = 0;
		this.higherTile = 0;
		this.currentHigherCell = 0;
		this.currentHighScore = 0;
		this.arrow = false;
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
		if(currentHighScore > highScore)
		{
			highScore = currentHighScore;
		}
		
		if(currentHigherCell > higherTile)
		{
			higherTile = currentHigherCell;
		}
		
		currentHighScore = 0;
		currentHigherCell = 0;
	}
	
	public void setHigherScore(int highScore) 
	{
        this.highScore = highScore;
    }
	
	public int getHigherScore()
	{
		return highScore;
	}
	
	public void setHigherTile(int higherTile) 
	{
        this.higherTile = higherTile;
    }
	
	public int getHigherTile()
	{
		return higherTile;
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
				if(grid[i][j].isZeroValue())
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
            for (int j = 0 ; j < (8 - 1); j++) {
                
                if (grid[i][j].getValue() == grid[i][j + 1].getValue()) {
                    return false;
                }
            }
        }
         
        for (int j = 0; j < 8; j++) {
            for (int i = 0 ; i < (8 - 1) ; i++) {
                
                if (grid[i][j].getValue() == grid[i + 1][j].getValue()) {
                    return false;
                }
            }
        }
         
        return true;
	}
	
	public void addTile()
	{
		if(random.nextInt(10) < 8)
		{
			int value = 2;
		}
		else
		{
			int value = 4;
		}
		
		boolean rightLocation = false;
		
		while (rightLocation == false)
		{
			int x = random.nextInt(8);
			int y = random.nextInt(8);
			
			if(grid[x][y].isZeroValue() == true)
			{
				rightLocation = true;
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("Cell added at[" + x + "][" + y + "]");
				System.out.println(stringBuilder.toString());
			}
			
		}
		
	}
	
	public Dimension getSize()
	{
		int dimension = 8 * Tile.getTileWidth() + FRAME_THICKNESS *9;
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
	
	
	
	
}
