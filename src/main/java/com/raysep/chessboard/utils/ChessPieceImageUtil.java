package com.raysep.chessboard.utils;

import javax.swing.*;
import java.util.Objects;


public class ChessPieceImageUtil {

    public enum ChessPieceIcon {
        KNIGHTBLACK,
        KNIGHTBLACKALT
    }

    public static ImageIcon getImageIcon(ChessPieceIcon chessPieceIcon) throws Exception {
        try {
            switch (chessPieceIcon) {
                case KNIGHTBLACK:
                    return new ImageIcon(Objects.requireNonNull(ChessPieceImageUtil.class.getClassLoader().getResource("KnightBlack.png")));
                case KNIGHTBLACKALT:
                    return new ImageIcon(Objects.requireNonNull(ChessPieceImageUtil.class.getClassLoader().getResource("KnightBlack-ALT.png")));
                default:
                    throw new Exception("Image is not set");
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new Exception("Image is not found");
        }
    }
}
