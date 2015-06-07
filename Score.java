package lp24.project;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Score {

	private Model model;
	private JPanel panel;
	private JTextField globalHighestScoreField;
	private JTextField globalHighestTileField;
	private JTextField localScoreField;
	private JTextField localHighestTileField;
	private static final NumberFormat numformat = NumberFormat.getInstance();
	


	
	public Score(Model model)
	{
		this.model = model;
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		JLabel globalHighScoreLabel = new JLabel("Global Highest Score");
		addComponent(panel, globalHighScoreLabel, 0, 0, 1, 1, new Insets(10,10,0,10),GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		globalHighestScoreField = new JTextField(6);
		globalHighestScoreField.setEditable(false);
		globalHighestScoreField.setHorizontalAlignment(JTextField.RIGHT);
		addComponent(panel, globalHighestScoreField, 1,0,1,1,new Insets(10,10,0,10),GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		JLabel globalHigherTileLabel = new JLabel("Global Highest Tile");
		addComponent(panel, globalHigherTileLabel, 0, 1, 1, 1, new Insets(10,10,10,10),GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		globalHighestTileField = new JTextField(6);
		globalHighestTileField.setEditable(false);
		globalHighestTileField.setHorizontalAlignment(JTextField.RIGHT);
		addComponent(panel, globalHighestTileField, 1,1,1,1,new Insets(10,10,10,10),GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		JLabel localHighScoreLabel = new JLabel("Score");
		addComponent(panel, localHighScoreLabel, 0, 2, 1, 1, new Insets(10,10,0,10),GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		localScoreField = new JTextField(6);
		localScoreField.setEditable(false);
		localScoreField.setHorizontalAlignment(JTextField.RIGHT);
		addComponent(panel, localScoreField, 1,2,1,1,new Insets(10,10,0,10),GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		JLabel localHighestTileLabel = new JLabel("Highest Tile");
		addComponent(panel, localHighestTileLabel, 0, 3, 1, 1, new Insets(10,10,0,10),GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		localHighestTileField = new JTextField(6);
		localHighestTileField.setEditable(false);
		localHighestTileField.setHorizontalAlignment(JTextField.RIGHT);
		addComponent(panel,localHighestTileField, 1,3,2,1,new Insets(10,10,0,10),GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL);
		
		
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
	
	public void updatePartControl(){
		globalHighestScoreField.setText(numformat.format(model.getGlobalHighestScore()));
		globalHighestTileField.setText(numformat.format(model.getGlobalHighestTile()));
		localScoreField.setText(numformat.format(model.getLocalScore()));
		localHighestTileField.setText(numformat.format(model.getLocalHighestTile()));
	}
	
	
}
