package lp24.project;

import javax.swing.SwingUtilities;

public class Game_2048 implements Runnable{
	
	public void run()
	{
		new Frame(new Model());
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Game_2048());
	}

}
