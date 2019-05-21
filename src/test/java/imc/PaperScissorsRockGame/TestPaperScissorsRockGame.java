package imc.paperscissorsrockgame;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import imc.exception.PlayerNumberException;
import imc.game.CoreGameEngine;
import imc.game.CoreGameEngine.RESULT;
import imc.inputadapter.ConsoleInput;
import imc.inputadapter.InputAdapter;
import imc.inputadapter.RandomInput;
import imc.game.GameSummary;
import imc.player.AbstractPlayer;
import imc.player.AbstractPlayer.HANDSIGN;
import imc.player.ComputerPlayer;
import imc.player.RealPlayer;

import java.util.*;

/**
 * Unit test for simple PaperScissorsRockGame.
 */
public class TestPaperScissorsRockGame {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	InputAdapter<String> realPlayerInput = new ConsoleInput(new Scanner(System.in));
	InputAdapter<String> randomInput = new RandomInput(new Random());

	/**
	 * Create the test case
	 *
	 */

	@Test
	public void testRockPaperGameOutcomeBetweenMovePaperAndScissors() throws Exception {

		AbstractPlayer player1 = new RealPlayer("Human1", realPlayerInput);
		AbstractPlayer player2 = new RealPlayer("Human2", realPlayerInput);
		AbstractPlayer[] playerArray = { player1, player2 };
		CoreGameEngine gameEngine = new CoreGameEngine(new ArrayList<AbstractPlayer>(Arrays.asList(playerArray)),
				new GameSummary());
		player1.setCurrentMove(HANDSIGN.PAPER);
		player2.setCurrentMove(HANDSIGN.SCISSORS);
		RESULT result = gameEngine.getIndividualMoveResult(player1, player2);
		Assert.assertEquals(result, RESULT.LOSE);
	}

	@Test
	public void testRockPaperGameOutcomeBetweenMovePaperAndRock() throws Exception {

		AbstractPlayer player1 = new RealPlayer("Human1", realPlayerInput);
		AbstractPlayer player2 = new RealPlayer("Human2", realPlayerInput);
		AbstractPlayer[] playerArray = { player1, player2 };
		CoreGameEngine gameEngine = new CoreGameEngine(new ArrayList<AbstractPlayer>(Arrays.asList(playerArray)),
				new GameSummary());
		player1.setCurrentMove(HANDSIGN.PAPER);
		player2.setCurrentMove(HANDSIGN.ROCK);
		RESULT result = gameEngine.getIndividualMoveResult(player1, player2);
		Assert.assertEquals(result, RESULT.WIN);
	}

	@Test
	public void testRockPaperGameOutcomeBetweenMoveRockAndScissors() throws Exception {

		AbstractPlayer player1 = new RealPlayer("Human1", realPlayerInput);
		AbstractPlayer player2 = new RealPlayer("Human2", realPlayerInput);
		AbstractPlayer[] playerArray = { player1, player2 };
		CoreGameEngine gameEngine = new CoreGameEngine(new ArrayList<AbstractPlayer>(Arrays.asList(playerArray)),
				new GameSummary());
		player1.setCurrentMove(HANDSIGN.ROCK);
		player2.setCurrentMove(HANDSIGN.SCISSORS);
		RESULT result = gameEngine.getIndividualMoveResult(player1, player2);
		Assert.assertEquals(result, RESULT.WIN);
	}

	@Test
	public void testRockPaperGameOutcomeBetweenMoveScissorsAndScissors() throws Exception {

		AbstractPlayer player1 = new RealPlayer("Human1", realPlayerInput);
		AbstractPlayer player2 = new RealPlayer("Human2", realPlayerInput);
		AbstractPlayer[] playerArray = { player1, player2 };
		CoreGameEngine gameEngine = new CoreGameEngine(new ArrayList<AbstractPlayer>(Arrays.asList(playerArray)),
				new GameSummary());
		player1.setCurrentMove(HANDSIGN.SCISSORS);
		player2.setCurrentMove(HANDSIGN.SCISSORS);
		RESULT result = gameEngine.getIndividualMoveResult(player1, player2);
		Assert.assertEquals(result, RESULT.TIE);
	}

	@Test
	public void testRockPaperGameOutcomeBetweenMoveRockAndRock() throws Exception {

		AbstractPlayer player1 = new RealPlayer("Human1", realPlayerInput);
		AbstractPlayer player2 = new RealPlayer("Human2", realPlayerInput);
		AbstractPlayer[] playerArray = { player1, player2 };
		CoreGameEngine gameEngine = new CoreGameEngine(new ArrayList<AbstractPlayer>(Arrays.asList(playerArray)),
				new GameSummary());
		player1.setCurrentMove(HANDSIGN.ROCK);
		player2.setCurrentMove(HANDSIGN.ROCK);
		RESULT result = gameEngine.getIndividualMoveResult(player1, player2);
		Assert.assertEquals(result, RESULT.TIE);
	}

	@Test
	public void testTotalValidTurnsAfterEndOfGame() throws Exception {
		int numberOfTurn = 10;
		AbstractPlayer player1 = new ComputerPlayer("Computer1", randomInput);
		AbstractPlayer player2 = new ComputerPlayer("Computer2", randomInput);
		AbstractPlayer[] playerArray = { player1, player2 };
		GameSummary gameSummary = new GameSummary();
		CoreGameEngine gameEngine = new CoreGameEngine(new ArrayList<AbstractPlayer>(Arrays.asList(playerArray)),
				gameSummary);
		gameEngine.playSession(numberOfTurn);
		Assert.assertEquals(numberOfTurn,
				gameSummary.getNumWinCount() + gameSummary.getNumLoseCount() + gameSummary.getNumTieCount());
	}

	@Test
	public void testPlayResetAfterEndOfGame() throws Exception {
		int numberOfTurn = 10;
		AbstractPlayer player1 = new ComputerPlayer("Computer1", randomInput);
		AbstractPlayer player2 = new ComputerPlayer("Computer2", randomInput);
		AbstractPlayer[] playerArray = { player1, player2 };
		GameSummary gameSummary = new GameSummary();
		CoreGameEngine gameEngine = new CoreGameEngine(new ArrayList<AbstractPlayer>(Arrays.asList(playerArray)),
				gameSummary);
		gameEngine.playSession(numberOfTurn);
		gameEngine.resetGame();
		Assert.assertEquals(0,
				gameSummary.getNumWinCount() + gameSummary.getNumLoseCount() + gameSummary.getNumTieCount());
	}

	@Test
	public void testExceptionForThreeRealPlayers() throws Exception {
		AbstractPlayer player1 = new RealPlayer("Human1", realPlayerInput);
		AbstractPlayer player2 = new RealPlayer("Human2", realPlayerInput);
		AbstractPlayer player3 = new RealPlayer("Human3", realPlayerInput);
		AbstractPlayer[] playerArray = { player1, player2, player3 };
		exception.expect(PlayerNumberException.class);
		exception.expectMessage("Number of Players " + playerArray.length + " won't suit to the game... ");
		new CoreGameEngine(new ArrayList<AbstractPlayer>(Arrays.asList(playerArray)), new GameSummary());
	}

	@Test
	public void testExceptionForOneComputerPlayer() throws Exception {
		AbstractPlayer player1 = new ComputerPlayer("Computer1", randomInput);
		AbstractPlayer[] playerArray = { player1 };
		exception.expect(PlayerNumberException.class);
		exception.expectMessage("Number of Players " + playerArray.length + " won't suit to the game... ");
		new CoreGameEngine(new ArrayList<AbstractPlayer>(Arrays.asList(playerArray)), new GameSummary());
	}
}
