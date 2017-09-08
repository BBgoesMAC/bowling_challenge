package com.bowling.challenge;


import org.junit.Assert;
import org.junit.Test;

public class RoundTest {

    private Round[] rounds = new Round[9];

    @Test
    public void constructorRoundTest(){
        Round round = new Round();
        round.setId(1);
        Assert.assertEquals(false, round.hasStrike);
        Assert.assertEquals(false, round.hasSpare);
        Assert.assertEquals(1, round.id);
    }

    @Test
    public void correctlySetId(){
        Round round = new Round();
        round.setId(4);
        Assert.assertEquals(4, round.id);
    }

    @Test
    public void correctlySetAmountPinsHitOnFirstToss(){
        Round round = new Round();
        round.setAmountPinsHitOnFirstToss(4);
        Assert.assertEquals(4, round.amountPinsHitOnFirstToss);
        round.setAmountPinsHitOnFirstToss(10);
        Assert.assertEquals(10, round.amountPinsHitOnFirstToss);
    }

    @Test
    public void correctlySetAmountPinsHitOnSecondToss(){
        Round round = new Round();
        round.setAmountPinsHitOnSecondToss(4);
        Assert.assertEquals(4, round.amountPinsHitOnSecondToss);
        round.setAmountPinsHitOnSecondToss(10);
        Assert.assertEquals(10, round.amountPinsHitOnSecondToss);
    }

    @Test
    public void correctlySetAmountPinsHitOnBonusToss(){
        Round round = new Round();
        round.setAmountPinsHitOnBonusToss(4);
        Assert.assertEquals(4, round.amountPinsHitOnBonusToss);
        round.setAmountPinsHitOnBonusToss(10);
        Assert.assertEquals(10, round.amountPinsHitOnBonusToss);
    }

    @Test
    public void correctlySetTotalPoints(){
        Round round = new Round();
        round.setTotalPoints(72);
        Assert.assertEquals(72, round.totalPoints);
        round.setTotalPoints(91);
        Assert.assertEquals(91, round.totalPoints);
    }

    @Test
    public void correctlySetCurrentSummedUpTotalPoints(){
        Round round = new Round();
        round.setCurrentSummedUpTotalPoints(72);
        Assert.assertEquals(72, round.currentSummedUpTotalPoints);
        round.setCurrentSummedUpTotalPoints(91);
        Assert.assertEquals(91, round.currentSummedUpTotalPoints);
    }

    @Test
    public void correctlySetStrike(){
        Round round = new Round();
        round.hasStrike(true);
        Assert.assertEquals(true, round.hasStrike);
        Assert.assertEquals(false, round.hasSpare);
        round.hasStrike(false);
        Assert.assertEquals(false, round.hasStrike);
    }

    @Test
    public void correctlySetSpare(){
        Round round = new Round();
        round.hasSpare(true);
        Assert.assertEquals(true, round.hasSpare);
        round.hasSpare(false);
        Assert.assertEquals(false, round.hasSpare);
    }

    @Test
    public void correctlyGetIdTest(){
        Round round = new Round();
        round.id = 4;
        Assert.assertEquals(4, round.getId());
    }

    @Test
    public void correctlyGetAmountPinsHitOnFirstToss(){
        Round round = new Round();
        round.amountPinsHitOnFirstToss = 4;
        Assert.assertEquals(4, round.getAmountPinsHitOnFirstToss());
    }

    @Test
    public void correctlyGetAmountPinsHitOnSecondToss(){
        Round round = new Round();
        round.amountPinsHitOnSecondToss = 4;
        Assert.assertEquals(4, round.getAmountPinsHitOnSecondToss());
    }

    @Test
    public void correctlyGetAmountPinsHitOnBonusToss(){
        Round round = new Round();
        round.amountPinsHitOnBonusToss = 5;
        Assert.assertEquals(5, round.getAmountPinsHitOnBonusToss());
    }

