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

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(buttonSize * rows, buttonSize * cols + 100));
        frame.setLocationRelativeTo(null);
        frame.add(tiles, BorderLayout.NORTH);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        return frame;
    }

}
