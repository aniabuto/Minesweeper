package butowska.anna.graphics;

import butowska.anna.entities.Board;

import javax.swing.*;
import java.awt.*;

public class GUI {

    public static JFrame CreateWindow(Board board, int buttonSize){
        JFrame frame = new JFrame("Minesweeper");
        int rows = board.getChosenLevel().getHeight();
        int cols = board.getChosenLevel().getWidth();
        TileGrid tiles = new TileGrid(board, buttonSize);

        JPanel bottomPanel = new JPanel();
        Reset reset = new Reset(board, tiles);
        reset.addMouseListener(reset);
        //JButton resetButton = new JButton(reset.getIcon());
        reset.setPreferredSize(new Dimension(50, 50));
        JLabel bombsLeft = new JLabel("\tBombs left: " + Integer.toString(board.getNumberOfBombsLeft()));
        bottomPanel.setLayout(new GridLayout(1, 2));
        bombsLeft.setMinimumSize(new Dimension(100, 100));
        tiles.bombsLeft = bombsLeft;
        tiles.resetButton = reset;
        bottomPanel.add(bombsLeft);
        bottomPanel.add(reset);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(buttonSize * rows, buttonSize * cols + 100));
        frame.setLocationRelativeTo(null);
        frame.add(tiles, BorderLayout.NORTH);
        frame.add(bottomPanel);
        //frame.add(reset);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        return frame;
    }

}
