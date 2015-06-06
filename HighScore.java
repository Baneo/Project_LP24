package lp24.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class HighScore {
	
	private static final String grid = "grid";
	private static final String highestTile = "highestTile";
	private static final String highestScore = "highestScore";
	private static final String localHighestTile = "localHighestTile";
	private static final String localScore = "localScore";
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
            model.loadGrid(properties.getProperty("grid"));
            model.setGlobalHighestScore(Integer.parseInt(properties.getProperty("globalHighestScore")));
            model.setGlobalHighestTile(Integer.parseInt(properties.getProperty("globalHighestTile")));
            model.setLocalScore(Integer.parseInt(properties.getProperty("localScore")));
            model.setLocalHighestTile(Integer.parseInt(properties.getProperty("localHighestTile")));
            model.setGameWonBlue(Integer.parseInt(properties.getProperty("gameWonBlue")));
            model.setGameWonRed(Integer.parseInt(properties.getProperty("gameWonRed")));
        } catch (FileNotFoundException exception) {
             
        } catch (IOException exception) {
            exception.printStackTrace();
        }
	}
	
	public void save()
	{
		Properties properties = new Properties();
		properties.setProperty("globalHighestScore", Integer.toString(model.getGlobalHighestScore()));
        properties.setProperty("globalHighestTile", Integer.toString(model.getGlobalHighestTile()));
        properties.setProperty("localScore", Integer.toString(model.getLocalScore()));
        properties.setProperty("localHighestTile", Integer.toString(model.getLocalHighestTile()));
        properties.setProperty("gameWonBlue", Integer.toString(model.getGameWonBlue()));
        properties.setProperty("gameWonRed", Integer.toString(model.getGameWonRed()));
        properties.setProperty("grid", model.toStringGrid());
        
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
