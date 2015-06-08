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
	private JPanel start, cont, exit;
	
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
			start = new JPanel();
			start.setLayout(new GridBagLayout());
			JButton startButton = new JButton("Start New Game");
			StartNewGameActionListener actionListenerStart = new StartNewGameActionListener(frame, model);
			startButton.addActionListener(actionListenerStart);
			GridBagConstraints gridBagConstraintsStart = new GridBagConstraints(x,y,width,height,1.0D,1.0D,anchor,fill,inset,0,0);
			start.add(startButton, gridBagConstraintsStart);
		
		
		// lignes de code pour le bouton "continue previous game"
			cont = new JPanel();
			cont.setLayout(new GridBagLayout());
			JButton continueButton = new JButton("Continue Previous Game");
			ContinueGameActionListener actionListenerContinue = new ContinueGameActionListener(frame, model);
			continueButton.addActionListener(actionListenerContinue);
			GridBagConstraints gridBagConstraintsContinue = new GridBagConstraints(x,y+1,width,height,1.0D,1.0D,anchor,fill,inset,0,0);
			cont.add(continueButton, gridBagConstraintsContinue);
			
			//lignes du bouton exit
			
			exit = new JPanel();
			exit.setLayout(new GridBagLayout());
			JButton exitButton = new JButton("Exit Game");
			ExitGameActionListener actionListenerExit = new ExitGameActionListener(frame, model);
			exitButton.addActionListener(actionListenerExit);
			GridBagConstraints gridBagConstraintsExit = new GridBagConstraints(x,y+2,width,height,1.0D,1.0D,anchor,fill,inset,0,0);
			exit.add(exitButton, gridBagConstraintsExit);
	}
	
	public JPanel getStart()
	{
		return start;
	}
	
	public JPanel getContinue()
	{
		return cont;
	}
	
	public JPanel getExit()
	{
		return exit;
	}
	
}
