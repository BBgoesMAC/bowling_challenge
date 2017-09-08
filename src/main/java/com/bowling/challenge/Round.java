package com.bowling.challenge;

import java.util.Random;

class Round {

    int amountPinsHitOnFirstToss;
    int amountPinsHitOnSecondToss;
    int amountPinsHitOnBonusToss;

    boolean hasStrike;
    boolean hasSpare;

    int id;
    int totalPoints;
    int currentSummedUpTotalPoints;

    Round(){
        this.hasStrike = false;
        this.hasSpare = false;
    }

    void setId(int id){
        this.id = id;
    }

    void setAmountPinsHitOnFirstToss(int amountPinsHitOnFirstToss){
        this.amountPinsHitOnFirstToss = amountPinsHitOnFirstToss;
        checkForStrike(amountPinsHitOnFirstToss);
    }

    void setAmountPinsHitOnSecondToss(int amountPinsHitOnSecondToss){
        this.amountPinsHitOnSecondToss = amountPinsHitOnSecondToss;
        checkForSpare(amountPinsHitOnSecondToss + amountPinsHitOnFirstToss);
    }

    void setAmountPinsHitOnBonusToss(int amountPinsHitOnBonusToss){
        this.amountPinsHitOnBonusToss = amountPinsHitOnBonusToss;
    }

    void setTotalPoints(int totalPoints){
        this.totalPoints = totalPoints;
    }

    void setCurrentSummedUpTotalPoints(int currentSummedUpTotalPoints){
        this.currentSummedUpTotalPoints = currentSummedUpTotalPoints;
    }

    void hasStrike(boolean hasStrike){
        this.hasStrike = hasStrike;
    }

    void hasSpare(boolean hasSpare){
        this.hasSpare = hasSpare;
    }

    int getId(){
        return id;
    }

    int getAmountPinsHitOnFirstToss(){
        return amountPinsHitOnFirstToss;
    }

    int getAmountPinsHitOnSecondToss(){
        return amountPinsHitOnSecondToss;
    }

    int getAmountPinsHitOnBonusToss(){
        return amountPinsHitOnBonusToss;
    }

    int getTotalPoints(){
        return totalPoints;
    }

    int getCurrentSummedUpTotalPoints(){
        return currentSummedUpTotalPoints;
    }

    void checkForStrike(int amountPinsHit){
        hasStrike = (amountPinsHit == 10);
    }

    void checkForSpare(int amountPinsHit){
        hasSpare = (amountPinsHit == 10);
    }

    /**
     * Return a random int value in the range between zero and 10.
     * In the first toss all(10) pins are available.
     * Therefore, the amount of knocked pins can be between zero and 10.
     */
    void doFirstToss(){
        setAmountPinsHitOnFirstToss( getRandomInt(9, 10) );

        //check for strike
        if (knockedAllPins(getAmountPinsHitOnFirstToss())){
            hasStrike(true);
        }
    }

    void doSecondToss(){
        setAmountPinsHitOnSecondToss(
                getRandomInt(0, 10 - getAmountPinsHitOnFirstToss()));

        // check for spare
        if(knockedAllPins(getAmountPinsHitOnFirstToss() + getAmountPinsHitOnSecondToss())){
            hasStrike(false);
            hasSpare(true);
        }
    }

    void doFirstBonusTossAfterStrike(){
        setAmountPinsHitOnSecondToss(
                getRandomInt(0, 10));
    }

    void doSecondBonusTossAfterStrike(){
        setAmountPinsHitOnBonusToss(
                getRandomInt(getAmountPinsHitOnSecondToss(), 10));
    }


    void doBonusTossAfterSpare(){
        setAmountPinsHitOnBonusToss(
                getRandomInt(0, 10));
    }

    boolean knockedAllPins(int knockedPins){
        return knockedPins == 10;
    }

    /**
     * Return a random int value in the range between the passed arguments.
     *
     * @param   min     the minimum integer as lower limit
     * @param   max     the maximum integer as upper limit
     * @return          return a random integer in the passed range
     */
    int getRandomInt(int min, int max){

        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random randomValue = new Random();
        return randomValue.nextInt((max - min) + 1) + min;
    }
}
