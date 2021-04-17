package com.raysep.chessboard.piece;

import com.raysep.chessboard.ChessCell;

import javax.swing.*;
import java.util.List;


public interface Piece {
    List<ChessCell> getAvailableMoves(int column, int row);

    ImageIcon getImageIcon(Boolean isAlternative) throws Exception;
}
