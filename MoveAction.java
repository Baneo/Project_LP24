package lp24.project;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

/*
 * This class corresponds to the listeners associated to all the keyboard inputs from the user.
 */
public class MoveAction extends AbstractAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3057256846265903098L;
	
	private Frame frame;
	private Model model;
	private Direction direction;
	
	public MoveAction(Frame frame, Model model, Direction direction){
		this.frame=frame;
		this.model=model;
		this.direction=direction;
	}
	
	public void actionPerformed(ActionEvent event){
		/*
		 * When the user hit a binded key on his keyboard, the switch will decide which key was hit,
		 * and will move all tiles if possible (if nothing moves, a new tile isn't added on the grid).
		 * After that, if the game is in Game Over state, the move is ended and the game over is detected
		 * and displayed on the user's screen. If it's not, a new tile is added on the grid.
		 */
		if(model.isArrow()){	
			switch (direction) {
			case up :	if(model.moveUp()){
							if(model.isGameOver()){
								model.setArrow(false);
							}
							else{
								addNewTile();
							}
						}
				break;
			case down :	if(model.moveDown()){
							if(model.isGameOver()){
								model.setArrow(false);
							}
							else{
								addNewTile();
							}
					}
				break;
			case left :	if(model.moveLeft()){
							if(model.isGameOver()){
								model.setArrow(false);
							}
							else{
								addNewTile();
							}
						}
				break;
			case right : if(model.moveRight()){
							if(model.isGameOver()){
								model.setArrow(false);
							}
							else{
								addNewTile();
							}
						}
				break;
			}//end of switch
		}// end of if
		
	}// end of "actionPerformed"

	
	/*
	 * Method that add a new tile and update the screen and the score panel.
	 */
	private void addNewTile(){
		model.addTile();
		frame.repaintPanel();
		frame.updateScore();
	}
}
