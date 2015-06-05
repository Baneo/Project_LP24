package lp24.project;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Score {

	private Model model;
	private JPanel panel;
	private JTextField globalHighScoreField;
	private JTextField globalHigherTileField;
	private JTextField localHighScoreField;
	private JTextField localHigherTileField;
	


	
	public Score(Model model)
	{
		this.model = model;
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		JLabel globalHighScoreLabel = new JLabel("Global High Score");
		addComponent(panel, globalHighScoreLabel, 0, 0, 1, 1, new Insets(10,10,0,10),GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		globalHighScoreField = new JTextField(6);
		globalHighScoreField.setEditable(false);
		globalHighScoreField.setHorizontalAlignment(JTextField.RIGHT);
		addComponent(panel, globalHighScoreField, 1,1,1,1,new Insets(10,10,0,10),GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		JLabel globalHigherTileLabel = new JLabel("Global Higher Tile");
		addComponent(panel, globalHigherTileLabel, 0, 0, 1, 1, new Insets(10,10,10,10),GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		globalHigherTileField = new JTextField(6);
		globalHigherTileField.setEditable(false);
		globalHigherTileField.setHorizontalAlignment(JTextField.RIGHT);
		addComponent(panel, globalHigherTileField, 1,1,1,1,new Insets(10,10,10,10),GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		JLabel localHighScoreLabel = new JLabel("Global High Score");
		addComponent(panel, localHighScoreLabel, 0, 0, 1, 1, new Insets(10,10,0,10),GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		localHighScoreField = new JTextField(6);
		localHighScoreField.setEditable(false);
		localHighScoreField.setHorizontalAlignment(JTextField.RIGHT);
		addComponent(panel, localHighScoreField, 1,1,1,1,new Insets(10,10,0,10),GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		JLabel localHigherTileLabel = new JLabel("Global High Score");
		addComponent(panel, localHigherTileLabel, 0, 0, 1, 1, new Insets(10,10,0,10),GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		localHigherTileField = new JTextField(6);
		localHigherTileField.setEditable(false);
		localHigherTileField.setHorizontalAlignment(JTextField.RIGHT);
		addComponent(panel,localHigherTileField, 1,1,1,1,new Insets(10,10,0,10),GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		
	}
	
	private void addComponent(Container container, Component component, int x, int y, int width, int height, Insets inset, int anchor, int fill)
	{
		GridBagConstraints gridBagConstraint = new GridBagConstraints(x, y, width, height, 1.0D, 1.0D, anchor, fill, inset, 0,0);
		container.add(component,  gridBagConstraint);
	}

	public JPanel getPanel()
	{
		return panel;
	}
	
	
}
