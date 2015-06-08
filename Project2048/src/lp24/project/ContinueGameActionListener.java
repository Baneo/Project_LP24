package lp24.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 
public class ContinueGameActionListener implements ActionListener {
     
    private Frame frame;
    private PauseScreen pause; 
    private Model model;
     
    public ContinueGameActionListener(Frame frame, Model model) {
        this.frame = frame;
        this.model = model;
        
    }
 
    @Override
    public void actionPerformed(ActionEvent event) {
    	model.setHighScores(false);
        model.setArrow(true);
        model.setPause(false);
        frame.repaintPanel();
        frame.updateScore();
    }
 
}
