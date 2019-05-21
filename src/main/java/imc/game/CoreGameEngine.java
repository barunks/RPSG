package imc.game;

import imc.exception.PlayerNumberException;
import imc.player.AbstractPlayer;
import imc.player.AbstractPlayer.HANDSIGN;
import java.util.ArrayList;

/**
 * 
 * CoreGameEngine class requires two players and game summary
 * 
 */
public class CoreGameEngine {

	public enum RESULT {
		WIN, LOSE, TIE, NONE
	}

	private ArrayList<AbstractPlayer> players;
	private GameSummary summary;

	public CoreGameEngine(ArrayList<AbstractPlayer> players, GameSummary summary) throws PlayerNumberException {

		if (players.size() != 2) {
			throw new PlayerNumberException("Number of Players " + players.size() + " won't suit to the game... ");
		}

		this.players = players;
		this.summary = summary;
	}

	public void playSession(int n) {
		// Run game for each individual turn
		for (int i = 0; i < n; i++) {
			run();
		}
	}

	public void resetGame() {
		System.out.println("\nGame Successfully Completed!");
		System.out.println("\nGame Summary:");
		System.out.println(summary.toString());
		summary.reset();
	}

	private void run() {
		// Make move by each individual Player turn
		for (AbstractPlayer player : players) {
			player.setCurrentMove(player.makeMove());
		}

		runIndividualMove();
	}

	private void runIndividualMove() {
		// Player1 and Player2 make individual move, update result and print
		RESULT result = getIndividualMoveResult(players.get(0), players.get(1));
		switch (result) {
		case WIN:
			summary.addToWinCount();
			break;
		case LOSE:
			summary.addToLoseCount();
			break;
		case TIE:
			summary.addToTieCount();
			break;
		default:
			break;
		}
		printResult(players.get(0), players.get(1));
	}
	
	public static RESULT getIndividualMoveResult(AbstractPlayer player1, AbstractPlayer player2) {
		HANDSIGN move1 = player1.getCurrentMove();
		HANDSIGN move2 = player2.getCurrentMove();

		// Game logic for Win, Tie or Lose
		if (move1 == move2)
			return RESULT.TIE;
		
		switch (move1) {
		case ROCK:
			return move2 == HANDSIGN.SCISSORS ? RESULT.WIN : RESULT.LOSE;
		case PAPER:
			return move2 == HANDSIGN.ROCK ? RESULT.WIN : RESULT.LOSE;
		case SCISSORS:
			return move2 == HANDSIGN.PAPER ? RESULT.WIN : RESULT.LOSE;
		default:
			break;
		}

		return RESULT.NONE;
	}

	public static void printResult(AbstractPlayer player1, AbstractPlayer player2) {
		RESULT result = getIndividualMoveResult(player1, player2);
		// Form result in the specific format
		String handSignString1 = player1.getName() + "=>" + player1.getHandSignInString();
		String handSignString2 = player2.getName() + "=>" + player2.getHandSignInString();
		
		switch (result) {
		case WIN:
			System.out.println(handSignString1 + " beats " + handSignString2 + "!");
			break;
		case LOSE:
			System.out.println(handSignString1 + " loses to " + handSignString2 + "!");
			break;
		case TIE:
			System.out.println(handSignString1 + " ties to " + handSignString2 + "!");
			break;
		default:
			break;
		}
	}

}