package lp24.project;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/*
 * This class corresponds to the Pause text that appear on the screen when a previous not-lost grid is loaded.
 */
public class PauseScreen implements Runnable{
	
	private BufferedImage pause;
	private Model model;
	
	public PauseScreen(Model model)
	{
		this.model = model;
	}
	
	public void run()
	{
		String pauseSentence = "Game Paused"; // displayed string
		Dimension d = model.getSize(); // setting the string's size to the grid panel's size
		
		pause = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB); // creation of the image
		Graphics2D g = pause.createGraphics();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0,  0, d.width, d.height);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		g.setColor(Color.WHITE);
		
		/*
		 * Wanted modification to the font
		 */
		Font font = g.getFont();
		Font largeFont = font.deriveFont(72.0F);
		FontRenderContext fontRenderContext = new FontRenderContext(null, true, true);
		Rectangle2D rectangle = largeFont.getStringBounds(pauseSentence, fontRenderContext);
		
		/*
		 * Modification to rectangle's dimensions and upper left corner's position.
		 */
		int rectangleWidth = (int) Math.round(rectangle.getWidth());
		int rectangleHeight = (int) Math.round(rectangle.getHeight());
		int rectangleX = (int) Math.round(rectangle.getX());
		int rectangleY = (int) Math.round(rectangle.getY());
		int x = (d.width/2)-(rectangleWidth/2)-rectangleX;
		int y = (d.width/2)-(rectangleHeight/2)-rectangleY;
		
		/*
		 * Font's modifications are applied
		 */
		g.setFont(largeFont);
		g.drawString(pauseSentence,  x,  y);
		g.dispose();
	}
	
	public BufferedImage getImage()
	{
		return pause;
	}

}
