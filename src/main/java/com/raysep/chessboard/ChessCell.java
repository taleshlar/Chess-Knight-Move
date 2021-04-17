package com.raysep.chessboard;

import com.raysep.chessboard.piece.Piece;
import lombok.Value;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;


@Value
public class ChessCell {
    public static final Map<Integer, String> columnLetters = Map.ofEntries(entry(1, "A"),
            entry(2, "B"),
            entry(3, "C"),
            entry(4, "D"),
            entry(5, "E"),
            entry(6, "F"),
            entry(7, "G"),
            entry(8, "H"));

    int column;
    int row;

    public ChessCell(String column, int row) {
        this(convertColumnLetterToNumber(column), row);
    }

    public ChessCell(int column, int row) {
        if (coordinatesAreValid(column, row)) {
            this.column = column;
            this.row = row;
        } else {
            throw new IllegalArgumentException("ChessCell coordinates out of bounds");
        }
    }

    private boolean coordinatesAreValid(int column, int row) {
        return column <= 8 && column >= 1
                && row <= 8 && row >= 1;
    }

    public List<ChessCell> getAvailableMoves(Piece piece) {
        return piece.getAvailableMoves(this.column, this.row);
    }

    @Override
    public String toString() {
        return convertColumnNumberToLetter(column) + row;
    }

    private static String convertColumnNumberToLetter(int columnNumber) {
        if (columnLetters.containsKey(columnNumber)) {
            return columnLetters.get(columnNumber);
        } else {
            return String.valueOf(columnNumber);
        }
    }

    private static int convertColumnLetterToNumber(String columnLetter) {
        if (columnLetters.containsValue(columnLetter)) {
            return columnLetters.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(columnLetter))
                    .map(Map.Entry::getKey).findFirst().get();
        } else {
            throw new IllegalArgumentException("No number found for column " + columnLetter);
        }
    }
}
