package event;

import filehandler.FolderManager;
import pbpparser.VariableHolder;
import pbpparser.VariableHolder.WeeklyStat;
import player.LuckCalculator;
import player.PlayerStats;
import player.WeeklyScore;

public class EventManager {

	FolderManager fm;

	public EventManager() 
	{
		fm = new FolderManager();
	}

	public boolean startNewEvent()
	{
		fm.getGameVariables().incCurrentWeek();
		
		//TODO THIS NEEDS TO BE SET A CERTAIN WAY
		fm.getGameVariables().setThisWeeksStatOfTheWeek(WeeklyStat.BRAVE);

		VariableHolder.WeeklyStat thisWeekStat = fm.getGameVariables().getLastestStatOfTheWeek();

		if( thisWeekStat == null )
		{
			return false;
		}

		for( PlayerStats pl : fm.getPlayerList() )
		{
			//TODO NEED TO SET UP SECONDARY STAT 
			this.calculateWeeksScore(pl, thisWeekStat, thisWeekStat);
		}

		return true;
	}

	private void calculateWeeksScore(PlayerStats pl, 
									 WeeklyStat thisWeekStatPri, 
									 WeeklyStat thisWeekStatSec) 
	{
		WeeklyScore thisScoring = new WeeklyScore();
		thisScoring.setLuckCalculator(new LuckCalculator(pl.getLuck()));
		thisScoring.setPrimaryStatForWeek(thisWeekStatPri);
		thisScoring.setSecondaryStatForWeek(thisWeekStatSec);
		
		int toBeat = 10;
		
		this.recursiveScore(thisWeekStatPri,pl,thisScoring,toBeat,10);
		this.recursiveScore(thisWeekStatSec,pl,thisScoring,toBeat,7);

		pl.addWeeklyScores(thisScoring);
		System.out.println("SCORING FOR THIS WEEK IS: " + thisScoring);
	}

	private void recursiveScore(WeeklyStat thisWeekStat, PlayerStats pl, WeeklyScore thisScoring, int toBeat, int scoreModifier) {
		int stat = pl.getStatFromEnum(thisWeekStat);
		int rand = fm.getGameVariables().getD20Roll();

		thisScoring.addD20Roll(rand);
		
		if( rand + stat > toBeat )
		{
			thisScoring.setScoreForThisWeek(thisScoring.getScoreForThisWeek() + scoreModifier);
			recursiveScore(thisWeekStat, pl, thisScoring, toBeat + 2, scoreModifier);
		}
		else if( thisScoring.getLuckCalculator().getRerollsLeft() > 0 && 
				 thisScoring.getLuckCalculator().getRerollIfRollBelow() >= rand )
		{
			thisScoring.getLuckCalculator().setRerollsLeft(thisScoring.getLuckCalculator().getRerollsLeft() - 1);
			recursiveScore(thisWeekStat, pl, thisScoring, toBeat, scoreModifier);
		}
	}

}
