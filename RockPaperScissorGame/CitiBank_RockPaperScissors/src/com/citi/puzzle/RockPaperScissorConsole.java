package com.citi.puzzle;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import com.citi.puzzle.enums.Command;
import com.citi.puzzle.enums.MoveType;
import com.citi.puzzle.exceptions.NoRightGameException;



/*
 * Flavio Marchi (c) November 2015
 */
public class RockPaperScissorConsole {
	
	private static String playerName = null;
	private static RockPaperScissorGame actual = null;
	
	private Map<String, RockPaperScissorGame> matches;

	public RockPaperScissorConsole(String playerName) {
		matches = new HashMap<String, RockPaperScissorGame>();
	}
	
	public void addMatch(RockPaperScissorGame match) {
		matches.put(match.getGameCode(), match);
	}
	
	public RockPaperScissorGame retrieveMatch(String gamecode) {
		return matches.get(gamecode);
	}
	
	public static void main(String[] args) {
		System.out.println("Rock, Paper & Scissor Game");
		System.out.println("(c) Flavio Marchi (for) Citi Bank");
		System.out.println("Please, introduce yourself: ");
		Scanner sc = new Scanner(System.in);
		playerName = sc.nextLine();
		RockPaperScissorConsole console = new RockPaperScissorConsole(playerName);
		System.out.println("Hello " + playerName + ", make your choice:");
		boolean exit = false;
		while(!exit) {
			Stream.of(Command.values()).forEach(System.out::println);
			int key = -1;
			try {
				key = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				sc.reset();
				System.out.println("command not understood at this stage. please retype it.");
			}
			switch(key) {
				case 1: //CREATE_GAME_PLAYER_VS_COMPUTER
					System.out.println("provide a numeric/alphanumeric id for this match:");
					String matchid = sc.nextLine();
					actual = new PlayerVsComputerGame(matchid);
					console.addMatch(actual);
					System.out.println("game created.");
					break;
				case 2: //CREATE_GAME_COMPUTER_VS_COMPUTER
					System.out.println("provide a numeric/alphanumeric id for this match:");
					matchid = sc.nextLine();
					actual = new ComputerVsComputerGame(matchid);
					console.addMatch(actual);
					System.out.println("game created.");
					break;
				case 3: //PLAY_DIFFERENT_GAME
					System.out.println("provide the gamecode of the match you want to play:");
					matchid = sc.nextLine();
					actual = console.retrieveMatch(matchid);
					System.out.println("game changed.");
					break;
				case 4:  //MAKE_MOVE
					System.out.println("make your move; let's type rock, paper or scissor:");
					String move = sc.nextLine();
					try {
						actual.makeMove(MoveType.valueOf(move.trim().toUpperCase()));
						System.out.println(actual);
					} catch (NoRightGameException e) {
						System.out.println("command not valid for this type of match");
					} catch (java.lang.IllegalArgumentException e) {
						System.out.println("command not valid");
					}
					break;
				case 5: //JUST_PLAY
					System.out.println("just press enter to see the outcome:");
					sc.nextLine();
					try {
						actual.play();
						System.out.println(actual);
					} catch (NoRightGameException e) {
						System.out.println("command not valid for this type of match");
					} catch (java.lang.IllegalArgumentException e) {
						System.out.println("command not valid");
					}
					break;
				case 6:
					exit = true;
					System.out.println("See you!");
					break;
				default:
					sc.reset();
			}
		}
		sc.close();
		System.exit(0);
	}

}
