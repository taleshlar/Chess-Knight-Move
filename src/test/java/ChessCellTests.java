import com.raysep.chessboard.ChessCell;
import com.raysep.chessboard.piece.Knight;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessCellTests {
    @Test
    public void testValidCreation() {
        int row = 4;
        int column = 5;
        ChessCell validChessCell = new ChessCell(column, row);
        assertThat(validChessCell.getColumn()).isEqualTo(column);
        assertThat(validChessCell.getRow()).isEqualTo(row);
    }

    @Test
    public void testValidCreationWithLetter() {
        int row = 3;
        String column = "D";
        ChessCell validChessCell = new ChessCell(column, row);
        assertThat(validChessCell.getColumn()).isEqualTo(4);
        assertThat(validChessCell.getRow()).isEqualTo(3);
    }


    @Test
    public void testAvailableMoves_allAvailableMoves() {
        ChessCell chessCell = new ChessCell(4, 4);
        List<ChessCell> possibleMoves = chessCell.getAvailableMoves(new Knight());
        assertThat(possibleMoves).containsExactly(new ChessCell(3, 6),
                new ChessCell(5, 6),
                new ChessCell(2, 5),
                new ChessCell(2, 3),
                new ChessCell(6, 5),
                new ChessCell(6, 3),
                new ChessCell(3, 2),
                new ChessCell(5, 2));
    }

    @Test
    public void testAvailableMoves_chessCellInCorner() {
        ChessCell cellInCorner = new ChessCell(1, 1);
        List<ChessCell> availableMoves = cellInCorner.getAvailableMoves(new Knight());
        assertThat(availableMoves).containsExactly(new ChessCell(2, 3),
                new ChessCell(3, 2));
    }

    @Test
    public void testAvailableMoves_chessCellAtEdge() {
        ChessCell cellInCorner = new ChessCell(3, 1);
        List<ChessCell> availableMoves = cellInCorner.getAvailableMoves(new Knight());
        assertThat(availableMoves).containsExactly(new ChessCell(2, 3),
                new ChessCell(4, 3),
                new ChessCell(1, 2),
                new ChessCell(5, 2));
    }
}
