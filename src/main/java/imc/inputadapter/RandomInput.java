package imc.inputadapter;

import java.util.Random;

import imc.player.AbstractPlayer.HANDSIGN;

public class RandomInput implements InputAdapter<String> {
	
	private Random inputSource;
	
	public RandomInput(Random inputSource) {
		this.inputSource = inputSource;
	}
	
	public String nextInput() {
		int choice = 1 + inputSource.nextInt(HANDSIGN.values().length);
		return String.valueOf(choice);
	}

}
