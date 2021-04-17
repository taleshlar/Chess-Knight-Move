import com.raysep.chessboard.ChessCell;
import com.raysep.chessboard.calculators.ChessShortestPathCalculator;
import com.raysep.chessboard.calculators.KnightShortestPathCalculator;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightShortestPathCalculatorTests {

    public ChessShortestPathCalculator notContainCycleChessShortestPathCalculator =
            new KnightShortestPathCalculator(false);

    @SneakyThrows
    @Test
    public void testKnightShortestPathCalculator_noSolution() {
        List<List<ChessCell>> moves =
                notContainCycleChessShortestPathCalculator.calculate(new ChessCell(1, 1),
                        new ChessCell(8, 8));
        assertThat(moves).isEmpty();
    }

    @SneakyThrows
    @Test
    public void testKnightShortestPathCalculator_oneSolutionWithThreeSteps() {
        ChessCell startingChessCell = new ChessCell(2, 2);
        ChessCell endingChessCell = new ChessCell(5, 8);
        List<List<ChessCell>> moves = notContainCycleChessShortestPathCalculator.calculate(startingChessCell, endingChessCell);
        assertThat(moves).containsExactly(List.of(startingChessCell,
                new ChessCell(3, 4),
                new ChessCell(4, 6),
                endingChessCell));

    }

    @SneakyThrows
    @Test
    public void testKnightShortestPathCalculator_twoSolutionsWithTwoSteps() {
        ChessCell startingChessCell = new ChessCell(2, 2);
        ChessCell endingChessCell = new ChessCell(5, 3);
        List<List<ChessCell>> moves = notContainCycleChessShortestPathCalculator.calculate(startingChessCell, endingChessCell);
        assertThat(moves).containsExactly(List.of(startingChessCell,
                new ChessCell(3, 4),
                endingChessCell),
                List.of(startingChessCell,
                        new ChessCell(4, 1),
                        endingChessCell));
    }

}
