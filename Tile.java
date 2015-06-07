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
	private TileColor color;
	
	public Tile(int value)
	{
		this.value = value;
	}
	
	public static int getTileWidth()
	{
		return 71;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setValue(int value)
	{
		this.value = value;
	}
	
	public void setTileColor(TileColor color){
		this.color = color;
	}
	
	public TileColor getTileColor(){
		return this.color;
	}
	
	public boolean isBlackTile(){
		return (this.color==TileColor.black);
	}
	
	public boolean isEmpty()
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
			g.fillRect(tileLocation.x, tileLocation.y, 71, 71);
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
		
			if(!this.color.equals(TileColor.black)){
        	
				// the value is negative if the tile is red, and positive is the tile is blue
				int valueAlgebraic = (this.color.equals(TileColor.blue)) ? value : -value;
        
				switch (valueAlgebraic) {
            		case 2:     color = new Color(171, 171, 252);
                        break;
            		case 4:     color = new Color(171, 171, 252);
                        break;
            		case 8:     color = new Color(149, 149, 251);
                        break;
            		case 16:    color = new Color(124, 124, 250);
                        break;
            		case 32:    color = new Color(100, 100, 249);
                        break;
            		case 64:    color = new Color(78, 78, 248);
                        break;
            		case 128:   color = new Color(38, 38, 243);
                        break;
            		case 256:   color = new Color(10, 10, 239);
                        break;
            		case 512:   color = new Color(7, 7, 180);
            			break;
            		case 1024:  color = new Color(5, 5, 120);
                        break;
            		case 2048:  color = new Color(3, 3, 90);
                        break;
            		case -2:     color = new Color(250, 198, 251);
            			break;
            		case -4:     color = new Color(250, 198, 251);
            			break;
            		case -8:     color = new Color(248, 173, 250);
            			break;
            		case -16:    color = new Color(247, 147, 249);
            			break;
            		case -32:    color = new Color(242, 107, 245);
            			break;
            		case -64:    color = new Color(239, 67, 243);
            			break;
            		case -128:   color = new Color(236, 27, 241);
            			break;
            		case -256:   color = new Color(200, 13, 204);
            			break;
            		case -512:   color = new Color(176, 11, 181);
            			break;
            		case -1024:  color = new Color(126, 9, 130);
            			break;
            		case -2048:  color = new Color(84, 5, 86);
            			break;
            		default:    if(this.color.equals(TileColor.blue)) 
            					color = new Color(2, 2, 64);
            				else
            					color = new Color(57, 3, 58);
                       	break;
				}// end of switch
        	
			}//end of if
			else{
        	
				color = new Color(0, 0, 0); // black color
        	
				/*THIS SWITCH CONTAINS ALL THE != COLORS FOR THE GREEN TileColor
				 * switch (value) {
    				case 2:     color = new Color(199, 253, 172);
            			break;
    				case 4:     color = new Color(184, 253, 151);
            			break;
    				case 8:     color = new Color(160, 252, 116);
    					break;
    				case 16:    color = new Color(132, 252, 73);
            			break;
    				case 32:    color = new Color(106, 251, 36);
            			break;
    				case 64:    color = new Color(87, 251, 9);
            			break;
    				case 128:   color = new Color(74, 251, 4);
            			break;
    				case 256:   color = new Color(61, 180, 3);
            			break;
    				case 512:   color = new Color(53, 156, 3);
            			break;
    				case 1024:  color = new Color(41, 123, 2);
            			break;
    				case 2048:  color = new Color(33, 98, 2);
            			break;
    				default:    color = new Color(24, 73, 1);
    					break;
    			}//end of switch	*/
        	
        	
			}//end of else
        
			return color;
	}
	
	private Color getTileTextColor() {
    	if( !color.equals(TileColor.black)){
    		return (value >= 256) ? Color.WHITE : Color.BLACK;
    	}
    	else{
    		return Color.BLACK;
    	}
        
    }

}
