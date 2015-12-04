package com.citi.puzzle;

import com.citi.puzzle.enums.GameOutcome;
import com.citi.puzzle.enums.MoveType;
import com.citi.puzzle.exceptions.NoRightGameException;



/*
 * Flavio Marchi (c) November 2015
 */
public abstract class RockPaperScissorGame {
	
	private String gamecode;
	private int howManyPlay = 0;
	private int scorePlayerA = 0;
	private int scorePlayerB = 0;
	private GameOutcome lastOutcome = null;

	public RockPaperScissorGame(String gamecode) {
		this.gamecode = gamecode;
	}
	
	public abstract void play() throws NoRightGameException;
	
	public abstract void makeMove(MoveType playerMove) throws NoRightGameException;
	
	public String getGameCode() {
		return gamecode;
	}
	
	protected void play(MoveType movePlayerA, MoveType movePlayerB) {
		howManyPlay++;
		if(movePlayerA.beats(movePlayerB)) {
			scorePlayerA++;
			lastOutcome = GameOutcome.WIN;
		} else {
			scorePlayerB++;
			lastOutcome = GameOutcome.LOST;
		}
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Game ");
		str.append(gamecode);
		str.append(" played " );
		str.append(howManyPlay);
		str.append(" with score ");
		str.append(scorePlayerA);
		str.append("/");
		str.append(scorePlayerB);
		str.append(" with last outcome as ");
		str.append(lastOutcome);
		return str.toString();
	}

}
