package edu.eci.dosw.bowling;

import java.util.List;

public class BowlingScorer {

    private static final int TOTAL_FRAMES = 10;

    public int calculate(List<Frame> frames) {
        if (frames.size() < TOTAL_FRAMES) {
            throw new IllegalStateException("Cannot score an incomplete game");
        }

        int score = 0;

        for (int i = 0; i < TOTAL_FRAMES; i++) {
            Frame frame = frames.get(i);
            score += frame.getPinsKnockedDown();

            // El frame 10 (índice 9) aloja sus propios tiros extra en su conteo base.
            // Solo aplicamos la lógica de bonos de siguientes frames a los primeros 9.
            if (i < TOTAL_FRAMES - 1) {
                if (frame.isStrike()) {
                    score += strikeBonus(i, frames);
                } else if (frame.isSpare()) {
                    score += spareBonus(i, frames);
                }
            }
        }
        return score;
    }

    private int spareBonus(int frameIndex, List<Frame> frames) {
        return frames.get(frameIndex + 1).getFirstRoll();
    }

    private int strikeBonus(int frameIndex, List<Frame> frames) {
        Frame nextFrame = frames.get(frameIndex + 1);

        // Si el siguiente también es strike y NO es el frame 10 (donde están todos los tiros finales),
        // tomamos el primer tiro del frame subsiguiente.
        if (nextFrame.isStrike() && (frameIndex + 1) < TOTAL_FRAMES - 1) {
            return nextFrame.getFirstRoll() + frames.get(frameIndex + 2).getFirstRoll();
        }

        // Si no es strike consecutivo o si el siguiente ES el frame 10,
        // simplemente tomamos los dos primeros tiros de ese siguiente frame.
        return nextFrame.getFirstRoll() + nextFrame.getSecondRoll();
    }
}