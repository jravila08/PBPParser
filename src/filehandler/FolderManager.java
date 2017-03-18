package filehandler;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.GameVariables;
import pbpparser.VariableHolder;
import player.PlayerStats;

public class FolderManager {
	
	private final Logger log = LoggerFactory.getLogger(FolderManager.class);

	private VariableHolder myInstance;
	
	private String[] myFolders;
	
	private GameVariables myGameScore;
	private ArrayList<PlayerStats> myPlayerStats;
	
	public FolderManager()
	{
		myInstance = VariableHolder.getInstance();
		this.findFolders();
		
		myGameScore = new GameScoreHandler().readGameVariables(myInstance.d20FileName);
		myPlayerStats = new ArrayList<PlayerStats>();
		
		PlayerFileHandler pHandler = new PlayerFileHandler();
		for( String s : myFolders )
		{
			PlayerStats p = pHandler.readPlayerFile(s+"/"+s+ ".txt" );
			if( p != null )
			{
				myPlayerStats.add(p);
			}
		}
	}
	
	private void findFolders()
	{
		File myFile = new File(myInstance.getGamePath());
		myFolders = myFile.list(new FilenameFilter() {
		  @Override
		  public boolean accept(File current, String name) {
		    return new File(current, name).isDirectory();
		  }
		});
	}
	
	public String[] getFolders()
	{
		return myFolders;
	}
	
	public List<PlayerStats> getPlayerList()
	{
		return myPlayerStats;
	}
	
	public GameVariables getGameVariables()
	{
		return myGameScore;
	}
	
	
	public boolean createNewCharacterDir(PlayerStats player) 
	{
		if( !player.valid() )
		{
			return false;
		}
		//check if dir already exists
		File charDir;
		
		charDir = new File(myInstance.getGamePath() + "/" + player.getName() );
		
		//return if dir already exists since this means the player is not new
		if ( charDir.exists() )
		{
			return false;
		}
		
		try
		{
			charDir.mkdir();
		} catch( SecurityException e )
		{
			log.error("Could not make dir: " + e.getMessage());
			return false;
		}
		
		PlayerFileHandler pHandler = new PlayerFileHandler();
		return pHandler.writeOutToFile(player.getName() + "/" + player.getName() + ".txt" , player);
	}

}
