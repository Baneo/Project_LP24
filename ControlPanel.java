package lp24.project;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel {

	private static final Insets regularInsets = new Insets(10, 10, 0, 10);
	private Frame frame;
	private Model model;
	private JPanel panel1, panel2;
	
	public ControlPanel(Frame frame, Model model)
	{
		this.frame = frame;
		this.model = model;
		
		int x = 0;
		int y = 1;
		int width = 1;
		int height = 1;
		Insets inset = regularInsets;
		int anchor = GridBagConstraints.LINE_START;
		int fill = GridBagConstraints.HORIZONTAL;
		
		// lignes de code pour le bouton " start new game"
			panel1 = new JPanel();
			panel1.setLayout(new GridBagLayout());
			JButton startButton = new JButton("Start New Game");
			StartNewGameActionListener actionListenerStart = new StartNewGameActionListener(frame, model);
			startButton.addActionListener(actionListenerStart);
			GridBagConstraints gridBagConstraintsStart = new GridBagConstraints(x,y,width,height,1.0D,1.0D,anchor,fill,inset,0,0);
			panel1.add(startButton, gridBagConstraintsStart);
		
		
		// lignes de code pour le bouton "continue previous game"
			panel2 = new JPanel();
			panel2.setLayout(new GridBagLayout());
			JButton continueButton = new JButton("Continue Previous Game");
			ContinueGameActionListener actionListenerContinue = new ContinueGameActionListener(frame, model);
			continueButton.addActionListener(actionListenerContinue);
			GridBagConstraints gridBagConstraintsContinue = new GridBagConstraints(x,y+1,width,height,1.0D,1.0D,anchor,fill,inset,0,0);
			panel2.add(continueButton, gridBagConstraintsContinue);
	}
	
	public JPanel getPanel1()
	{
		return panel1;
	}
	
	public JPanel getPanel2()
	{
		return panel2;
	}
	
}
