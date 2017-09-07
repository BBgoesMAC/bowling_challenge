package com.bowling.challenge;


import org.junit.Assert;
import org.junit.Test;

public class BowlingChallengeTest {

    Round[] rounds = new Round[9];
    BowlingChallenge bowlingGame = new BowlingChallenge();

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
