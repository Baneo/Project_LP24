package lp24.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class HighScore {
	
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
	
	public void load()
	{
		Properties properties = new Properties();
		InputStream input = null;
        File file = new File("jjq.data");
        try {
            input = new FileInputStream(file);
            properties.load(input);
            
            // COMMENTAIRES A ENLEVER APRES LA PREMIERE SAUVEGARDE DE PARTIE : PAS DE SAVE -> NULLPOINTER EXCEPTION AU LANCEMENET
            /*model.loadGrid(properties.getProperty("grid"));
            model.setGlobalHighestScore(Integer.parseInt(properties.getProperty(globalHighestScore)));
            model.setGlobalHighestTile(Integer.parseInt(properties.getProperty(globalHighestTile)));
            model.setLocalScore(Integer.parseInt(properties.getProperty(localScore)));
            model.setLocalHighestTile(Integer.parseInt(properties.getProperty(localHighestTile)));
            model.setBlueWins(Integer.parseInt(properties.getProperty(blueWins)));
            model.setRedWins(Integer.parseInt(properties.getProperty(redWins)));
            model.setWinColor(properties.getProperty(winColor));*/
        } catch (FileNotFoundException exception) {
             
        } catch (IOException exception) {
            exception.printStackTrace();
        }
	}
	
	public void save()
	{
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
