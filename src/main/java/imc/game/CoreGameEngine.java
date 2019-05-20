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
	
	public enum RESULT {WIN, LOSE, TIE, NONE}
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
        // Run game for each pass
		for (int i = 0; i < n; i++) {
			run();
	    }
	}
	
	public void playReset() {
		// Before new game, Reset is required as refresh
	    System.out.println("\nGame Successfully Completed!");
	    System.out.println("\nGame Summary:");
	    System.out.println(summary.toString());
	    summary.reset();
	}
	
	public RESULT getIndividualMoveResult(HANDSIGN move1, HANDSIGN move2) {
		
		if (move1 == move2) return RESULT.TIE;
		
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
	
	private void run() {
		// Make move by each individual Player
		for (AbstractPlayer player : players ) {
			player.setCurrentMove(player.makeMove());
		}
		
		individualMoveRun();
    }
	
	private void individualMoveRun() {
		 // Player1 and Player2 make individual move and then results updated
		 AbstractPlayer player1 = players.get(0);
		 AbstractPlayer player2 = players.get(1);
		 updateResult(player1, player2, getIndividualMoveResult(player1.getCurrentMove(), player2.getCurrentMove()));
	 }
	
	private void updateResult(AbstractPlayer player1, AbstractPlayer player2, RESULT result) {
		 String handSignString1 = player1.getName() + "=>" + player1.getHandSignInString();
		 String handSignString2 = player2.getName() + "=>" + player2.getHandSignInString();
		 switch (result) {
         	case WIN:
         		System.out.println(handSignString1 + " beats " + handSignString2 + "!");
         		summary.addToWinCount();
         		break;
         	case LOSE:
         		System.out.println(handSignString1 + " loses to " + handSignString2 + "!");
         		summary.addToLoseCount();
         		break;
         	case TIE:
         		System.out.println(handSignString1 + " ties to " + handSignString2 + "!");
         		summary.addToTieCount();
         		break;
         	default:
         		break;
		 }
	}

}