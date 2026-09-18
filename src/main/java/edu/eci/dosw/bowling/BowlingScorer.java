package edu.eci.dosw.bowling;

import java.util.List;

public class BowlingScorer {

    public int calculate(List<Frame> frames) {
        int score = 0;
        for (Frame frame : frames) {
            score += frame.getPinsKnockedDown();
        }
        return score;
    }
}