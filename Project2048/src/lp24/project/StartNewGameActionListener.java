package lp24.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 
public class StartNewGameActionListener implements ActionListener {
     
    private Frame frame;
     
    private Model model;
     
    public StartNewGameActionListener(Frame frame, Model model) {
        this.frame = frame;
        this.model = model;
    }
 
    @Override
    public void actionPerformed(ActionEvent event) {
    	model.initialization();
    	model.setHighScores(true);
        model.setArrow(true);
        model.addTile();
        model.addTile();
        model.setWinColor(TileColor.black);
        model.setPause(false);
         
        frame.repaintPanel();
        frame.updateScore();
    }
 
}
