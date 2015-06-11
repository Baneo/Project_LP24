package lp24.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This class corresponds to the listener associated to the "continue previous game" button.
 */
 
public class ContinueGameActionListener implements ActionListener {
     
    private Frame frame;
    private Model model;
     
    public ContinueGameActionListener(Frame frame, Model model) {
        this.frame = frame;
        this.model = model;
        
    }
 
    @Override
    public void actionPerformed(ActionEvent event) {
    	/*
    	 * When the user click on the "continue previous game" button :
    	 * 	1) the different highscores hidden values are updated, with false in parameter because it is not a new game (true would have set all local scores to zero
    	 *  2) displayed scores are updated    	 
    	 *  3) the pause is unset (the game's in pause when a previous grid is loaded from the save file and when this grid isn't in game over state)
    	 *  4) grid's graphics are updated
    	 *  5)  we set arrow to true because a listener is called.
    	 */ 
    	model.setHighScores(false);
    	frame.updateScore();
        model.setPause(false);
        frame.repaintPanel();
         model.setArrow(true);
    }
 
}
