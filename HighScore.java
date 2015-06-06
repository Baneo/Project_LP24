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
            model.setGlobalHighestScore(Integer.parseInt(properties.getProperty("highestScore")));
            model.setGlobalHighestTile(Integer.parseInt(properties.getProperty("highestTile")));
            model.setLocalScore(Integer.parseInt(properties.getProperty("localScore")));
            model.setLocalHighestTile(Integer.parseInt(properties.getProperty("localHighestTile")));
        } catch (FileNotFoundException exception) {
             
        } catch (IOException exception) {
            exception.printStackTrace();
        }
	}
	
	public void save()
	{
		Properties properties = new Properties();
        properties.setProperty("highScore", Integer.toString(model.getGlobalHighestScore()));
        properties.setProperty("highCell", Integer.toString(model.getGlobalHighestTile()));
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
