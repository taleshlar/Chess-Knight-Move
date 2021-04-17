package com.raysep.chessboard.piece;

import com.raysep.chessboard.ChessCell;
import lombok.SneakyThrows;

import javax.swing.*;
import java.util.List;


public class Bishop implements Piece {
    @SneakyThrows
    @Override
    public List<ChessCell> getAvailableMoves(int column, int row) {
        throw new Exception("Not Implemented!");
    }

    @Override
    public ImageIcon getImageIcon(Boolean isALT) throws Exception {
        throw new Exception("Not Implemented!");
    }
}
