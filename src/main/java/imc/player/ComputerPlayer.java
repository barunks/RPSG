package imc.player;

import java.util.Random;

import imc.inputadapter.InputAdapter;

public class ComputerPlayer extends AbstractPlayer {

	private InputAdapter<String> inputSource;

	public ComputerPlayer(String name, InputAdapter<String> inputSource) {
		super();
		this.setName(name);
		this.inputSource = inputSource;
	}

	public HANDSIGN makeMove() {

		HANDSIGN option = HANDSIGN.SCISSORS;

		switch (inputSource.nextInput()) {
			case "1":
				option = HANDSIGN.ROCK;
				break;
			case "2":
				option = HANDSIGN.PAPER;
				break;
			default:
				break;
		}

		return option;
	}

}
