package imc.PaperScissorsRockGame;
import imc.game.CoreGameEngine;
import imc.game.GameSummary;
import imc.player.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class PaperScissorsRockGame {

	public static void main( String[] args ) {
       beginGame();
    }
	
	 private static void beginGame() {
		 // Initialize PlayerList
		 ArrayList<AbstractPlayer> playerList = new ArrayList<AbstractPlayer>(); 
		 Scanner scanner = new Scanner(System.in);
		 Random randomNumber = new Random();
		// Player1 and Player2  are instantiated
		 playerList.add(new RealPlayer("Human", scanner));
		 playerList.add(new ComputerPlayer("Computer", randomNumber));
		 // Instantiate gameEngine instance for PlayerList and GameSummary as input parameter
		 try {
			 CoreGameEngine gameEngine = new CoreGameEngine(playerList, new GameSummary());
			 char exitGame = 'N';
			 while (exitGame == 'N') {
				 int n = inputNumberOfPlays(scanner);
				 System.out.println("\n Game started. Each Player will play " + n + " moves");
				 gameEngine.playSession(n);
				 gameEngine.playReset(); // Reset game before every new game starts
				 System.out.println("\n Do you want to Exit? Press 'N' or 'n' to continue else press any other key to continue");
				 exitGame = scanner.nextLine().toUpperCase().charAt(0);
			 }
		 } catch (Exception ex) {
			 System.out.println("Error in CoreGameEngine Parameters! " + ex.getMessage());
         } 
	 }
	 
	 private static int inputNumberOfPlays(Scanner scanner) {
		 int n = 0;
	     while (true) {
	    	 try {
	    		 System.out.println("How many numbers of move you want in this game<Must be Greater than Zero>");
	             n = scanner.nextInt();
	             if (n <= 0) {
	                    throw new NumberFormatException("User input must be positive...");
	             }
	             scanner.nextLine();
	             break;
	         } catch (NumberFormatException ex) {
	        	 System.out.println("Invalidated user input! " + ex.getMessage());
	         }
	     }
	     return n;
	 }
}
