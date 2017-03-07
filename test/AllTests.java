import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import filehandler.VariableHolder;
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
	public void printFile()
	{
		BufferedReader br = null;
		FileReader fr = null;
		VariableHolder v = VariableHolder.getInstance();

		StringBuilder builder = new StringBuilder();

		try 
		{
			fr = new FileReader(v.getGamePath() + "/zugdush.txt");
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				builder.append(sCurrentLine);
			}
		} catch (IOException e) 
		{
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
			PlayerStats filePlayer = gson.fromJson(builder.toString(), PlayerStats.class);
			System.out.println(gson.toJson(filePlayer));
		}
	}
	
	@Test
	public void testRandomNum()
	{
		VariableHolder v = VariableHolder.getInstance();
		int count = 0;
		int a = v.popD20();
		int b = v.popD20();
		int min = 20;
		int max = 1;
		
		while( count < 1000 )
		{
			//System.out.println(a);
			while( a == b )
			{
				b = v.popD20();
			}
			a = b;
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
		System.out.println(min+","+max);
	}

}
