package com.bowling.challenge;

import java.util.Scanner;

public class BowlingGame {

    Round[] rounds = new Round[10];
    int currentRound;

    private int currentTossDone;

    private final int STRIKE_AFTER_STRIKE_BONUS = 20, STRIKE_AFTER_SPARE_BONUS = 20, SPARE_AFTER_STRIKE_BONUS = 20;
    private final int STRIKE_BONUS = 10, SPARE_BONUS = 10;
    private final int DOUBLE_STRIKE_AFTER_STRIKE_BONUS = 30;

    public static void main(String[] args){
        System.out.println("Welcome to the Bowling Challenge!");
        while(true) {
            System.out.println(" ");
            Scanner scanUserInputNewGame = new Scanner(System.in);
            System.out.println("--- Press enter to start a new game ---");
            while(!scanUserInputNewGame.hasNextLine()) {
            }
            BowlingGame bowlingGame = new BowlingGame();
            bowlingGame.startGame();


            bowlingGame.initBowlingGame();
            System.out.println("==> Thank you for playing the Bowling Challenge");
        }

    }

    /**
     * Starts a new game and controls the user input.
     * Before tosses, always waits for the user to press enter.
     */
    private void startGame(){
        Scanner scanUserInput;
        initBowlingGame();

        for (int i = 0; i < 10; i++) {
            System.out.println("=> Toss the bowling ball by pressing enter ...");
            scanUserInput = new Scanner(System.in);
            while(!scanUserInput.hasNextLine()) {
            }

            rounds[i].doFirstToss();
            currentTossDone = 1;

            if (currentRound != 1){
                calculateSums(currentRound);
            }
            displayResultsTable();

            // skip second toss if the first toss was a strike
            if(rounds[i].hasStrike){
                System.out.println(" ----- !!!! ---- ITS A STRIKE ----- !!!! ----");
                System.out.println("");

            }else{
                System.out.println("=> Toss the bowling ball by pressing enter ...");
                scanUserInput = new Scanner(System.in);
                while(!scanUserInput.hasNextLine()) {
                }
                rounds[i].doSecondToss();
                currentTossDone = 2;

                // always update the sums after the second toss
                calculateSums(currentRound);
                displayResultsTable();
                if(rounds[i].hasSpare){
                    System.out.println(" --- NICE SPARE --- ");
                    System.out.println(" ");
                }
            }

            // in the last round, check if there was a strike or a spare
            // if so, the user gets a bonus toss
            if(currentRound == 10 && rounds[i].hasStrike){
                System.out.println("You earned a bonus toss! (press enter to toss)");
                scanUserInput = new Scanner(System.in);
                while(!scanUserInput.hasNextLine()) {
                }
                rounds[i].doFirstBonusTossAfterStrike();
                currentTossDone = 2;
                displayResultsTable();

                System.out.println("Do your second bonus toss! (press enter)");
                scanUserInput = new Scanner(System.in);
                while(!scanUserInput.hasNextLine()) {
                }
                rounds[i].doSecondBonusTossAfterStrike();
                currentTossDone = 3;
                displayResultsTable();
            }else if(currentRound == 10 && rounds[i].hasSpare){
                System.out.println("You earned a bonus toss! (press enter)");
                scanUserInput = new Scanner(System.in);
                while(!scanUserInput.hasNextLine()) {
                }
                rounds[i].doBonusTossAfterSpare();
                currentTossDone = 3;
                displayResultsTable();
            }
            currentRound++;
            currentTossDone = 0;
        }
    }

    /**
     * Initializes a new game.
     * To do so, we need to instantiate and initialize 10 Rounds.
     */
    void initBowlingGame(){

        for(int i = 0; i < rounds.length; i++) {
            Round round = new Round();
            rounds[i] = round;

            rounds[i].setId(i + 1);
        }
        currentRound = 1;
    }


