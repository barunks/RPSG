package imc.player;

import java.util.Random;

public class ComputerPlayer extends AbstractPlayer {
	
	private Random randomNumber;

	public ComputerPlayer(String name, Random randomNumber) {
		super();
		this.setName(name);
		this.randomNumber = randomNumber;
	}
	
	public HANDSIGN makeMove() {
        
        HANDSIGN option = HANDSIGN.SCISSORS;
        int choice = 1 + randomNumber.nextInt(HANDSIGN.values().length);

        switch (choice) {
            case 1:
            	option =  HANDSIGN.ROCK;
            	break;
            case 2:
            	option =  HANDSIGN.PAPER;
            	break;
            default:
            	break;
        }

        return option;
    }

}
