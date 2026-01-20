package com.snakesandladders.classes.objects;

import java.util.Random;

/**
 * Class representation of a die to roll.
 */
public class Die {
    private int numSides;
    private static final int MIN_ROLL = 1;
    private static final int MIN_SIDES = 4;

    /**
     * 
     * @param numSides the number of sides required for the die. Must be greater
     *                 than or equal to 4
     * @throws InvalidSidesException
     */
    public Die(int numSides) throws InvalidSidesException {
        if (numSides < MIN_SIDES) {
            throw new InvalidSidesException("The number of sides must be greater than or equal to 4.");
        }
        this.numSides = numSides;
    }

    public int rollDie() {
        Random random = new Random();

        return random.nextInt((numSides - MIN_ROLL) + 1) + MIN_ROLL;
    }

    public class InvalidSidesException extends Exception {
        public InvalidSidesException(String message) {
            super(message);
        }
    }

}
