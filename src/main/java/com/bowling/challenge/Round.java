package com.bowling.challenge;

public class Round {

    int id;
    int amountPinsHitOnFirstToss;
    int amountPinsHitOnSecondToss;
    int totalPoints;
    int amountPinsHitOnBonusRound;

    boolean hasStrike;
    boolean hasSpare;

    public Round(){
        this.hasStrike = false;
        this.hasSpare = false;
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

    public void setTotalPoints(int totalPoints){
        this.totalPoints = totalPoints;
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

    public int getTotalPoints(){
        return totalPoints;
    }
}
