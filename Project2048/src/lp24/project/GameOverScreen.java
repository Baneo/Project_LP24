package lp24.project;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class GameOverScreen {
	
	private BufferedImage gameOver;
	private Model model;
	public GameOverScreen(Model model)
	{
		this.model = model;
	}
	
	public void run()
	{
		String gameOverSentence = "YOU LOOSE, Game Over !";
		Dimension d = model.getSize();
		gameOver = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = gameOver.createGraphics();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0,  0, d.width, d.height);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		g.setColor(Color.BLUE);
		Font font = g.getFont();
		Font largeFont = font.deriveFont(72.0F);
		FontRenderContext fontRenderContext = new FontRenderContext(null, true, true);
		Rectangle2D rectangle = largeFont.getStringBounds(gameOverSentence, fontRenderContext);
		int rectangleWidth = (int) Math.round(rectangle.getWidth());
		int rectangleHeight = (int) Math.round(rectangle.getHeight());
		int rectangleX = (int) Math.round(rectangle.getX());
		int rectangleY = (int) Math.round(rectangle.getY());
		int x = (d.width/2)-(rectangleWidth/2)-rectangleX;
		int y = (d.width/2)-(rectangleWidth/2)-rectangleY;
		g.setFont(largeFont);
		g.drawString(gameOverSentence,  x,  y);
		g.dispose();
	}
	
	public BufferedImage getImage()
	{
		return gameOver;
	}

}
