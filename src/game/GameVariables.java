package game;

import java.util.LinkedList;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This is the class that holds all of the GM info, avail dice rolls, event difficulty, ect
 * @author jravi
 *
 */
public class GameVariables {
	
	LinkedList<Integer> D20;
	LinkedList<Integer> D4;
	
	public GameVariables()
	{
		D20 = new LinkedList<Integer>();
		D4 = new LinkedList<Integer>();
	}

	/**
	 * get a d20 roll from the list.  If there are none left gen a random number
	 * @return a random number 1 - 20
	 */
	public int getD20Roll()
	{
		if ( D20 != null && D20.size() > 0)
		{
			return D20.pop().intValue();
		}
		Random r = new Random();
		return r.nextInt(20)+1;
	}

	public boolean hasMoreD20() {
		return (D20.size() > 0);
	}
	
	/**
	 * print out as a json file
	 */
	public String toString()
	{
		GsonBuilder gbuilder = new GsonBuilder();
		Gson gson = gbuilder.create();
		return gson.toJson(this);
	}
}
