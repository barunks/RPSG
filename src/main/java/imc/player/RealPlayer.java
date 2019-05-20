package imc.player;
import java.util.Scanner;

public class RealPlayer extends AbstractPlayer {
	private Scanner scanner;
	
	public RealPlayer(String name, Scanner scanner) {
        super();
        this.setName(name);
        this.scanner = scanner;
    }
	
	public HANDSIGN makeMove() {

        System.out.println("\nPlease enter your choice: R=Rock, P=Paper, S=Scissors");       
        
        char inputString = ' ';
        String line = scanner.nextLine();
        
        if(line.length() > 0)
        	inputString = line.toUpperCase().charAt(0);

        switch (inputString) {
            case HANDSIGN_ROCK:
                return HANDSIGN.ROCK;
            case HANDSIGN_PAPER:
                return HANDSIGN.PAPER;
            case HANDSIGN_SCISSORS:
                return HANDSIGN.SCISSORS;
        }

        //Invalid input
        System.out.println("Invalid input!! Try Again");
        return makeMove();
    }
}
