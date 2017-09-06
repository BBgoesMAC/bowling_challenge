package com.bowling.challenge;

import java.util.Random;
import java.util.Scanner;

public class BowlingChallenge {

    Round[] rounds = new Round[10];
    BowlingChallenge bowlingGame;
    int currentRound;
    int currentTossDone;
    int bonusCounter = 0;
    Scanner scanner;

    public static void main(String[] args){
        System.out.println("Welcome to the Bowling Challenge!");
        System.out.println(" ");
        Scanner scanUserInputNewGame = new Scanner(System.in);
        System.out.println("---Pres any key to start a new game---");
        BowlingChallenge bowlingGame = new BowlingChallenge();
        bowlingGame.startGame();


        bowlingGame.initBowlingGame();


    }

    public void startGame(){
        initBowlingGame();
        for (int i = 0; i < 10; i++) {

            System.out.println("Next toss? (press enter)");
            scanner = new Scanner(System.in);
            while(!scanner.hasNextLine()) {
            }

            rounds[i].doFirstToss();
            currentTossDone = 1;


            if (currentRound != 1){
                calculateSums(currentRound);
            }
            displayResultsTable();

            // skip second toss if the first toss was a strike
            if(!rounds[i].hasStrike){

                System.out.println("Next toss? (press enter)");
                scanner = new Scanner(System.in);
                while(!scanner.hasNextLine()) {
                }
                rounds[i].doSecondToss();
                currentTossDone = 2;

                // always update the sums after the second toss
                calculateSums(currentRound);
                if(rounds[i].hasSpare){
                    System.out.println("ITS A SPARE!!");
                }
                displayResultsTable();
            }else{
                System.out.println("ITS A STRIKE!!");
            }

            currentRound++;
            currentTossDone = 0;
        }
    }

    public void initBowlingGame(){

        for(int i = 0; i < rounds.length; i++) {
            Round round = new Round();
            rounds[i] = round;

            rounds[i].setId(i + 1);
            rounds[i].setAmountPinsHitOnFirstToss(-1);
            rounds[i].setAmountPinsHitOnSecondToss(-1);
            rounds[i].setTotalPoints(-1);
            rounds[i].setAmountPinsHitOnBonusRound(-1);
        }
        currentRound = 1;
    }

    public void displayResultsTable(){

        System.out.println(
                "________________________ ______________________________________\n" +
                        "|__"+rounds[0].getId()+"__|__"+rounds[1].getId()+"__|__"+rounds[2].getId()+
                        "__|__"+rounds[3].getId()+"__|__"+rounds[4].getId()+"__|__"+rounds[5].getId()+
                        "__|__"+rounds[6].getId()+"__|__"+rounds[7].getId()+"__|__"+rounds[8].getId()+
                        "__|__"+rounds[9].getId()+"___|\n" +
                        "| "+displayResultForToss(1, 1)+" |"+displayResultForToss(1, 2)+
                        "| "+displayResultForToss(2, 1)+" |"+displayResultForToss(2, 2)+
                        "| "+displayResultForToss(3, 1)+" |"+displayResultForToss(3, 2)+
                        "| "+displayResultForToss(4, 1)+" |"+displayResultForToss(4, 2)+
                        "| "+displayResultForToss(5, 1)+" |"+displayResultForToss(5, 2)+
                        "| "+displayResultForToss(6, 1)+" |"+displayResultForToss(6, 2)+
                        "| "+displayResultForToss(7, 1)+" |"+displayResultForToss(7, 2)+
                        "| "+displayResultForToss(8, 1)+" |"+displayResultForToss(8, 2)+
                        "| "+displayResultForToss(9, 1)+" |"+displayResultForToss(9, 2)+
                        "| "+displayResultForToss(10, 1)+" |"+displayResultForToss(10, 2)+
                        "|"+displayResultForToss(10, 3)+ "| \n" +
                        "|  "+displayTotalPointsForRound(1)+" |  "+displayTotalPointsForRound(2)+
                        " |  "+displayTotalPointsForRound(3)+" |  "+displayTotalPointsForRound(4)+
                        " |  "+displayTotalPointsForRound(5)+" |  "+displayTotalPointsForRound(6)+
                        " |  "+displayTotalPointsForRound(7)+" |  "+displayTotalPointsForRound(8)+
                        " |  "+displayTotalPointsForRound(9)+" |  "+displayTotalPointsForRound(10)+
                        "   |\n" +
                        "'-----'-----'-----'-----'-----'-----'-----'-----'-----'-------'");
    }

