package filehandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import core.VariableHolder;
import player.PlayerStats;

/**
 * file reader for the PlayerStats obj
 * @author jravi
 *
 */
public class PlayerFileHandler {
	
	private final Logger log = LoggerFactory.getLogger(PlayerFileHandler.class);

	private static VariableHolder myInstance;
	
	public PlayerFileHandler()
	{
		myInstance = VariableHolder.getInstance();
	}
	
	public PlayerStats readPlayerFile(String fileName) 
	{
		File myFile = null;
		BufferedReader br = null;
		FileReader fr = null;
		
		PlayerStats playerStats = null;

		StringBuilder builder = new StringBuilder();

		try 
		{
			myFile = new File(myInstance.getGamePath() + "/" + fileName);
			if( !myFile.isFile() )
			{
				return null;
			}
			fr = new FileReader(myFile);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				builder.append(sCurrentLine);
			}
		} catch (IOException e) 
		{
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
		return playerStats;
	}
	
	public boolean writeOutToFile(String fileName, PlayerStats player)
	{
		boolean success = false;
		//each player file will have its own obj so set player = playerStats
		GsonBuilder gbuilder = new GsonBuilder();
		Gson gson = gbuilder.setPrettyPrinting().disableHtmlEscaping().create();
		FileWriter fw = null;
		
		JsonWriter jsonWriter = null;
		try {
			fw = new FileWriter(myInstance.getGamePath() + "/" + fileName);
			jsonWriter = new JsonWriter(fw);
			jsonWriter.setIndent(" ");
			gson.toJson(player,PlayerStats.class,jsonWriter);
			success = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally 
		{
			try 
			{
				if (fw != null)
					fw.close();
				if (jsonWriter != null)
					jsonWriter.close();
			} catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
		return success;
	}

	public boolean createNewCharacterDir(PlayerStats player) {
		if( !player.valid() )
		{
			return false;
		}
		//check if dir already exists
		File charDir;
		
		charDir = new File(myInstance.getGamePath() + "/" + player.getName() );
		
		//return if dir already exists since this means the player is not new
		if ( !charDir.exists() )
		{
			try
			{
				charDir.mkdir();
			} catch( SecurityException e )
			{
				log.error("Could not make dir: " + e.getMessage());
				return false;
			}
		}
		
		return this.writeOutToFile(player.getName() + "/" + player.getName() + ".txt" , player);
	}
}
