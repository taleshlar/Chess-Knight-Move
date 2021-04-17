package com.raysep.chessboard.piece;

import com.raysep.chessboard.ChessCell;
import com.raysep.chessboard.utils.ChessPieceImageUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;


public class Knight implements Piece {
    @Override
    public List<ChessCell> getAvailableMoves(int column, int row) {
        List<Integer> newColumns = new ArrayList<>();
        List<Integer> newRows = new ArrayList<>();

        //up - left
        newColumns.add(column - 1);
        newRows.add(row + 2);

        //up - right
        newColumns.add(column + 1);
        newRows.add(row + 2);

        // left - up
        newColumns.add(column - 2);
        newRows.add(row + 1);

        //left - down
        newColumns.add(column - 2);
        newRows.add(row - 1);

        //right - up
        newColumns.add(column + 2);
        newRows.add(row + 1);

        //right - down
        newColumns.add(column + 2);
        newRows.add(row - 1);

        //down - left
        newColumns.add(column - 1);
        newRows.add(row - 2);

        //down - right
        newColumns.add(column + 1);
        newRows.add(row - 2);

        List<ChessCell> availableMoves = new ArrayList<>();
        IntStream.range(0, newColumns.size()).forEach(i -> {
            try {
                ChessCell newMove = new ChessCell(newColumns.get(i), newRows.get(i));
                availableMoves.add(newMove);
            } catch (IllegalArgumentException e) {
            }
        });
        return Collections.unmodifiableList(availableMoves);
    }

    @Override
    public ImageIcon getImageIcon(Boolean isAlternative) throws Exception {
        return isAlternative ? ChessPieceImageUtil.getImageIcon(ChessPieceImageUtil.ChessPieceIcon.KNIGHTBLACKALT) :
                ChessPieceImageUtil.getImageIcon(ChessPieceImageUtil.ChessPieceIcon.KNIGHTBLACK);
    }
}
