package filehandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import game.GameVariables;
import pbpparser.VariableHolder;

/**
 * This will read in the file with all the dice rolls, event power lvls ect
 * @author jravi
 *
 */
public class GameScoreHandler {
	
	private final Logger log = LoggerFactory.getLogger(GameScoreHandler.class);
	
	private static VariableHolder myInstance;
	
	/**
	 * read in the game file and parse into a POJO
	 */
	public GameScoreHandler()
	{
		myInstance = VariableHolder.getInstance();
	}
	
	public GameVariables readGameVariables(String fileName)
	{
		GameVariables gameVariables = null;

		BufferedReader br = null;
		FileReader fr = null;

		StringBuilder builder = new StringBuilder();

		try 
		{
			//read in the file
			fr = new FileReader(myInstance.getGamePath() + "/" + fileName);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				builder.append(sCurrentLine);
			}
		} catch (IOException e) 
		{
			log.error("Unable to read in game file.  Check that -Dfile.path is set correctly");
			e.printStackTrace();
		} finally 
		{
			try 
			{
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
		if ( builder.length() > 0 )
		{
			GsonBuilder gbuilder = new GsonBuilder();
			Gson gson = gbuilder.create();
			gameVariables = gson.fromJson(builder.toString(), GameVariables.class);
		}
		return gameVariables;
	}
	
	public boolean writeOutToFile(String fileName, GameVariables gameVar)
	{
		boolean success = false;
		//each player file will have its own obj so set player = playerStats
		GsonBuilder gbuilder = new GsonBuilder();
		Gson gson = gbuilder.setPrettyPrinting().create();
		String stringToPrintToFile = gson.toJson(gameVar);
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(myInstance.getGamePath() + "/" + fileName);
			bw = new BufferedWriter(fw);
			
			bw.write(stringToPrintToFile);
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally 
		{
			try 
			{
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
		return success;
	}

}