    @Test
    public void correctlyGetTotalPoints(){
        Round round = new Round();
        round.totalPoints = 72;
        Assert.assertEquals(72, round.getTotalPoints());
        round.totalPoints = 91;
        Assert.assertEquals(91, round.getTotalPoints());
    }

    @Test
    public void correctlyGetCurrentSummedUpTotalPoints(){
        Round round = new Round();
        round.currentSummedUpTotalPoints = 72;
        Assert.assertEquals(72, round.getCurrentSummedUpTotalPoints());
        round.currentSummedUpTotalPoints = 91;
        Assert.assertEquals(91, round.getCurrentSummedUpTotalPoints());
    }

    @Test
    public void correctlyCheckForStrike(){
        Round round = new Round();
        round.checkForStrike(10);
        Assert.assertEquals(true, round.hasStrike);
        round.checkForStrike(2);
        Assert.assertEquals(false, round.hasStrike);
    }

    @Test
    public void correctlyCheckForSpare(){
        Round round = new Round();
        round.checkForSpare(10);
        Assert.assertEquals(true, round.hasSpare);
        round.checkForSpare(2);
        Assert.assertEquals(false, round.hasSpare);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getRandomIntWithWrongOrderedParameters(){
        for(int i = 0; i < rounds.length; i++){
            Round round = new Round();
            rounds[i] = round;
            rounds[i].getRandomInt(10, 0);
        }
    }

    @Test
    public void getRandomIntInCorrectRange(){
        int totalAmountOfPinsAvailable = 10;
        int channelHit = 0;
        for(int i = 0; i < rounds.length; i++) {
            Round round = new Round();
            rounds[i] = round;
            int pinsHit = rounds[i].getRandomInt(channelHit, totalAmountOfPinsAvailable);
            Assert.assertTrue(totalAmountOfPinsAvailable >= pinsHit && pinsHit >= channelHit);
        }
    }

    @Test
    public void getAmountOfPinsKnockedOnFirstTossRangeCheck(){

        int totalAmountOfPinsAvailable = 10;
        int channelHit = 0;
        for(int i = 0; i < rounds.length; i++) {
            Round round = new Round();
            rounds[i] = round;
            rounds[i].doFirstToss();
            Assert.assertTrue(totalAmountOfPinsAvailable >=
                    rounds[i].getAmountPinsHitOnFirstToss() &&
                    rounds[i].getAmountPinsHitOnFirstToss() >= channelHit);
        }
    }

    @Test
    public void getAmountOfPinsKnockedOnSecondTossRangeCheck(){
        int pinsHitOnFirstToss;

        for(int i = 0; i < rounds.length; i++) {
            Round round = new Round();
            rounds[i] = round;
            pinsHitOnFirstToss = rounds[i].getAmountPinsHitOnFirstToss();
            rounds[i].doSecondToss();
            Assert.assertTrue(pinsHitOnFirstToss + rounds[i].getAmountPinsHitOnSecondToss() <= 10);
        }
    }

    @Test
    public void knockedAllPins(){
        for(int i = 0; i < rounds.length; i++) {
            Round round = new Round();
            rounds[i] = round;
            Assert.assertTrue(rounds[i].knockedAllPins(10));
        }
    }

    @Test
    public void detectStrike(){
        for(int i = 0; i < rounds.length; i++) {
            Round round = new Round();
            rounds[i] = round;
            rounds[i].doFirstToss();
            Assert.assertEquals(rounds[i].getAmountPinsHitOnFirstToss() == 10,
                    rounds[i].hasStrike);
        }
    }

    @Test
    public void detectSpare(){
        for(int i = 0; i < rounds.length; i++) {
            Round round = new Round();
            rounds[i] = round;
            rounds[i].doFirstToss();
            rounds[i].doSecondToss();
            Assert.assertEquals(rounds[i].getAmountPinsHitOnFirstToss() +
                    rounds[i].getAmountPinsHitOnSecondToss() == 10, rounds[i].hasSpare);
        }
    }


}
