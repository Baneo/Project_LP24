package lp24.project;

import java.awt.Graphics;

import javax.swing.JPanel;

/*
 * This class handle the graphic part of the grid, as well ass the Game Over and the Pause screen.
 */
public class Panel extends JPanel{
	
	private static final long serialVersionUID = 1702790145343124778L;
	
	
	private Model model;
	private GameOverScreen gameOver;
	private PauseScreen pause;
	
	public Panel(Model model)
	{
		/*
		 * When the game is launched, we set the grid with the right size (preferedSize), and we
		 * create the Game Over screen as well as the Pause screen. At this state, both are just
		 * created, but not shown to the user.
		 */
		this.model = model;
		this.setPreferredSize(model.getSize());
		this.gameOver = new GameOverScreen(model);
		this.gameOver.run();
		this.pause = new PauseScreen(model);
		this.pause.run();
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * 
	 * When this method is called, the grid's updated on the screen and one of the Game Over/Pause screen is displayed,
	 * if the requirement are matched. the Game Over screen has priority over the Pause screen, because we don't want a
	 * Pause screen when a previous grid is loaded, if this grid is in Game Over state.
	 */
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		model.draw(g);
		if(model.isGameOver() == true)
		{
			g.drawImage(gameOver.getImage(), 0, 0, null);
		}
		else if(model.isGamePaused() == true)
		{
			g.drawImage(pause.getImage(), 0, 0, null);
		}
	}

}
