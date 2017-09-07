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
        while(true) {
            System.out.println("Welcome to the Bowling Challenge!");
            System.out.println(" ");
            Scanner scanUserInputNewGame = new Scanner(System.in);
            System.out.println("--- Press any key to start a new game ---");
            while(!scanUserInputNewGame.hasNextLine()) {
            }
            BowlingChallenge bowlingGame = new BowlingChallenge();
            bowlingGame.startGame();


            bowlingGame.initBowlingGame();
            System.out.println("Thank you for playing the Bowling Challenge");
            scanUserInputNewGame = new Scanner(System.in);
            System.out.println("--- Press any key to restart the game ---");
            while(!scanUserInputNewGame.hasNextLine()) {
            }
        }

    }

    public void startGame(){
        initBowlingGame();
        for (int i = 0; i < 10; i++) {
            System.out.println("Toss the bowling ball by pressing enter");
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

                System.out.println("Second toss? (press enter)");
                scanner = new Scanner(System.in);
                while(!scanner.hasNextLine()) {
                }
                rounds[i].doSecondToss();
                currentTossDone = 2;

                // always update the sums after the second toss
                calculateSums(currentRound);
                if(rounds[i].hasSpare){
                    System.out.println(" --- NICE SPARE --- ");
                    System.out.println(" ");
                }
                displayResultsTable();
            }else{
                System.out.println(" ----- !!!! ---- ITS A STRIKE ----- !!!! ----");
                System.out.println(" ");

            }

            if(currentRound == 10 && rounds[i].hasStrike){
                System.out.println("You earned a bonus toss! (press enter to toss)");
                scanner = new Scanner(System.in);
                while(!scanner.hasNextLine()) {
                }
                rounds[i].doFirstBonusTossAfterStrike();
                displayResultsTable();

                System.out.println("Do your second bonus toss! (press enter)");
                scanner = new Scanner(System.in);
                while(!scanner.hasNextLine()) {
                }
                rounds[i].doSecondBonusTossAfterStrike();
                displayResultsTable();
            }else if(currentRound == 10 && rounds[i].hasSpare){
                System.out.println("You earned a bonus toss! (press enter)");
                scanner = new Scanner(System.in);
                while(!scanner.hasNextLine()) {
                }
                rounds[i].doBonusTossAfterSpare();
                currentTossDone = 3;
                displayResultsTable();
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
                        "|"+String.format("%5s", displayTotalPointsForRound(1))+
                        "|"+String.format("%5s", displayTotalPointsForRound(2))+
                        "|"+String.format("%5s", displayTotalPointsForRound(3))+
                        "|"+String.format("%5s", displayTotalPointsForRound(4))+
                        "|"+String.format("%5s", displayTotalPointsForRound(5))+
                        "|"+String.format("%5s", displayTotalPointsForRound(6))+
                        "|"+String.format("%5s", displayTotalPointsForRound(7))+
                        "|"+String.format("%5s", displayTotalPointsForRound(8))+
                        "|"+String.format("%5s", displayTotalPointsForRound(9))+
                        "|"+String.format("%7s", displayTotalPointsForRound(10))+
                        "|\n" +
                        "'-----'-----'-----'-----'-----'-----'-----'-----'-----'-------'");
    }

    public String displayResultForToss(int round, int toss){
        int arrayIndexForCurrentRound = round - 1;

        if(toss == 1){
            if(rounds[arrayIndexForCurrentRound].hasStrike){
                return "X";
            }else if(rounds[arrayIndexForCurrentRound].getId() > currentRound){
                return " ";
            }else return "" + rounds[arrayIndexForCurrentRound].getAmountPinsHitOnFirstToss();
        }else if(toss == 2){
            if(rounds[arrayIndexForCurrentRound].hasSpare){
                return "/";
            }else if(rounds[arrayIndexForCurrentRound].getId() > currentRound||
                    (rounds[arrayIndexForCurrentRound].getId() == currentRound && currentTossDone == 1)) {
                return " ";
            }else if(rounds[arrayIndexForCurrentRound].hasStrike ||
                    (rounds[arrayIndexForCurrentRound].hasStrike && currentRound != 10)){
                // if first toss was a strike only display value for second toss if in round 10
                return " ";
            }else return "" + rounds[arrayIndexForCurrentRound].getAmountPinsHitOnSecondToss();
        }else if (toss == 3) {
            if (currentRound == 10 && rounds[arrayIndexForCurrentRound].getAmountPinsHitOnBonusToss() == 10) {
                return "X";
            } else if (currentRound == 10 && (
                    (rounds[9].hasSpare && currentTossDone == 3) ||
                            (rounds[9].hasStrike && currentTossDone == 3) ||
                            (!rounds[9].hasStrike && !rounds[9].hasSpare && currentTossDone == 2))) {
                return "" + rounds[arrayIndexForCurrentRound].getAmountPinsHitOnBonusToss();
            } else if (currentRound != 10) {
                return " ";
            }
        }else throw new IllegalArgumentException("bad value for toss passed");
        return " ";
    }

    public String displayTotalPointsForRound(int round){

        int arrayIndexForCurrentRound = round - 1;
        if (rounds[arrayIndexForCurrentRound].getId() > currentRound ||
                (rounds[arrayIndexForCurrentRound].getId() == currentRound && currentTossDone == 1) &&
                        currentRound != 10) {
            return "  ";
        }else if (currentRound == 10 && (
                (rounds[9].hasSpare && currentTossDone == 3) ||
                (rounds[9].hasStrike && currentTossDone == 3) ||
                        (!rounds[9].hasStrike && !rounds[9].hasSpare && currentTossDone == 2)
                )){
            // wait for bonus toss in round 10 before displaying the total points
            return "" + rounds[arrayIndexForCurrentRound].getCurrentSummedUpTotalPoints();
        }else if(currentRound != 10){
            return "" + rounds[arrayIndexForCurrentRound].getCurrentSummedUpTotalPoints();
        }else return " ";

    }

    public void calculateSums(int currentRound){
        int strikeBonus = 0;
        int spareBonus = 0;
        for (int i = 0; i < currentRound; i++){
            if(rounds[i].hasStrike){
                strikeBonus = calculateSumForStrike(i);


                if(i == 0){
                    rounds[i].setCurrentSummedUpTotalPoints(rounds[i].getTotalPoints() + strikeBonus);
                }else {
                    rounds[i].setCurrentSummedUpTotalPoints(rounds[i].getTotalPoints() +
                            rounds[i - 1].getCurrentSummedUpTotalPoints());

                    rounds[i].setTotalPoints(rounds[i - 1].getCurrentSummedUpTotalPoints() + strikeBonus);
                    rounds[i].setCurrentSummedUpTotalPoints(rounds[i].getTotalPoints());
                }

            }else if(rounds[i].hasSpare){
                spareBonus = calculateSumForSpare(i);
                if(i == 0){
                    rounds[i].setCurrentSummedUpTotalPoints(rounds[i].getTotalPoints() + spareBonus);
                }else {
                    rounds[i].setCurrentSummedUpTotalPoints(rounds[i].getTotalPoints() +
                            rounds[i - 1].getCurrentSummedUpTotalPoints());
                    rounds[i].setTotalPoints(spareBonus);
                    rounds[i].setCurrentSummedUpTotalPoints(rounds[i].getTotalPoints() +
                            rounds[i - 1].getCurrentSummedUpTotalPoints());
                }
            }else{
                rounds[i].setTotalPoints(rounds[i].getAmountPinsHitOnFirstToss() +
                        rounds[i].getAmountPinsHitOnSecondToss());
                if(i == 0){
                    rounds[i].setCurrentSummedUpTotalPoints(rounds[i].getTotalPoints());
                }else {
                    rounds[i].setCurrentSummedUpTotalPoints(rounds[i].getTotalPoints() +
                            rounds[i - 1].getCurrentSummedUpTotalPoints());
                }
            }
        }
    }

    public int calculateSumForStrike(int round){
        int strikeBonus;
        if(round + 2 > 9){
            if(rounds[9].hasStrike){
                strikeBonus = 20 + rounds[9].getAmountPinsHitOnSecondToss();
            }else if(rounds[9].getAmountPinsHitOnSecondToss() == 10){
                strikeBonus = 30;
            }else{
                strikeBonus = 10 + rounds[9].getAmountPinsHitOnFirstToss() + rounds[9].getAmountPinsHitOnSecondToss();
            }
        }else if(rounds[round + 1].hasStrike) {
                if (rounds[round + 2].hasStrike) {
                    strikeBonus = 30;
                } else {
                   strikeBonus = 20 + rounds[round + 2].getAmountPinsHitOnFirstToss();
                }
        }else if(rounds[round + 1].hasSpare){
            strikeBonus = 20;
        }else {
            strikeBonus = 10 + rounds[round + 1].getAmountPinsHitOnFirstToss() + rounds[round + 1].getAmountPinsHitOnSecondToss();
        }
        return strikeBonus;
    }

    public int calculateSumForSpare(int round){
        int spareBonus;
        if(round + 1 > 9){
            if(rounds[9].hasStrike){
                spareBonus = 20;
            }else spareBonus = 10 + rounds[9].getAmountPinsHitOnFirstToss();

        }else if(rounds[round + 1].hasStrike){
            spareBonus = 20;
        }else spareBonus = rounds[round + 1].getAmountPinsHitOnFirstToss() + 10;
        return spareBonus;
    }
}
