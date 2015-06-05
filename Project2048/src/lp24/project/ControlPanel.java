package lp24.project;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel {

	private Frame frame;
	private Model model;
	private JPanel panel;
	
	public ControlPanel(Frame frame, Model model)
	{
		this.frame = frame;
		this.model = model;
		
					//StartGameActionListener actionListener = new StartGameActionListener(frame, model);
					
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		JButton startButton = new JButton("Start The Game");
		
					//startButton.addActionListener(actionListener);
					
		int x = 0;
		int y = 1;
		int width = 1;
		int height = 1;
		Insets inset = new Insets(10, 10, 0, 10);;
		int anchor = GridBagConstraints.LINE_START;
		int fill = GridBagConstraints.HORIZONTAL;
		GridBagConstraints gridBagConstraints = new GridBagConstraints(x,y,width,height,1.0D,1.0D,anchor,fill,inset,0,0);
		panel.add(startButton, gridBagConstraints);
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
	
}
