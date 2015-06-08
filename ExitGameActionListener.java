package lp24.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitGameActionListener implements ActionListener{

	private Frame frame;
    private PauseScreen pause; 
    private Model model;
     
    public ExitGameActionListener(Frame frame, Model model) {
        this.frame = frame;
        this.model = model;
        
    }
 
    @Override
    public void actionPerformed(ActionEvent event) {
    	
    	frame.exitGame();
    	
    }
}
