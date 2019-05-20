package imc.game;


import imc.exception.PlayerNumberException;
import imc.player.AbstractPlayer;
import imc.player.AbstractPlayer.HANDSIGN;
import java.util.ArrayList;

public class CoreGameEngine {
	
	private enum RESULT {WIN, LOSE, TIE, NONE}
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

		for (int i = 0; i < n; i++) {
			run();
	    }

	    System.out.println("\nGame Successfully Completed!");
	    System.out.println("\nGame Summary:");
	    System.out.println(summary.toString());
	    summary.reset();
	}
	
	private void run() {

		for (AbstractPlayer player : players ) {
			player.setCurrentMove(player.makeMove());
		}
		
		individualGameRun();
    }
	
	private void individualGameRun() {
		 // Abstract Player1 and Player2  for their move and find the result
		 AbstractPlayer player1 = players.get(0);
		 AbstractPlayer player2 = players.get(1);
		 updateResult(player1, player2, getGameResult(player1.getCurrentMove(), player2.getCurrentMove()));
	 }
	
	private RESULT getGameResult(HANDSIGN move1, HANDSIGN move2) {
		
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