package com.citi.puzzle.enums;

import java.security.SecureRandom;


/*
 * Flavio Marchi (c) November 2015
 */
public enum MoveType {
	
    ROCK {
	   @Override
	   public boolean beats(MoveType other) {            
	        return other ==  SCISSOR;
	
	   }
    }
    ,PAPER {
	   @Override
	   public boolean beats(MoveType other) {            
	        return other ==  ROCK;
	
	   }
    }
    ,SCISSOR {
	   @Override
	   public boolean beats(MoveType other) {            
	        return other ==  PAPER;
	   }
    };
    
    private static final SecureRandom random = new SecureRandom();
    
    public static <T extends Enum<?>> T getRandomMove(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
    
    public abstract boolean beats(MoveType other);
	 
}
