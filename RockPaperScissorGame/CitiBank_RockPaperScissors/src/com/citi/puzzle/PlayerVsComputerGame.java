package com.citi.puzzle;

import com.citi.puzzle.enums.MoveType;
import com.citi.puzzle.exceptions.NoRightGameException;



/*
 * Flavio Marchi (c) November 2015
 */
public class PlayerVsComputerGame extends RockPaperScissorGame {

	public PlayerVsComputerGame(String id) {
		super(id);
	}
	
	@Override
	public void play() throws NoRightGameException {
		throw new NoRightGameException("This is a Player vs Computer game; you have to make your move.");
	}

	@Override
	public void makeMove(MoveType playerMove) throws NoRightGameException {
		MoveType computerMove = MoveType.getRandomMove(MoveType.class);
		play(playerMove, computerMove);
	}

}
