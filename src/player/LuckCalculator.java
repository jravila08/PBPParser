package player;

/**
 * int the wee details tab inside the player obj
 * @author jravi
 *
 */
public class LuckCalculator {
	
	private int rerollIfRollBelow;
	private int totalRerollCount;
	private int rerollsLeft;

	public LuckCalculator(int playerLuck)
	{
		this.calcLuckRoll(playerLuck);
	}
	
	/**
	 * @param playerLuck
	 */
	private void calcLuckRoll(int playerLuck)
	{
		int s = playerLuck / 2;
		switch(s)
		{
			case 1:
				rerollIfRollBelow = 1;
				totalRerollCount = 1;
				rerollsLeft = totalRerollCount;
				break; 
			case 2:
				rerollIfRollBelow = 2;
				totalRerollCount = 1;
				rerollsLeft = totalRerollCount;
				break; 
			case 3:
				rerollIfRollBelow = 3;
				totalRerollCount = 1;
				rerollsLeft = totalRerollCount;
				break; 
			case 4:
				rerollIfRollBelow = 1;
				totalRerollCount = 2;
				rerollsLeft = totalRerollCount;
				break; 
			case 5:
				rerollIfRollBelow = 2;
				totalRerollCount = 2;
				rerollsLeft = totalRerollCount;
				break; 
			case 6:
				rerollIfRollBelow = 3;
				totalRerollCount = 2;
				rerollsLeft = totalRerollCount;
				break; 
			case 7:
				rerollIfRollBelow = 1;
				totalRerollCount = 3;
				rerollsLeft = totalRerollCount;
				break; 
			case 8:
				rerollIfRollBelow = 2;
				totalRerollCount = 3;
				rerollsLeft = totalRerollCount;
				break; 
			case 9:
				rerollIfRollBelow = 3;
				totalRerollCount = 3;
				rerollsLeft = totalRerollCount;
				break; 
			case 10:
				rerollIfRollBelow = 4;
				totalRerollCount = 3;
				rerollsLeft = totalRerollCount;
				break; 
			default:
				rerollIfRollBelow = 0;
				totalRerollCount = 0;
				rerollsLeft = totalRerollCount;
				break; 
 		}
	}
	
	public int getRerollIfRollBelow() {
		return rerollIfRollBelow;
	}

	public void setRerollIfRollBelow(int rerollIfRollBelow) {
		this.rerollIfRollBelow = rerollIfRollBelow;
	}

	public int getTotalRerollCount() {
		return totalRerollCount;
	}

	public void setTotalRerollCount(int totalRerollCount) {
		this.totalRerollCount = totalRerollCount;
	}

	public int getRerollsLeft() {
		return rerollsLeft;
	}

	public void setRerollsLeft(int rerollsLeft) {
		this.rerollsLeft = rerollsLeft;
	}
}
