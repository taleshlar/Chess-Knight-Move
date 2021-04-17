package com.raysep.chessboard.calculators;

import com.raysep.chessboard.ChessCell;
import com.raysep.chessboard.ChessTest;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.*;


@RequiredArgsConstructor
public class KnightShortestPathCalculator implements ChessShortestPathCalculator {

    private List<List<ChessCell>> moves;

    private Queue<BfsNode> queue = new LinkedList<>();

    private final boolean containCycle;
    private final int DEPTH_LIMIT = 3; //Maximum allowable movement of the knight

    @Value
    class BfsNode {
        ChessCell chessCell;
        List<ChessCell> path;
    }

    @Override
    public List<List<ChessCell>> calculate(ChessCell startingChessCell, ChessCell endingChessCell) {
        try {
            moves = new ArrayList<>();
            queue.add(new BfsNode(startingChessCell, Arrays.asList(startingChessCell)));
            while (!queue.isEmpty()) {
                BfsNode current = queue.poll();

                if (current.getChessCell().equals(endingChessCell)) {
                    moves.add(current.getPath());
                }

                current.getChessCell().getAvailableMoves(ChessTest.SelectedPiece).forEach(chessCell -> {
                    if (current.getPath().size() <= DEPTH_LIMIT) {
                        List<ChessCell> path = new ArrayList<>(current.getPath());
                        boolean validPath = true;

                        if (containCycle) {
                            if (path.contains(chessCell)) {
                                validPath = false;
                            }
                        }

                        if (validPath) {
                            path.add(chessCell);
                            queue.add(new BfsNode(chessCell, path));
                        }
                    }
                });
            }
            return moves;
        } catch (Exception exception) {
            throw exception;
        }
    }
}
