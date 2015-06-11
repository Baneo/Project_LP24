package lp24.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * This class corresponds to the listener associated to the "exit" button, which close the game on click.
 */

public class ExitGameActionListener implements ActionListener{

	private Frame frame;
     
    public ExitGameActionListener(Frame frame) {
        this.frame = frame;
        
    }
 
    @Override
    public void actionPerformed(ActionEvent event) {
    	/*
    	 * When the user click on this button, the method call the exitGame method in the Frame Class,
    	 * which handle the closing behavior of the software.
    	 */
    	frame.exitGame();
    	
    }
}
