/*
 * Copyright 2017 jravi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package player;

import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import core.VariableHolder;

/**
 * this file will hold all of the information on a single given player
 *
 * @author jravi
 */
public class PlayerStats {
	private String name;
	private int intel;
	private int pr;
	private int str;
	private int brave;
	private int luck;

	private LinkedList<WeeklyScore> weeklyScores;

	public PlayerStats() {
		weeklyScores = new LinkedList<WeeklyScore>();
	}
	
	public PlayerStats(String name, int intel, int pr, int str, int brave, int luck) {
		weeklyScores = new LinkedList<WeeklyScore>();
		this.name = name;
		this.intel = intel;
		this.pr = pr;
		this.str = str;
		this.brave = brave;
		this.luck = luck;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIntel() {
		return intel;
	}

	public void setIntel(int intel) {
		this.intel = intel;
	}

	public int getPr() {
		return pr;
	}

	public void setPr(int pr) {
		this.pr = pr;
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public int getBrave() {
		return brave;
	}

	public void setBrave(int brave) {
		this.brave = brave;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public LinkedList<WeeklyScore> getWeeklyScores() {
		return weeklyScores;
	}

	public void setWeeklyScores(LinkedList<WeeklyScore> weeklyScores) {
		this.weeklyScores = weeklyScores;
	}
	
	public void addWeeklyScores(WeeklyScore weeklyScores) {
		this.weeklyScores.add(weeklyScores);
	}

	public int getStatFromEnum(VariableHolder.WeeklyStat stat)
	{
		switch(stat)
		{
			case INTEL:
				return intel;
			case PR:
				return pr;
			case STR:
				return str;
			case BRAVE:
				return brave;
			default:
				return 0;
		}

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

	public void populateTestObject() {
		this.name = "testPlayer";
		this.intel = 10;
		this.pr = 10;
		this.str = 10;
		this.brave = 10;
		this.luck = 10;

		WeeklyScore w1 = new WeeklyScore();
		w1.populateTestObject();

		weeklyScores.add(w1);
		weeklyScores.add(w1);
	}

	public boolean valid() {
		// TODO Auto-generated method stub
		if( !this.name.isEmpty() &&
				this.brave != 0     &&
				this.intel != 0     &&
				this.luck  != 0     &&
				this.pr    != 0     &&
				this.str   != 0 )
		{
			return true;
		}
		return false;
	}

	public int getScore() {
		int totalScore = 0;
		for(WeeklyScore w: weeklyScores)
		{
			totalScore += w.getScoreForThisWeek();
		}
		return totalScore;
	}

}
