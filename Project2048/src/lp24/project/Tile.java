package lp24.project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Tile {
	
	private int value;
	private Point tileLocation;
	
	public Tile(int value)
	{
		this.value = value;
	}
	
	public static int getTileWidth()
	{
		return 120;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
	
	public boolean isZeroValue()
	{
		if(value == 0)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	public void setTileLocation(int x, int y)
	{
		this.tileLocation = new Point(x,y);
	}
	
	public void drawTile(Graphics g)
	{
		if (value == 0)
		{
			g.setColor(Color.GRAY);
			g.fillRect(tileLocation.x, tileLocation.y, 120, 120);
		}
		else
		{
			Font font  = g.getFont();
			FontRenderContext fontRenderContext = new FontRenderContext(null, true, true);
			String tileValue = Integer.toString(value);
			BufferedImage tileImage = createImage(font, fontRenderContext, 120, tileValue);
			g.drawImage(tileImage, tileLocation.x, tileLocation.y, null);
		}
	}
	
	private BufferedImage createImage(Font font, FontRenderContext fontRenderContext, int width, String tileValue)
	{
		Font largeFont = font.deriveFont((float) (width/4));
		Rectangle2D rectangle = largeFont.getStringBounds(tileValue, fontRenderContext);
		int rectangleWidth = (int) Math.round(rectangle.getWidth());
		int rectangleHeight = (int) Math.round(rectangle.getHeight());
		int rectangleX = (int) Math.round(rectangle.getX());
		int rectangleY = (int) Math.round(rectangle.getY());
		BufferedImage tileImage = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
		Graphics g = tileImage.getGraphics();
		g.setColor(defineTileColor());
		g.fillRect(0, 0, tileImage.getWidth(), tileImage.getHeight());
		int x = (width/2) - (rectangleWidth/2) - rectangleX;
		int y = (width/2) - (rectangleHeight/2) - rectangleY;
		g.setFont(largeFont);
		g.setColor(getTileTextColor());
		g.drawString(tileValue, x, y);
		g.dispose();
		return tileImage;
	}
	
	private Color defineTileColor()
	{
		Color color = Color.WHITE;
		
		switch(value)
		{
		case 2:
			color = Color.WHITE;
			break;
		case 4:
			color = Color.WHITE;
			break;
		case 8:
			color = Color.WHITE;
			break;
		case 16:
			color = Color.WHITE;
			break;
		case 32:
			color = Color.WHITE;
			break;
		case 64:
			color = Color.WHITE;
			break;
		case 128:
			color = Color.WHITE;
			break;
		case 256:
			color = Color.WHITE;
			break;
		case 512:
			color = Color.WHITE;
			break;
		case 1024:
			color = Color.WHITE;
			break;
		case 2048:
			color = Color.WHITE;
			break;
		case 4096:
			color = Color.WHITE;
			break;
		default: 
			color = new Color(43,43,0);
			break;
		}
		
		return color;
	}
	
	private Color getTileTextColor()
	{
		if(value > 256)
		{
			return Color.WHITE;
		}
		else
		{
			return Color.BLACK;
		}
	}

}
