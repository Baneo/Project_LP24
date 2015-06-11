package lp24.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/*
 * This class handle the save/load process for the scores. These data are exchanged with the Model Class.
 */
public class HighScore {
	/*
	 * All these strings corresponds to the field where the Model's variables will be stored (inside the save file).
	 */
	private static final String grid = "grid";
	private static final String globalHighestTile = "highestTile";
	private static final String globalHighestScore = "highestScore";
	private static final String localHighestTile = "localHighestTile";
	private static final String localScore = "localScore";
	private static final String blueWins = "blueWins";
    private static final String redWins = "redWins";
    private static final String winColor = "winColor";
	
	private Model model;
	
	
	
	public HighScore(Model model)
	{
		this.model = model;
	}
	
	/*
	 * Method that will load data from the file to the Model object.
	 */
	public void load()
	{
		Properties properties = new Properties();
		InputStream input = null;
        File file = new File("jjq.data"); // "jjq.data" is the name of the file where the data are saved.
        try {
            input = new FileInputStream(file);
            properties.load(input);
            
            /*
             *  for each loaded data, we call the corresponding setter from the Model Class, with the data in parameter
             */
            model.loadGrid(properties.getProperty("grid"));
            model.setGlobalHighestScore(Integer.parseInt(properties.getProperty(globalHighestScore)));
            model.setGlobalHighestTile(Integer.parseInt(properties.getProperty(globalHighestTile)));
            model.setLocalScore(Integer.parseInt(properties.getProperty(localScore)));
            model.setLocalHighestTile(Integer.parseInt(properties.getProperty(localHighestTile)));
            model.setBlueWins(Integer.parseInt(properties.getProperty(blueWins)));
            model.setRedWins(Integer.parseInt(properties.getProperty(redWins)));
            model.setWinColor(properties.getProperty(winColor));
        } catch (FileNotFoundException exception) {
             
        } catch (IOException exception) {
            exception.printStackTrace();
        }
	}
	
	public void save()
	{
		/*
		 * for each data we want to save, we call the corresponding getter, which is associated
		 * to matching string (declarations are located above the methods), and are added to a 
		 * Properties object. These properties will be stored in the file in the try{} field.
		 */
		Properties properties = new Properties();
		properties.setProperty(globalHighestScore, Integer.toString(model.getGlobalHighestScore()));
        properties.setProperty(globalHighestTile, Integer.toString(model.getGlobalHighestTile()));
        properties.setProperty(localScore, Integer.toString(model.getLocalScore()));
        properties.setProperty(localHighestTile, Integer.toString(model.getLocalHighestTile()));
        properties.setProperty(blueWins, Integer.toString(model.getBlueWins()));
        properties.setProperty(redWins, Integer.toString(model.getRedWins()));
        properties.setProperty(grid, model.toStringGrid());
        properties.setProperty(winColor, model.getWinColor().toString());
        
        OutputStream output = null;
        File file = new File("jjq.data");
         
        try {
            output = new FileOutputStream(file);
            properties.store(output, "2048 High Score");
            output.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        
	}

}
