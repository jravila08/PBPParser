package filehandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pbpparser.VariableHolder;
import player.PlayerStats;

/**
 * file reader for the PlayerStats obj
 * @author jravi
 *
 */
public class PlayerFileReader {
	
	private final Logger log = LoggerFactory.getLogger(PlayerFileReader.class);

	private static VariableHolder myInstance;

	private PlayerStats playerStats;
	
	public PlayerFileReader()
	{
		myInstance = VariableHolder.getInstance();

		BufferedReader br = null;
		FileReader fr = null;

		StringBuilder builder = new StringBuilder();

		try 
		{
			//TODO change the hardcoded file name to a variable so that each player's folder name goes here
			fr = new FileReader(myInstance.getGamePath() + "/zugdush.txt");
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				builder.append(sCurrentLine);
			}
		} catch (IOException e) 
		{
			playerStats = null;
			log.error("Unable to read in player file.  Please check that -Dfile.path is set correctly");
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
			playerStats = gson.fromJson(builder.toString(), PlayerStats.class);
		}
	}
	
	/**
	 * Returns PlayerStats obj
	 * @return PlayerStats obj populated in the constructor
	 */
	public PlayerStats getPlayerStats()
	{
		return playerStats;
	}
}
