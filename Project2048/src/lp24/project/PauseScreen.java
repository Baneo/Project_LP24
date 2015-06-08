package lp24.project;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class PauseScreen implements Runnable{
	
	private BufferedImage pause;
	private Model model;
	
	public PauseScreen(Model model)
	{
		this.model = model;
	}
	
	public void run()
	{
		String pauseSentence = "Game Paused";
		Dimension d = model.getSize();
		pause = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = pause.createGraphics();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0,  0, d.width, d.height);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		g.setColor(Color.WHITE);
		Font font = g.getFont();
		Font largeFont = font.deriveFont(72.0F);
		FontRenderContext fontRenderContext = new FontRenderContext(null, true, true);
		Rectangle2D rectangle = largeFont.getStringBounds(pauseSentence, fontRenderContext);
		int rectangleWidth = (int) Math.round(rectangle.getWidth());
		int rectangleHeight = (int) Math.round(rectangle.getHeight());
		int rectangleX = (int) Math.round(rectangle.getX());
		int rectangleY = (int) Math.round(rectangle.getY());
		int x = (d.width/2)-(rectangleWidth/2)-rectangleX;
		int y = (d.width/2)-(rectangleHeight/2)-rectangleY;
		g.setFont(largeFont);
		g.drawString(pauseSentence,  x,  y);
		g.dispose();
	}
	
	public BufferedImage getImage()
	{
		return pause;
	}

}
