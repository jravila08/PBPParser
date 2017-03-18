package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pbpparser.VariableHolder;

/**
 * This is the class that holds all of the GM info, avail dice rolls, event difficulty, ect
 * @author jravi
 *
 */
public class GameVariables {
	
	LinkedList<Integer> D20;
	LinkedList<Integer> D4;
	
	int currentWeek;
	ArrayList<VariableHolder.WeeklyStat> statOfTheWeek;
	
	public GameVariables()
	{
		currentWeek = 0;
		D20 = new LinkedList<Integer>();
		D4 = new LinkedList<Integer>();
		statOfTheWeek = new ArrayList<VariableHolder.WeeklyStat>();
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
	
	public int getD4Roll()
	{
		if ( D4 != null && D4.size() > 0)
		{
			return D4.pop().intValue();
		}
		Random r = new Random();
		return r.nextInt(4)+1;
	}

	public boolean hasMoreD20() {
		return (D20.size() > 0);
	}
	
	public boolean hasMoreD4() {
		return (D4.size() > 0);
	}
	
	/**
	 * print out as a json file
	 */
	public String toString()
	{
		GsonBuilder gbuilder = new GsonBuilder();
		Gson gson = gbuilder.setPrettyPrinting().create();
		return gson.toJson(this);
	}
	
	/**
	 * rith now this is only used for test purposes
	 * @param d20
	 */
	public void setD20(LinkedList<Integer> d20)
	{
		D20 = d20;
	}
	
	/**
	 * right now this is only used for test purposes
	 * @param d4
	 */
	public void setD4(LinkedList<Integer> d4)
	{
		D4 = d4;
	}
}
