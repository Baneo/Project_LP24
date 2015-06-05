package lp24.project;

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame {
	
	private ControlPanel controlPanel;
	private Model model;
	private Panel panel;
	private HighScore highScore;
	private JFrame frame;
	private Score score;
	
	public Frame (Model model)
	{
		this.model = model;
		this.highScore = new HighScore(model);
		this.highScore.load();
		createPartControl();
	}
	
	private void createPartControl()
	{
		panel = new Panel(model);
		score = new Score(model);
		controlPanel = new ControlPanel(this, model);
		
		frame = new JFrame();
		frame.setTitle("Colored 2048");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		/*frame.addWindowFocusListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent event)
			{
				model.setHighScores();
				highScore.saveProperties();
				frame.dispose();
				System.exit(0);
			}
			
		});*/
		
		//setKeyBindings();
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		mainPanel.add(panel);
		
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
		sidePanel.add(score.getPanel());
		sidePanel.add(Box.createVerticalStrut(30));
		sidePanel.add(controlPanel.getPanel());
		
		mainPanel.add(sidePanel);
		
		frame.add(mainPanel);
		frame.setLocationByPlatform(true);
		frame.pack();
		frame.setVisible(true);
		
		/*private void setKeyBindings()
		{
			Inputs inputs = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
			inputs.put(KeyStroke.getKeyStroke("UP"), "up arrow");
			inputs.put(KeyStroke.getKeyStroke("DOWN"), "down arrow");
			inputs.put(KeyStroke.getKeyStroke("LEFT"), "left arrow");
			inputs.put(KeyStroke.getKeyStroke("RIGHT"), "right arrow");
			
			inputs.panel.getInputMap(JPanel.WHEN_FOCUSED);
			inputs.put(KeyStroke.getKeyStroke("UP"), "up arrow");
			inputs.put(KeyStroke.getKeyStroke("DOWN"), "down arrow");
			inputs.put(KeyStroke.getKeyStroke("LEFT"), "left arrow");
			inputs.put(KeyStroke.getKeyStroke("RIGHT"), "right arrow");
			
			panel.getActionMap().put("up arrow", new UpAction(this, model));
			panel.getActionMap().put("down arrow", new DownAction(this, model));
			panel.getActionMap().put("left arrow", new LeftAction(this, model));
			panel.getActionMap().put("right arrow", new RightAction(this, model));
		
		}*/
		
	}

}
