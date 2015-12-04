package com.citi.puzzle.enums;



/*
 * Flavio Marchi (c) November 2015
 */
public enum Command {

	CREATE_GAME_PLAYER_VS_COMPUTER(1),
	CREATE_GAME_COMPUTER_VS_COMPUTER(2),
	PLAY_DIFFERENT_GAME(3),
	MAKE_MOVE(4),
	JUST_PLAY(5),
	EXIT(6);
	
	private Integer id;

	Command(Integer id) {
        this.id = id;
    }
	
	public static Command getById(Integer id) {
	    for(Command e : values()) {
	        if(e.id.equals(id)) return e;
	    }
	    return null;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(id);
		str.append(" ");
		str.append(Command.getById(id).name());
		return str.toString();
	}

}
