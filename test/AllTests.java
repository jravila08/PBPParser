import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

import event.EventManager;
import filehandler.FolderManager;
import filehandler.GameScoreHandler;
import filehandler.PlayerFileHandler;
import game.GameVariables;
import pbpparser.VariableHolder;
import player.LuckCalculator;
import player.PlayerStats;
import player.WeeklyScore;

public class AllTests {
	
	@Test
	public void gameWriteReadTest()
	{
		String testFileName = "gameTestFile.txt";
		GameVariables gv = new GameVariables();
		
		LinkedList<Integer> D20 = new LinkedList<Integer>();
		for(int x = 10; x < 20; x++)
		{
			D20.push(new Integer(x));
		}
		LinkedList<Integer> D4 = new LinkedList<Integer>();
		for(int x = 1; x < 5; x++)
		{
			D4.push(new Integer(x));
		}
		
		gv.setD20(D20);
		gv.setD4(D4);
		
		GameScoreHandler gsh = new GameScoreHandler();
		gsh.writeOutToFile(testFileName, gv);
		
		GameVariables gvReadFromFile = gsh.readGameVariables(testFileName);
		
		while(gv.hasMoreD20())
		{
			assertTrue(gv.getD20Roll() == gvReadFromFile.getD20Roll());
		}
		
		while(gv.hasMoreD4())
		{
			assertTrue(gv.getD4Roll() == gvReadFromFile.getD4Roll());
		}
	}
	
	@Test
	public void playerWriteReadTest()
	{
		PlayerStats player = new PlayerStats();
		player.populateTestObject();
	
		PlayerFileHandler pFile = new PlayerFileHandler();
		
		pFile.writeOutToFile(player.getName() + ".txt",player);
		
		PlayerStats readPlayer = pFile.readPlayerFile(player.getName() + ".txt");
		
		assertNotNull(readPlayer);
	}
	
	@Test
	public void testFolderManager()
	{
		FolderManager fm = new FolderManager();
		for ( String s : fm.getFolders() )
		{
			System.out.println("Active players with folders: " + s);
		}
		
		System.out.println(fm.getGameVariables());
		System.out.println(fm.getPlayerList());
	}
	
	@Test
	public void createNewPlayerHierarchy()
	{
		FolderManager fm = new FolderManager();
		PlayerStats player = new PlayerStats();
		player.populateTestObject();
		
		assertTrue(fm.createNewCharacterDir(player));
		
		File f = new File(VariableHolder.getInstance().getGamePath() + "/" + player.getName());
		
		assertTrue(f.isDirectory());
		
		File f2 = new File(VariableHolder.getInstance().getGamePath() + "/" + player.getName() 
						+ "/" + player.getName() + ".txt");
		
		assertTrue(f2.isFile());
		
		f2.delete();
		f.delete();
	}
	
	@Test
	public void testEvent()
	{
		EventManager evt = new EventManager();
		evt.startNewEvent();
	}
}
