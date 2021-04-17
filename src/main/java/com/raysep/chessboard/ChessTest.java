package com.raysep.chessboard;

import com.raysep.chessboard.calculators.BishopShortestPathCalculator;
import com.raysep.chessboard.calculators.ChessShortestPathCalculator;
import com.raysep.chessboard.calculators.KnightShortestPathCalculator;
import com.raysep.chessboard.calculators.RockShortestPathCalculator;
import com.raysep.chessboard.piece.Knight;
import com.raysep.chessboard.piece.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static javax.swing.JOptionPane.showMessageDialog;


public class ChessTest extends JFrame implements MouseListener {

    //Panel to hold chess cells
    private JPanel[][] pnlChessCells = new JPanel[8][8];

    //Main panel
    final JPanel pnlMain = new JPanel(new GridLayout(8, 8));

    //Action panel
    final JPanel pnlActions = new JPanel(new GridLayout(1, 3));

    final JButton btnReset = new JButton("Reset");
    final JButton btnCalculate = new JButton("Calculate");

    final JRadioButton radKnight = new JRadioButton("Knight", true);
    final JRadioButton radBishop = new JRadioButton("Bishop");
    final JRadioButton radRock = new JRadioButton("Rock");

    //Start & End position
    private Point pntStart, pntEnd;

    public static Piece SelectedPiece = new Knight();

    public ChessShortestPathCalculator chessShortestPathCalc;

    public static void main(String[] args) {
        final ChessTest app = new ChessTest();
    }


    public ChessTest() {
        createForm();
    }

