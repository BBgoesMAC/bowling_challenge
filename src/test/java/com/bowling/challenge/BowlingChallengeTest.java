package com.bowling.challenge;


import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class BowlingChallengeTest {

    @Test (expected = IllegalArgumentException.class)
    public final void getRandomIntWithWrongOrderedParameters(){
        BowlingChallenge.getRandomInt(10, 0);
    }

    @Test
    public final void getRandomIntInCorrectRange(){
        int totalAmountOfPinsAvailable = 10;
        int channelHit = 0;
        int pinsHit = BowlingChallenge.getRandomInt(channelHit, totalAmountOfPinsAvailable);
        Assert.assertTrue( totalAmountOfPinsAvailable >= pinsHit && pinsHit >= channelHit );
    }

    @Test
    public final void getAmountOfPinsKnockedOnFirstTossRangeCheck(){
        BowlingChallenge.doFirstToss(0);
        int totalAmountOfPinsAvailable = 10;
        int channelHit = 0;
        Assert.assertTrue( totalAmountOfPinsAvailable >=
                BowlingChallenge.rounds[0].getAmountPinsHitOnFirstToss() &&
                BowlingChallenge.rounds[0].getAmountPinsHitOnFirstToss() >= channelHit );
    }

    @Test
    public final void getAmountOfPinsKnockedOnSecondTossRangeCheck(){
        int pinsHitOnFirstToss = BowlingChallenge.rounds[0].getAmountPinsHitOnFirstToss();
        BowlingChallenge.doSecondToss(0);
        Assert.assertTrue(pinsHitOnFirstToss + BowlingChallenge.rounds[0].getAmountPinsHitOnSecondToss() <= 10);
    }

    @Test
    public final void knockedALlPins(){
        Assert.assertTrue(BowlingChallenge.knockedAllPins(10));
    }

    @Test
    public final void detectStrike(){
        BowlingChallenge.doFirstToss(0);
        Assert.assertTrue(BowlingChallenge.rounds[0].getAmountPinsHitOnFirstToss() == 10 &&
                BowlingChallenge.rounds[0].hasStrike);
    }

    @Test
    public final void detectSpare(){
        BowlingChallenge.doFirstToss(0);
        BowlingChallenge.doSecondToss(0);
        Assert.assertTrue(BowlingChallenge.rounds[0].getAmountPinsHitOnFirstToss() +
                BowlingChallenge.rounds[0].getAmountPinsHitOnSecondToss() == 10 &&
                BowlingChallenge.rounds[0].hasSpare);
    }


}
