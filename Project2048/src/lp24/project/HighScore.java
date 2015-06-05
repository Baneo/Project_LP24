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
	
	private static final String higherTile = "higherTile";
	private static final String highScore = "highScore";
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
            model.setHigherScore(Integer.parseInt(properties.getProperty("highScore")));
            model.setHigherTile(Integer.parseInt(properties.getProperty("higherTile")));
        } catch (FileNotFoundException exception) {
             
        } catch (IOException exception) {
            exception.printStackTrace();
        }
	}
	
	public void save()
	{
		Properties properties = new Properties();
        properties.setProperty("highScore", Integer.toString(model.getHigherScore()));
        properties.setProperty("highCell", Integer.toString(model.getHigherTile()));
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