    public String displayResultForToss(int currentRound, int toss){
        int arrayIndexForCurrentRound = currentRound - 1;
        if(toss == 1){
            if(rounds[arrayIndexForCurrentRound].hasStrike){
                return "X";
            }else if(rounds[arrayIndexForCurrentRound].getAmountPinsHitOnFirstToss() == -1){
                return " ";
            }else return "" + rounds[arrayIndexForCurrentRound].getAmountPinsHitOnFirstToss();
        }else if(toss == 2){
            if(rounds[arrayIndexForCurrentRound].hasSpare){
                return "/";
            }else if(rounds[arrayIndexForCurrentRound].getAmountPinsHitOnSecondToss() == -1) {
                return " ";
            }else return "" + rounds[arrayIndexForCurrentRound].getAmountPinsHitOnSecondToss();
        }else if (toss == 3) {
            if(currentRound == 10 && rounds[arrayIndexForCurrentRound].getAmountPinsHitOnBonusRound() == 10){
                return "X";
            }else if (rounds[arrayIndexForCurrentRound].getAmountPinsHitOnBonusRound() == -1){
                return " ";
            }else return "" + rounds[arrayIndexForCurrentRound].getAmountPinsHitOnBonusRound();
        }else{
            throw new IllegalArgumentException("bad value for toss passed");
        }
    }

    public String displayTotalPointsForRound(int currentRound){

        int arrayIndexForCurrentRound = currentRound - 1;
        if (rounds[arrayIndexForCurrentRound].getTotalPoints() == -1)
            return "  ";
        else
            return "" + rounds[arrayIndexForCurrentRound].getCurrentSummedUpTotalPoints();
    }

    public void calculateSums(int currentRound){
        int currentSumOfAllPlayedRounds = 0;
        int arrayIndexForCurrentRound = currentRound - 1;
        if(rounds[arrayIndexForCurrentRound].hasStrike){
            //bonusCounter += 2;
        }else if(rounds[arrayIndexForCurrentRound].hasSpare){
            System.out.println("Spare increases bonus");
            bonusCounter++;
        }else if(currentTossDone == 2){
            //if(bonusCounter == 1){
                //rounds[arrayIndexForCurrentRound - 1].setTotalPoints(rounds[arrayIndexForCurrentRound].getAmountPinsHitOnFirstToss() + rounds[arrayIndexForCurrentRound].getAmountPinsHitOnSecondToss() + 10);
                //rounds[arrayIndexForCurrentRound].setTotalPoints(rounds[arrayIndexForCurrentRound].getAmountPinsHitOnFirstToss() + rounds[arrayIndexForCurrentRound].getAmountPinsHitOnSecondToss());
                //System.out.println("Apply bonus: " + rounds[arrayIndexForCurrentRound - 1].getTotalPoints());
                //bonusCounter = 0;
            //}else{
                rounds[arrayIndexForCurrentRound].setTotalPoints(rounds[arrayIndexForCurrentRound].getAmountPinsHitOnFirstToss() + rounds[arrayIndexForCurrentRound].getAmountPinsHitOnSecondToss());
            //}
        }else {
            // update previous cells with spares and strikes
        }
        System.out.println("currentSumBeforeLoop" +
                ": " + currentSumOfAllPlayedRounds);

        for (int i = 0; i < currentRound; i++){
            System.out.println("getTotalPoints: " + rounds[i].getTotalPoints());
            if(currentTossDone == 2){
                if(i >= 1) {
                    System.out.println("currentPoints: " + rounds[i].getTotalPoints());
                    currentSumOfAllPlayedRounds += rounds[i].getTotalPoints();
                    rounds[i].setCurrentSummedUpTotalPoints(currentSumOfAllPlayedRounds);
                }else{
                    currentSumOfAllPlayedRounds = rounds[i].getTotalPoints();
                    rounds[i].setCurrentSummedUpTotalPoints(currentSumOfAllPlayedRounds);
                }
            }
        }
        System.out.println("currentSum: " + currentSumOfAllPlayedRounds);
    }
}
