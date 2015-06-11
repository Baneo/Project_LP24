package lp24.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This class corresponds to the listener associated to the "start new game" button, which start
 * a new game from scratch, erasing the previous one, as well as setting to zero the local scores,
 * before saving them of course
 */
public class StartNewGameActionListener implements ActionListener {
     
    private Frame frame;
     
    private Model model;
     
    public StartNewGameActionListener(Frame frame, Model model) {
        this.frame = frame;
        this.model = model;
    }
 
    @Override
    public void actionPerformed(ActionEvent event) {
    	/*
    	 * When the user click on this button, the following actions are performed :
    	 *  1) the grid is re-initialized (tiles' values set to zero, tiles' colors set to black)
    	 *  2) highScores are updated(true is given in parameter because it's a new game, it'll set the "local" score values to zero)
    	 *  3) arrow is set to true to allow the user to make his first move. (he can't if arrow's value is not true)
    	 *  4) 2 new tiles are created and added to the grid.
    	 *  5) winColor is set to black (winColor is the color of the first 2048 tile in this game).
    	 *  6) the pause associated value is set to false, which will remove the pause state if it was active.
    	 *  7) the screen is updated, displaying all previous changes (from step 1  to step 6)
    	 *  8) score panel is updated with the value changed in step 1).
    	 */
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
