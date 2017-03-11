package filehandler;

import java.io.BufferedReader;
import java.io.FileReader;
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
public class GameScoreReader {
	
	private final Logger log = LoggerFactory.getLogger(GameScoreReader.class);
	
	private static VariableHolder myInstance;

	//game variables, dice event difficulties ect
	private GameVariables gameVariables;

	/**
	 * read in the game file and parse into a POJO
	 */
	public GameScoreReader()
	{
		myInstance = VariableHolder.getInstance();

		BufferedReader br = null;
		FileReader fr = null;

		StringBuilder builder = new StringBuilder();

		try 
		{
			//read in the file
			fr = new FileReader(myInstance.getGamePath() + "/" + myInstance.d20FileName);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				builder.append(sCurrentLine);
			}
		} catch (IOException e) 
		{
			gameVariables = null;
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

	}

	/**
	 * get the GameVariables object that is populated in the constructor
	 * @return GameVariables populated from the game file
	 */
	public GameVariables getGameVariables() {
		return gameVariables;
	}

}
