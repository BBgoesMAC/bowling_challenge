package com.bowling.challenge;

import java.util.Random;
import java.util.Scanner;

public class BowlingChallenge {

    Round[] rounds = new Round[9];
    int currentRound;

    public static void main(String[] args){
        System.out.println("Welcome to the Bowling Challenge!");
        System.out.println("Press Enter for new Game");
        Scanner scanner = new Scanner(System.in);
        //while(!scanner.hasNextLine()) {
        //}
        initBowlingGame();

        for (int i = 0; i < 10; i++) {

            displayResultsForRound(i);

            System.out.println("Next toss? (press enter)");
            while(!scanner.hasNextLine()) {
            }
            doFirstToss(i);

            displayResultsForRound(i);

            System.out.println("Next toss? (press enter)");
            while(!scanner.hasNextLine()) {
            }
            doSecondToss(i);

        }
    }

    public void initBowlingGame(){
        rounds[0].setId(1);
        System.out.println("rounds: " + rounds[0].getId());

        for(int i = 0; i < rounds.length; i++){
            System.out.println("i: " + i);

            rounds[i].setId(i + 1);
            rounds[i].setAmountPinsHitOnFirstToss(-1);
            rounds[i].setAmountPinsHitOnSecondToss(-1);
            rounds[i].setTotalPoints(-1);
        }

        currentRound = 1;
    }

    public void displayResultsForRound(int currentRound){

        System.out.println(
                "________________________ ______________________________________\n" +
                        "|__"+rounds[0].getId()+"__|__"+rounds[1].getId()+"__|__"+rounds[2].getId()+
                        "__|__"+rounds[3].getId()+"__|__"+rounds[4].getId()+"__|__"+rounds[5].getId()+
                        "__|__"+rounds[6].getId()+"__|__"+rounds[7].getId()+"__|__"+rounds[8].getId()+
                        "__|__"+rounds[9].getId()+"___|\n" +
                        "| "+displayResultForToss(currentRound, 1)+" |"+displayResultForToss(currentRound, 2)+"|  8|/|  3|6|   |X|  9|/|  8|/|   |X|  9|/|  9|G|  X|X|X|\n" +
                        " |  "+displayTotalPointsForRound(currentRound)+" |  "+displayTotalPointsForRound(currentRound)+
                        " |  "+displayTotalPointsForRound(currentRound)+" |  "+displayTotalPointsForRound(currentRound)+
                        " |  "+displayTotalPointsForRound(currentRound)+" |  "+displayTotalPointsForRound(currentRound)+
                        " |  "+displayTotalPointsForRound(currentRound)+" |  "+displayTotalPointsForRound(currentRound)+
                        " |  "+displayTotalPointsForRound(currentRound)+" |  "+displayTotalPointsForRound(currentRound)+
                        "  |\n" +
                        "'-----'-----'-----'-----'-----'-----'-----'-----'-----'-------'");
    }

    public String displayResultForToss(int currentRound, int toss){
        if(toss == 1){
            if(rounds[currentRound].hasStrike){
                return "X";
            }else if(rounds[currentRound].getAmountPinsHitOnFirstToss() == -1){
                return " ";
            }else return "" + rounds[currentRound].getAmountPinsHitOnFirstToss();
        }else if(toss == 2){
            if(rounds[currentRound].hasSpare){
                return "/";
            }else if(rounds[currentRound].getAmountPinsHitOnFirstToss() == -1) {
                return " ";
            }else return "" + rounds[currentRound].getAmountPinsHitOnSecondToss();
        }else throw new IllegalArgumentException("bad value for toss passed");
    }

    public String displayTotalPointsForRound(int currentRound){
        return "" + rounds[currentRound].getTotalPoints();
    }

    /**
     * Return a random int value in the range between zero and 10.
     * In the first toss all(10) pins are available.
     * Therefore, the amount of knocked pins can be between zero and 10.
     */
    public void doFirstToss(int currentRound){
        rounds[currentRound].setAmountPinsHitOnFirstToss( getRandomInt(0, 10) );

        //check for strike
        if (knockedAllPins(rounds[currentRound].getAmountPinsHitOnFirstToss())){
            rounds[currentRound].hasStrike(true);
        }
    }

    public void doSecondToss(int currentRound){
        rounds[currentRound].setAmountPinsHitOnSecondToss(
                getRandomInt(0, 10 - rounds[currentRound].getAmountPinsHitOnSecondToss()));

        // check for spare
        if(knockedAllPins(rounds[currentRound].getAmountPinsHitOnFirstToss())){
            rounds[currentRound].hasSpare(true);
        }
    }

    public boolean knockedAllPins(int knockedPins){
        return knockedPins == 10;
    }

    /**
     * Return a random int value in the range between the passed arguments.
     *
     * @param   min     the minimum integer as lower limit
     * @param   max     the maximum integer as upper limit
     * @return          return a random integer in the passed range
     */
    public int getRandomInt(int min, int max){

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random randomValue = new Random();
        int randomInt = randomValue.nextInt((max - min) + 1) + min;
        return randomInt;
    }


}
