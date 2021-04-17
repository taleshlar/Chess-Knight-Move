package com.raysep.chessboard.calculators;

import com.raysep.chessboard.ChessCell;

import java.util.List;


public interface ChessShortestPathCalculator {
    List<List<ChessCell>> calculate(ChessCell startingChessCell, ChessCell endingChessCell) throws Exception;
}
