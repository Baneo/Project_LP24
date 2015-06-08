package lp24.project;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;


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

	
	private void addNewTile(){
		model.addTile();
		frame.repaintPanel();
		frame.updateScore();
	}
}
