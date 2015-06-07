package lp24.project;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1702790145343124778L;
	
	
	private Model model;
	private GameOverScreen gameOver;
	
	public Panel(Model model)
	{
		this.model = model;
		this.setPreferredSize(model.getSize());
		this.gameOver = new GameOverScreen(model);
		this.gameOver.run();
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		model.draw(g);
		if(model.isGameOver() == true)
		{
			g.drawImage(gameOver.getImage(), 0, 0, null);
		}
	}

}
