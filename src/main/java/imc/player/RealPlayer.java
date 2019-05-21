package imc.player;

import imc.inputadapter.InputAdapter;

public class RealPlayer extends AbstractPlayer {
	private InputAdapter<String> inputSource;

	public RealPlayer(String name, InputAdapter<String> inputSource) {
		super();
		this.setName(name);
		this.inputSource = inputSource;
	}

	public HANDSIGN makeMove() {

		System.out.println("\nPlease enter your choice: R=Rock, P=Paper, S=Scissors");

		switch (inputSource.nextInput().charAt(0)) {
			case HANDSIGN_ROCK:
				return HANDSIGN.ROCK;
			case HANDSIGN_PAPER:
				return HANDSIGN.PAPER;
			case HANDSIGN_SCISSORS:
				return HANDSIGN.SCISSORS;
		}

		// Invalid input
		System.out.println("Invalid input!! Try Again");
		return makeMove();
	}
}