    //setting up the UI of the form
    private void createForm() {
        try {
            Container container = getContentPane();
            setBounds(100, 100, 605, 705);
            setBackground(new Color(204, 204, 204));
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setTitle("Chess Knight Move");
            setResizable(false);
            container.setLayout(null);


            btnCalculate.setBounds(0, 0, 20, 20);
            btnCalculate.addActionListener(e -> {
                try {
                    calculate();
                } catch (Exception exception) {
                    showMessageDialog(null, exception.getMessage(), "Error occurred", JOptionPane.ERROR_MESSAGE);
                }
            });
            btnCalculate.setEnabled(false);
            pnlActions.add(btnCalculate);


            pnlActions.setBounds(2, 603, 603, 70);
            btnReset.setBounds(0, 0, 20, 20);
            btnReset.addActionListener(e -> reset());
            pnlActions.add(btnReset);

            createRadioButtons();

            pnlMain.setBounds(3, 3, 600, 600);
            pnlMain.setBorder(BorderFactory.createTitledBorder("Please select a starting and an ending position (By clicking on two chess cells)"));
            container.add(pnlMain);

            pnlActions.setBorder(BorderFactory.createTitledBorder("Actions"));
            container.add(pnlActions);

            drawChessBoard();

            setVisible(true);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            showMessageDialog(null, exception.getMessage(), "Error occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createRadioButtons() {
        radBishop.setToolTipText("Created just for showing program extensibility");
        radRock.setToolTipText("Created just for showing program extensibility");

        radKnight.addItemListener(e -> SelectedPiece = new Knight());
        radBishop.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                showMessageDialog(null, "Not Implemented (Created just for showing program extensibility)", "Error occurred", JOptionPane.ERROR_MESSAGE);
                radKnight.setSelected(true);
            }

        });
        radRock.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                showMessageDialog(null, "Not Implemented (Created just for showing program extensibility)", "Error occurred", JOptionPane.ERROR_MESSAGE);
                radKnight.setSelected(true);
            }

        });

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();

        group.add(radKnight);
        group.add(radBishop);
        group.add(radRock);

        pnlActions.add(radKnight);
        pnlActions.add(radBishop);
        pnlActions.add(radRock);
    }

    private void calculate() throws Exception {
        if (radKnight.isSelected())
            chessShortestPathCalc = new KnightShortestPathCalculator(false);
        else if (radBishop.isSelected())
            chessShortestPathCalc = new BishopShortestPathCalculator();
        else
            chessShortestPathCalc = new RockShortestPathCalculator();

        List<List<ChessCell>> moves = chessShortestPathCalc.calculate(new ChessCell(((int) pntStart.getX() + 1), ((int) pntStart.getY() + 1)),
                new ChessCell(((int) pntEnd.getX() + 1), ((int) pntEnd.getY() + 1)));
        if (moves.isEmpty()) {
            System.out.println("There is no solution.");
            showMessageDialog(null, "There is no solution", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String pathString = concatShortestMoves(moves);
            System.out.println("Path: " + pathString);
            showMessageDialog(null, "Path: " + pathString, "Solution Found!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String concatShortestMoves(List<List<ChessCell>> moves) throws Exception {
        List<ChessCell> shortestMove = moves.stream().min(Comparator.comparingInt(List::size))
                .orElse(Collections.emptyList());
        drawPath(shortestMove);
        return shortestMove.stream().map(ChessCell::toString).collect(Collectors.joining(" -> "));
    }

    //Draw path
    private void drawPath(List<ChessCell> shortestMove) throws Exception {
        for (ChessCell chessCell : shortestMove) {
            this.pnlChessCells[chessCell.getRow() - 1][chessCell.getColumn() - 1].add(new JLabel(SelectedPiece.getImageIcon(true)), BorderLayout.CENTER);
            this.pnlChessCells[chessCell.getRow() - 1][chessCell.getColumn() - 1].validate();
        }
    }

    private void reset() {
        try {
            pntStart = pntEnd = null;
            btnCalculate.setEnabled(false);
            pnlChessCells = new JPanel[8][8];
            pnlMain.removeAll();
            pnlMain.updateUI();
            drawChessBoard();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    // Draw the board
    private void drawChessBoard() {
        try {
            for (int y = 0; y < 8; y++)
                for (int x = 0; x < 8; x++) {
                    JPanel jPanel = new JPanel(new BorderLayout());
                    jPanel.add(new JLabel(ChessCell.columnLetters.get(x + 1) + "" + (y + 1)));//Create label of chess cells
                    pnlChessCells[y][x] = jPanel;
                    pnlChessCells[y][x].addMouseListener(this);
                    pnlMain.add(pnlChessCells[y][x]);
                    //Chess cell coloring (Black and white)
                    if (y % 2 == 0)
                        if (x % 2 != 0)
                            pnlChessCells[y][x].setBackground(Color.DARK_GRAY);
                        else
                            pnlChessCells[y][x].setBackground(Color.WHITE);
                    else if (x % 2 == 0)
                        pnlChessCells[y][x].setBackground(Color.DARK_GRAY);
                    else
                        pnlChessCells[y][x].setBackground(Color.WHITE);
                }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            showMessageDialog(null, exception.getMessage(), "Error occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    // This method captures the mouse move on the chess board
    public void mouseReleased(MouseEvent e) {
        try {
            if (pntStart != null && pntEnd != null)
                return;

            Object source = e.getSource();
            JPanel pnlTemp = (JPanel) source;
            int intX = (pnlTemp.getX() / 74);
            int intY = (pnlTemp.getY() / 74);

            if (pntStart == null)
                pntStart = new Point(intX, intY);
            else if (pntEnd == null) {
                pntEnd = new Point(intX, intY);
            }

            if (pntEnd != null)
                btnCalculate.setEnabled(true);

            this.pnlChessCells[intY][intX].add(new JLabel(SelectedPiece.getImageIcon(false)), BorderLayout.CENTER);
            this.pnlChessCells[intY][intX].validate();
        } catch (Exception exception) {
            pntStart = pntEnd = null;
            System.out.println(exception.getMessage());
            showMessageDialog(null, exception.getMessage(), "Error occurred", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }
}
