package imc.player;

/**
 * 
 * AbstractPlayer is a abstract class manages each player move 
 * And having a individual name
 *
 */
public abstract class AbstractPlayer {
	
	public enum HANDSIGN {
	    ROCK,
	    PAPER,
	    SCISSORS
	}
	
	protected static final char HANDSIGN_ROCK = 'R';
    protected static final char HANDSIGN_PAPER = 'P';
    protected static final char HANDSIGN_SCISSORS = 'S';
	
	private HANDSIGN sign;
	private String name;
	public abstract HANDSIGN makeMove();
	public HANDSIGN getCurrentMove() { return sign; }
	public void setCurrentMove(HANDSIGN sign) { this.sign = sign; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	// Method returns string value for Rock, paper and Scissors handsign
	public String getHandSignInString() {
		switch(sign) {
			case ROCK:
				return "Rock";
			case PAPER:
				return "Paper";
			case SCISSORS:
				return "Scissors";
		}
		
		return "";
	}
}
