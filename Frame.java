package lp24.project;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class Frame {
	
	/*
	 * The only line of code that create a new object of the Frame Class is in Game_2048.java
	 * 
	 * controlPanel : variable which handle the different buttons ( "start new game", "continue game" and "exit game").
	 * model : variable which handle the hidden values of the game : the grid, the highScores, and all the computations.
	 * panel : variable which will handle the graphical area corresponding to the grid.
	 * highScore : variable which handle the loading and saving process.
	 * score : variable which handle the score panel in a graphical way.
	 */
	private ControlPanel controlPanel;
	private Model model;
	private Panel panel;
	private HighScore highScore;
	private JFrame frame;
	private Score score;
	
	/*
	 * This is the only lines of code that create a instance of the Model Class, and of the HighScore Class.
	 */
	public Frame ()
	{
		this.model = new Model();
		this.highScore = new HighScore(model);
		this.highScore.load();
		createPartControl();
	}
	
	
	/*
	 * createPartControl will 'create' the game on the user's screen, creating and gathering all graphical parts of the software, and linking it to the hidden part.
	 */
	private void createPartControl()
	{
		panel = new Panel(model);
		score = new Score(model);
		controlPanel = new ControlPanel(this, model);
		
		frame = new JFrame();
		frame.setTitle("Colored 2048");
		
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    frame.addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent event) {
	            	exitGame();
	            }
	        });
		
	    // the key that are able to move the tiles are set.
		setKeyBindings();
		
		//Creation of the grid
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		mainPanel.add(panel);
		
		// Creation of the score panel and the buttons.
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
		sidePanel.add(score.getPanel());
		sidePanel.add(Box.createVerticalStrut(30));
		sidePanel.add(controlPanel.getStart());
		sidePanel.add(controlPanel.getContinue());
		sidePanel.add(controlPanel.getExit());
		mainPanel.add(sidePanel);
		
		// all these components are added to the main frame, and this frame is displayed on the user's screen.
		frame.add(mainPanel);
		frame.setLocationByPlatform(true);
		frame.pack();
		frame.setVisible(true);
	}
		
	/*
	 * Settings for the keyboard's key used to play the game. 
	 * the user can use ZQSD or the arrow-keys to move the tiles.
	 */
		private void setKeyBindings()
		{
			InputMap inputs = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
			
			inputs.put(KeyStroke.getKeyStroke("Z"), "up arrow");
	        inputs.put(KeyStroke.getKeyStroke("S"), "down arrow");
	        inputs.put(KeyStroke.getKeyStroke("Q"), "left arrow");
	        inputs.put(KeyStroke.getKeyStroke("D"), "right arrow");
	        
	        
			inputs.put(KeyStroke.getKeyStroke("UP"), "up arrow");
			inputs.put(KeyStroke.getKeyStroke("DOWN"), "down arrow");
			inputs.put(KeyStroke.getKeyStroke("LEFT"), "left arrow");
			inputs.put(KeyStroke.getKeyStroke("RIGHT"), "right arrow");
			
			inputs = panel.getInputMap(JPanel.WHEN_FOCUSED);
			inputs.put(KeyStroke.getKeyStroke("UP"), "up arrow");
			inputs.put(KeyStroke.getKeyStroke("DOWN"), "down arrow");
			inputs.put(KeyStroke.getKeyStroke("LEFT"), "left arrow");
			inputs.put(KeyStroke.getKeyStroke("RIGHT"), "right arrow");
			
			panel.getActionMap().put("up arrow", new MoveAction(this, model, Direction.up));
			panel.getActionMap().put("down arrow", new MoveAction(this, model, Direction.down));
			panel.getActionMap().put("left arrow", new MoveAction(this, model, Direction.left));
			panel.getActionMap().put("right arrow", new MoveAction(this, model, Direction.right));
		
			
		}
		
	
	/*
	 * This method's purpose is to update the grid in a graphical way.
	 */
	public void repaintPanel(){
		panel.repaint();
	}
	
	/*
	 * This method's purpose is to update the score's panel in a graphical way.
	 */
	public void updateScore(){
		score.updatePartControl();
	}
	
	/*
	 * This method changes the behavior of the software on close.
	 * When the user close the program (red cross in the upper right corner or the "exit" button), the score's values are updated, and then saved in a text file.
	 * Then we 'dispose' the frame, which means that we remove all graphical components without closing the program.
	 * And after, the program end ( System.exit(0) ).
	 */
	public void exitGame() 
	{		
		model.setHighScores(false);
		highScore.save();
		frame.dispose();
		System.exit(0);
	}
	

}
