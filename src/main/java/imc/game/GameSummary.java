package imc.game;

public class GameSummary {
	private int countWin;
	private int countLose;
	private int countTie;
	
	public GameSummary() {
		countWin = countLose = countTie = 0;
	}
	
	public void addToWinCount() {
		countWin++;
	}
	
	public void addToLoseCount() {
		countLose++;
	}
	
	public void addToTieCount() {
		countTie++;
	}
	
	public String toString(){
		String output = "Wins: " + countWin + " Losses: " + countLose + " Ties: " + countTie;
		return output;
	}
	
	public void reset() {
		countWin = countLose = countTie = 0;
	}
	
	public int getNumWinCount() { return countWin; }
	public int getNumLoseCount() { return countLose; }
	public int getNumTieCount() { return countTie; }

}
