package com.citi.puzzle;

import com.citi.puzzle.enums.MoveType;
import com.citi.puzzle.exceptions.NoRightGameException;



/*
 * Flavio Marchi (c) November 2015
 */
public class ComputerVsComputerGame extends RockPaperScissorGame {

	public ComputerVsComputerGame(String id) {
		super(id);
	}

	@Override
	public void play() throws NoRightGameException {
		MoveType computerMoveA = MoveType.getRandomMove(MoveType.class);
		MoveType computerMoveB = MoveType.getRandomMove(MoveType.class);
		play(computerMoveA, computerMoveB);
	}

	@Override
	public void makeMove(MoveType playerMove) throws NoRightGameException {
		throw new NoRightGameException("This is a Computer vs Computer game; you do not have to make any move.");
	}

}
