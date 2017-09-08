package com.bowling.challenge;

import org.junit.Assert;
import org.junit.Test;

public class BowlingGameTest {

    @Test
    public void initBowlingGameTest(){
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.initBowlingGame();
        for(int i = 0; i < bowlingGame.rounds.length; i++){
            Assert.assertEquals(i + 1, bowlingGame.rounds[i].getId());
        }
        Assert.assertEquals(1, bowlingGame.currentRound);
    }

    @Test
    public void checkCalculateSpareBonus(){
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.initBowlingGame();

        // check spare followed by a 3 on first toss in next round
        bowlingGame.rounds[0].setAmountPinsHitOnFirstToss(2);
        bowlingGame.rounds[0].setAmountPinsHitOnSecondToss(8);
        bowlingGame.rounds[1].setAmountPinsHitOnFirstToss(3);
        Assert.assertEquals(13, bowlingGame.calculateSpareBonus(0));

        // check spare followed by a strike in next round
        bowlingGame.rounds[0].setAmountPinsHitOnFirstToss(2);
        bowlingGame.rounds[0].setAmountPinsHitOnSecondToss(8);
        bowlingGame.rounds[1].setAmountPinsHitOnFirstToss(10);
        Assert.assertEquals(20, bowlingGame.calculateSpareBonus(0));
    }

    @Test
    public void checkStrikeFollowedByNeitherStrikeNorSpareBonus() {
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.initBowlingGame();

        // check strike followed by a 3 on first toss and 4 on second toss in next round
        bowlingGame.rounds[0].setAmountPinsHitOnFirstToss(10);
        bowlingGame.rounds[1].setAmountPinsHitOnFirstToss(3);
        bowlingGame.rounds[1].setAmountPinsHitOnSecondToss(4);
        Assert.assertEquals(17, bowlingGame.calculateStrikeBonus(0));
    }
    @Test
    public void checkStrikeFollowedByStrikeFollowedByNeitherStrikeNorSpareBonus() {
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.initBowlingGame();
        // check strike followed by a strike in next round
        // and a 3 on first toss and 4 on second toss in round + 2
        bowlingGame.rounds[0].setAmountPinsHitOnFirstToss(10);
        bowlingGame.rounds[1].setAmountPinsHitOnFirstToss(10);
        bowlingGame.rounds[2].setAmountPinsHitOnFirstToss(3);
        bowlingGame.rounds[2].setAmountPinsHitOnSecondToss(4);
        Assert.assertEquals(23, bowlingGame.calculateStrikeBonus(0));
    }
    @Test
    public void checkStrikeFollowedByTwoStrikeBonus() {
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.initBowlingGame();

        // check strike followed by a strike in next round
        // and a strike in round + 2
        bowlingGame.rounds[0].setAmountPinsHitOnFirstToss(10);
        bowlingGame.rounds[1].setAmountPinsHitOnFirstToss(10);
        bowlingGame.rounds[2].setAmountPinsHitOnFirstToss(3);
        bowlingGame.rounds[2].setAmountPinsHitOnSecondToss(7);
        Assert.assertEquals(23, bowlingGame.calculateStrikeBonus(0));
    }
    @Test
    public void checkStrikeInRound9FollowedByStrikeBonus() {
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.initBowlingGame();

        // check strike followed by a strike in next round
        // if next round is round 10
        bowlingGame.rounds[8].setAmountPinsHitOnFirstToss(10);
        bowlingGame.rounds[9].setAmountPinsHitOnFirstToss(10);
        bowlingGame.rounds[9].setAmountPinsHitOnSecondToss(7);
        bowlingGame.rounds[9].setAmountPinsHitOnBonusToss(1);
        Assert.assertEquals(27, bowlingGame.calculateStrikeBonus(8));
    }

    @Test
    public void checkStrikeInRound9FollowedBySpareBonus() {
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.initBowlingGame();

        // check strike followed by a spare in next round
        // if next round is round 10
        bowlingGame.rounds[8].setAmountPinsHitOnFirstToss(10);
        bowlingGame.rounds[9].setAmountPinsHitOnFirstToss(3);
        bowlingGame.rounds[9].setAmountPinsHitOnSecondToss(7);
        Assert.assertEquals(30, bowlingGame.calculateStrikeBonus(8));
    }

    @Test
    public void checkStrikeInRound9FollowedByNeitherStrikeNorSpareBonus() {
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.initBowlingGame();

        // check strike followed by a 3 and a 4 in next round
        // if next round is round 10
        bowlingGame.rounds[8].setAmountPinsHitOnFirstToss(10);
        bowlingGame.rounds[9].setAmountPinsHitOnFirstToss(3);
        bowlingGame.rounds[9].setAmountPinsHitOnSecondToss(4);
        Assert.assertEquals(17, bowlingGame.calculateStrikeBonus(8));
    }
}
