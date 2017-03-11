import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import filehandler.GameScoreReader;
import filehandler.PlayerFileReader;
import game.GameVariables;
import player.PlayerStats;

public class AllTests {

	@Test
	public void convertJsonTest()
	{
		PlayerStats start = new PlayerStats();
		start.name = "Test";
		start.intel = 9001;
		start.str = 9001;
		start.luck = 9001;
		start.pr = 9001;
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String javaToJson = gson.toJson(start);

		PlayerStats filePlayer = gson.fromJson(javaToJson, PlayerStats.class);

		assertEquals(start.name,filePlayer.name);
		assertEquals(start.intel,filePlayer.intel);
		assertEquals(start.str,filePlayer.str);
		assertEquals(start.luck,filePlayer.luck);
		assertEquals(start.pr,filePlayer.pr);
	}
	
	@Test
	public void testPlayerReader()
	{
		PlayerFileReader pfr = new PlayerFileReader();
		PlayerStats stats = pfr.getPlayerStats();
		assertTrue(stats.intel > 0);
		assertTrue(stats.luck > 0);
		assertTrue(!stats.name.isEmpty());
		assertTrue(stats.pr > 0);
		assertTrue(stats.str > 0);
		assertTrue(stats.brave > 0);
	}

	@Test
	public void printDiceFile()
	{
		GameScoreReader gsReader = new GameScoreReader();
		GameVariables dice = gsReader.getGameVariables();

		System.out.println(dice);
		int count = 0;
		int min = 20;
		int max = 0;
		
		while( dice.hasMoreD20() )
		{
			int a = dice.getD20Roll();
			if ( a > max )
			{
				max = a;
			}
			if ( a < min )
			{
				min = a;
			}
			count++;
		}
		assertTrue(min > 0);
		assertTrue(max < 21);
		System.out.println(min+","+max+","+count);
	}
}
