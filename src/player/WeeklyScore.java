package player;

import java.util.ArrayList;

import core.VariableHolder;

public class WeeklyScore {
	
	private VariableHolder.WeeklyStat primaryStatForWeek;
	private VariableHolder.WeeklyStat secondaryStatForWeek;
	
	private int modifiedIntel;
	private int modifiedPr;
	private int modifiedStr;
	private int modifiedBrave;
	private int modifiedLuck;
	private int scoreForThisWeek;
	
	private boolean postingBonus;
    
	private LuckCalculator luckCalculator;
    
	private ArrayList<Integer> d20Rolls;
    
    public WeeklyScore()
    {
    	d20Rolls = new ArrayList<Integer>();
    }

	public VariableHolder.WeeklyStat getPrimaryStatForWeek() {
		return primaryStatForWeek;
	}

	public void setPrimaryStatForWeek(VariableHolder.WeeklyStat primaryStatForWeek) {
		this.primaryStatForWeek = primaryStatForWeek;
	}

	public VariableHolder.WeeklyStat getSecondaryStatForWeek() {
		return secondaryStatForWeek;
	}

	public void setSecondaryStatForWeek(VariableHolder.WeeklyStat secondaryStatForWeek) {
		this.secondaryStatForWeek = secondaryStatForWeek;
	}

	public int getModifiedIntel() {
		return modifiedIntel;
	}

	public void setModifiedIntel(int modifiedIntel) {
		this.modifiedIntel = modifiedIntel;
	}

	public int getModifiedPr() {
		return modifiedPr;
	}

	public void setModifiedPr(int modifiedPr) {
		this.modifiedPr = modifiedPr;
	}

	public int getModifiedStr() {
		return modifiedStr;
	}

	public void setModifiedStr(int modifiedStr) {
		this.modifiedStr = modifiedStr;
	}

	public int getModifiedBrave() {
		return modifiedBrave;
	}

	public void setModifiedBrave(int modifiedBrave) {
		this.modifiedBrave = modifiedBrave;
	}

	public int getModifiedLuck() {
		return modifiedLuck;
	}

	public void setModifiedLuck(int modifiedLuck) {
		this.modifiedLuck = modifiedLuck;
	}

	public boolean isPostingBonus() {
		return postingBonus;
	}

	public void setPostingBonus(boolean postingBonus) {
		this.postingBonus = postingBonus;
	}

	public LuckCalculator getLuckCalculator() {
		return luckCalculator;
	}

	public void setLuckCalculator(LuckCalculator luckCalculator) {
		this.luckCalculator = luckCalculator;
	}

	public ArrayList<Integer> getD20Rolls() {
		return d20Rolls;
	}

	public void addD20Roll(int roll) {
		this.d20Rolls.add(roll);
	}

	public int getScoreForThisWeek() {
		return scoreForThisWeek;
	}

	public void setScoreForThisWeek(int scoreForThisWeek) {
		this.scoreForThisWeek = scoreForThisWeek;
	}
    
    public void populateTestObject()
    {
    	this.primaryStatForWeek = VariableHolder.WeeklyStat.BRAVE;
    	this.secondaryStatForWeek = VariableHolder.WeeklyStat.INTEL;
    	
    	this.modifiedIntel = 10;
    	this.modifiedPr = 10;
    	this.modifiedStr = 10;
    	this.modifiedBrave = 10;
    	this.modifiedLuck = 10;
    	
    	this.postingBonus = true;
        
    	this.luckCalculator = new LuckCalculator(this.modifiedLuck);
        
    	this.d20Rolls.add(1);
    	this.d20Rolls.add(10);
    	this.d20Rolls.add(11);

    	this.scoreForThisWeek = 10;
    }
    
    public String toString()
    {
    	String ret = "primaryStatForWeek: " + primaryStatForWeek +
    				 "\nsecondaryStatForWeek: " + secondaryStatForWeek +
    				 "\nscoreForThisWeek: " + scoreForThisWeek + 
    				 "\nluckCalculator" + luckCalculator +
    				 "\nd20Rolls: " + d20Rolls ;
    	return ret;
    }
}
