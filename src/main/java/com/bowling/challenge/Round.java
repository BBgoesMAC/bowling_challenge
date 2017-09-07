package com.bowling.challenge;

import java.util.Random;

public class Round {

    int id;
    int amountPinsHitOnFirstToss;
    int amountPinsHitOnSecondToss;
    int totalPoints;
    int currentSummedUpTotalPoints;
    int amountPinsHitOnBonusToss;

    int bonusPoints;

    boolean hasStrike;
    boolean hasSpare;

    public Round(){
        this.hasStrike = false;
        this.hasSpare = false;
        this.bonusPoints = 0;
    }

    public Round(int id){
        this.id = id;
        this.hasStrike = false;
        this.hasSpare = false;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setAmountPinsHitOnFirstToss(int amountPinsHitOnFirstToss){
        this.amountPinsHitOnFirstToss = amountPinsHitOnFirstToss;
        if(amountPinsHitOnFirstToss == 10){
            hasStrike = true;
        }
    }

    public void setAmountPinsHitOnSecondToss(int amountPinsHitOnSecondToss){
        this.amountPinsHitOnSecondToss = amountPinsHitOnSecondToss;
        if(amountPinsHitOnSecondToss + amountPinsHitOnFirstToss == 10){
            hasSpare = true;
        }
    }

    public void setAmountPinsHitOnBonusToss(int amountPinsHitOnBonusToss){
        this.amountPinsHitOnBonusToss = amountPinsHitOnBonusToss;
    }

    public void setTotalPoints(int totalPoints){
        this.totalPoints = totalPoints;
    }

    public void setCurrentSummedUpTotalPoints(int currentSummedUpTotalPoints){
        this.currentSummedUpTotalPoints = currentSummedUpTotalPoints;
    }

    public void hasStrike(boolean hasStrike){
        this.hasStrike = hasStrike;
    }

    public void hasSpare(boolean hasSpare){
        this.hasSpare = hasSpare;
    }

    public int getId(){
        return id;
    }

    public int getAmountPinsHitOnFirstToss(){
        return amountPinsHitOnFirstToss;
    }

    public int getAmountPinsHitOnSecondToss(){
        return amountPinsHitOnSecondToss;
    }

    public int getAmountPinsHitOnBonusToss(){
        return amountPinsHitOnBonusToss;
    }

    public int getTotalPoints(){
        return totalPoints;
    }

    public int getCurrentSummedUpTotalPoints(){
        return currentSummedUpTotalPoints;
    }

    public int getBonusPoints(){
        return bonusPoints;
    }



    /**
     * Return a random int value in the range between zero and 10.
     * In the first toss all(10) pins are available.
     * Therefore, the amount of knocked pins can be between zero and 10.
     */
    public void doFirstToss(){
        setAmountPinsHitOnFirstToss( getRandomInt(0, 10) );

        //check for strike
        if (knockedAllPins(getAmountPinsHitOnFirstToss())){
            hasStrike(true);
        }
    }

    public void doSecondToss(){
        setAmountPinsHitOnSecondToss(
                getRandomInt(0, 10 - getAmountPinsHitOnFirstToss()));

        // check for spare
        if(knockedAllPins(getAmountPinsHitOnFirstToss() + getAmountPinsHitOnSecondToss())){
            hasStrike(false);
            hasSpare(true);
        }
    }

    public void doFirstBonusTossAfterStrike(){
        setAmountPinsHitOnSecondToss(
                getRandomInt(0, 10));
    }

    public void doSecondBonusTossAfterStrike(){
        setAmountPinsHitOnBonusToss(
                getRandomInt(0, 10));
    }


    public void doBonusTossAfterSpare(){
        setAmountPinsHitOnBonusToss(
                getRandomInt(0, 10));
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

        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random randomValue = new Random();
        int randomInt = randomValue.nextInt((max - min) + 1) + min;
        return randomInt;
    }
}