    /**
     * Displays the current results as ascii table and prints it to the console.
     */
    private void displayResultsTable(){

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

    /**
     * Every round has two toss results.
     * Round 10 can have three toss results if there was a spare or a strike
     *
     * @param round the round to request the results for
     * @param toss the toss within the round to request results for
     * @return the results for the requested round and toss as a string.
     *         This can either be an X as a marker for a strike,
     *         a / as a marker for a spare,
     *         an empty space if there are currently no results(e.g. round not played yet)
     *         or the points as a string.
     */
    private String displayResultForToss(int round, int toss){

        // round 1 is stored at round[0], therefore round must be decreased to access the round
        int arrayIndexForCurrentRound = round - 1;

        if(toss == 1){
            if(rounds[arrayIndexForCurrentRound].hasStrike){
                return "X";
            }else if(rounds[arrayIndexForCurrentRound].getId() > currentRound){

                // round not played yet
                return " ";

            }else return "" + rounds[arrayIndexForCurrentRound].getAmountPinsHitOnFirstToss();
        }else if(toss == 2){
            if(rounds[arrayIndexForCurrentRound].hasSpare){

                // if we are in last round and there was a strike on the first toss,
                // a spare means that we must display a strike
                if(currentRound == 10 && rounds[arrayIndexForCurrentRound].hasStrike && currentTossDone == 2){
                    return "X";
                }else return "/";
            }else if(rounds[arrayIndexForCurrentRound].getId() > currentRound||
                    (rounds[arrayIndexForCurrentRound].getId() == currentRound && currentTossDone == 1)) {
                // do not display second toss if we have not reached this round yet
                return " ";
            }else if(rounds[arrayIndexForCurrentRound].hasStrike && round != 10){
                // if first toss was a strike only display value for second toss if in round 10
                return " ";
            }else return "" + rounds[arrayIndexForCurrentRound].getAmountPinsHitOnSecondToss();
        }else if (toss == 3) {
            if (currentRound == 10 && rounds[arrayIndexForCurrentRound].getAmountPinsHitOnBonusToss() == 10) {
                return "X";
            } else if (currentRound == 10 && (
                    (rounds[9].hasSpare && currentTossDone == 3) ||
                            (rounds[9].hasStrike && currentTossDone == 3))) {
                return "" + rounds[arrayIndexForCurrentRound].getAmountPinsHitOnBonusToss();
            } else return " ";

        }else throw new IllegalArgumentException("bad value for toss passed");
        //return " ";
    }

    /**
     * @param round the current round the user is in
     * @return returns the string of total points which must be displayed for the passed round

     */
    private String displayTotalPointsForRound(int round) {

        // round 1 is stored in array[0]
        int roundIndex = round - 1;
        if (round != 10){
            if (rounds[roundIndex].getId() > currentRound ||
                    (rounds[roundIndex].getId() == currentRound && currentTossDone == 1)) {
                return " ";
            } else if (currentTossDone == 2 ){
                // wait for bonus toss in round 10 before displaying the total points
                return "" + rounds[roundIndex].getCurrentSummedUpTotalPoints();
            } else
                return "" + rounds[roundIndex].getCurrentSummedUpTotalPoints();
        }else if(((rounds[roundIndex].hasStrike || rounds[roundIndex].hasSpare) &&
            currentTossDone != 3) || currentTossDone == 1){
            return " ";
        }else if (currentRound == 10){
            return "" + rounds[roundIndex].getCurrentSummedUpTotalPoints();
        }else return "";
    }

    /**
     * Calculates the summed up total points for each round and stores it in the round
     * As a strike or spare can impact the previous sum, e.g. strike followed by strike + spare
     * this method iterates over all rounds and updates their sum.
     * @param currentRound the current round that is played. Only update round 1 up to currentRound
     *                     as the rounds > currentRound are all 0
     */
    private void calculateSums(int currentRound){
        int strikeBonus;
        int spareBonus;
        for (int i = 0; i < currentRound; i++){

            if(rounds[i].hasStrike){
                strikeBonus = calculateStrikeBonus(i);
                if(i == 0){
                    rounds[i].setCurrentSummedUpTotalPoints(rounds[i].getTotalPoints() + strikeBonus);
                }else {
                    rounds[i].setCurrentSummedUpTotalPoints(rounds[i].getTotalPoints() +
                            rounds[i - 1].getCurrentSummedUpTotalPoints());

                    rounds[i].setTotalPoints(rounds[i - 1].getCurrentSummedUpTotalPoints() + strikeBonus);
                    rounds[i].setCurrentSummedUpTotalPoints(rounds[i].getTotalPoints());
                }

            }else if(rounds[i].hasSpare){
                spareBonus = calculateSpareBonus(i);
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

    /**
     * Calculates the bonus sum in case of a strike in round passed as param
     * @param round the round for which the strike bonus is calculated
     * @return returns the sum of the strike bonus.
     */
    int calculateStrikeBonus(int round){
        int strikeBonus;
        // check if in last round
        if(round + 2 > 9){
            if(rounds[9].hasStrike){
                strikeBonus = STRIKE_AFTER_STRIKE_BONUS + rounds[9].getAmountPinsHitOnSecondToss();
            }else if(rounds[9].hasSpare){
                //
                strikeBonus = DOUBLE_STRIKE_AFTER_STRIKE_BONUS;
            }else{
                strikeBonus = STRIKE_BONUS + rounds[9].getAmountPinsHitOnFirstToss() + rounds[9].getAmountPinsHitOnSecondToss();
            }
        }else if(rounds[round + 1].hasStrike) {
            // after strike, there is another strike
                if (rounds[round + 2].hasStrike) {
                    // strike followed by two other strikes
                    strikeBonus = DOUBLE_STRIKE_AFTER_STRIKE_BONUS;
                } else {
                    // strike followed by strike followed by spare -> take first toss of round with spare into account
                   strikeBonus = STRIKE_AFTER_STRIKE_BONUS + rounds[round + 2].getAmountPinsHitOnFirstToss();
                }
        }else if(rounds[round + 1].hasSpare){
            // strike followed by spare
            strikeBonus = SPARE_AFTER_STRIKE_BONUS;
        }else {
            // strike bonus followed by neither strike nor spare
            strikeBonus = STRIKE_BONUS + rounds[round + 1].getAmountPinsHitOnFirstToss()
                    + rounds[round + 1].getAmountPinsHitOnSecondToss();
        }
        return strikeBonus;
    }

    /**
     * Calculates the bonus sum in case of a spare in round passed as param
     * @param round the roundArrayIndex for which the strike bonus is calculated
     * @return returns the sum of the spare bonus
     */
    int calculateSpareBonus(int round){
        int spareBonus;
        if(round + 1 > 9){
            if(rounds[9].hasStrike){
                spareBonus = STRIKE_AFTER_SPARE_BONUS;
            }else spareBonus = SPARE_BONUS + rounds[9].getAmountPinsHitOnFirstToss();

        }else if(rounds[round + 1].hasStrike){
            spareBonus = STRIKE_AFTER_SPARE_BONUS;
        }else spareBonus = SPARE_BONUS + rounds[round + 1].getAmountPinsHitOnFirstToss();
        return spareBonus;
    }
}
