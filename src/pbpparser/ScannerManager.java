package pbpparser;

import java.util.List;
import java.util.Scanner;

import core.VariableHolder.WeeklyStat;
import event.EventManager;
import filehandler.FolderManager;
import player.PlayerStats;

public class ScannerManager {
	Scanner sc;
	
	FolderManager fm;

	public ScannerManager()
	{
		sc = new Scanner(System.in);
		fm = new FolderManager();
	}
	
	public void start()
	{
		System.out.println("Welcome!");
		this.topLevelDialog();
	}

	private void topLevelDialog() {
		boolean keepGoing = true;
		while( keepGoing )
		{
			System.out.println("What do you want to do?");
			System.out.println("1: List players");
			System.out.println("2: Create new player");
			System.out.println("3: Save All");
			System.out.println("4: Preform Event");
			System.out.println("5: Print Player Info");
			System.out.println("6: Print Player Scores");
			System.out.println("0: Exit");
			String b = sc.next();
			int scanInt = this.numberConvert(b);
			
			switch(scanInt)
			{
				case -1:
					System.out.println("Please enter a number.");
					break;
				case 0:
					keepGoing = false;
					break;
				case 1:
					this.listAllPlayers();
					break;
				case 2:
					this.createNewPlayer();
					break;
				case 3:
					this.saveAll();
					break;
				case 4:
					this.preformEvent();
					break;
				case 5:
					this.printPlayerInfo();
					break;
				case 6:
					this.printPlayerScores();
					break;
				default:
					break;
			}
		}

	}

	private void printPlayerScores() {
		for( PlayerStats player: fm.getPlayerList() )
		{
			System.out.println(player.getName()+ ": " + player.getScore());
		}
		
	}

	private void printPlayerInfo() {
		for( PlayerStats player: fm.getPlayerList() )
		{
			System.out.println(player);
		}
		
	}

	private void preformEvent() {
		EventManager evt = new EventManager(fm);
		
		WeeklyStat primeStat = this.getWeeklyStat("What is the Prime WeeklyStat?");
		
		evt.primeNewEvent(primeStat);;
		
		for(PlayerStats player: fm.getPlayerList())
		{
			WeeklyStat secondStat = this.getWeeklyStat("What is the secondary stat for "+ player.getName());
			
			boolean postBonus = this.checkForPostBonus(player.getName());
			
			evt.calculateWeeksScore(player, primeStat, secondStat, postBonus);
		}
	}

	private boolean checkForPostBonus(String name) {
		System.out.println("Post bonus for " + name + "?(y/n)");
		
		String in = sc.next();
		if( in.equalsIgnoreCase("y") )
		{
			return true;
		}
		else if ( in.equalsIgnoreCase("n") )
		{
			return false;
		}
		else
		{
			return this.checkForPostBonus(name);
		}
	}

	private WeeklyStat getWeeklyStat(String note) {
		System.out.println(note);
		System.out.println("1: INTEL");
		System.out.println("2: PR");
		System.out.println("3: STR");
		System.out.println("4: BRAVE");

		String in = sc.next();
		int iIn = -1;
		while ( iIn == -1 )
		{
			iIn = this.numberConvert(in);
			if( iIn == -1 )
			{
				System.out.println("Try again.");
			}
		}
		
		switch(iIn)
		{
			case 1:
				return WeeklyStat.INTEL;
			case 2:
				return WeeklyStat.PR;
			case 3:
				return WeeklyStat.STR;
			case 4: 
				return WeeklyStat.BRAVE;
			default:
				return WeeklyStat.BRAVE;
		}
	}

	private void saveAll() {
		fm.saveAll();
	}

	private void createNewPlayer() {
		System.out.println("Name:");
		String name = sc.next();
		System.out.println("intel:");
		String intel = sc.next();
		System.out.println("pr:");
		String pr = sc.next();
		System.out.println("str:");
		String str = sc.next();
		System.out.println("brave:");
		String brave = sc.next();
		System.out.println("luck:");
		String luck = sc.next();
		
		int iIntel = this.numberConvert(intel);
		int iPr = this.numberConvert(pr);
		int iStr = this.numberConvert(str);
		int iBrave = this.numberConvert(brave);
		int iLuck = this.numberConvert(luck);
		
		PlayerStats player = new PlayerStats(name, iIntel, iPr, iStr, iBrave, iLuck);
		if( player.valid() )
		{
			fm.getPlayerList().add(player);
			fm.saveAll();
		}
		else
		{
			System.out.println("This player was not valid.  No player added.");
		}
		
	}

	private int numberConvert(String number) {
		int scanInt = -1;
		try
		{
			scanInt = Integer.parseInt(number);
		} catch( NumberFormatException e)
		{}		
		return scanInt;
	}

	private void listAllPlayers() {
		List<PlayerStats> players = fm.getPlayerList();
		System.out.println("---");
		for(PlayerStats s: players)
		{
			System.out.println(s.getName());
		}
		System.out.println("---");

	}
}
