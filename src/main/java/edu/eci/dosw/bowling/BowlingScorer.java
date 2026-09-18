package edu.eci.dosw.bowling;

import java.util.List;

public class BowlingScorer {

    public int calculate(java.util.List<Frame> frames) {
        int score = 0;
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            score += frame.getPinsKnockedDown();

            // Sumar el bono si es un SPARE y no es el último frame
            if (frame.getType() == FrameType.SPARE && i + 1 < frames.size()) {
                score += frames.get(i + 1).getFirstRoll();
            }
        }
        return score;
    }
}