package butowska.anna.graphics;

import butowska.anna.entities.Board;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
public class GUI {

    private JFrame frame;

    public JFrame CreateWindow(Board board, int buttonSize, boolean first){
        if(!first)
            frame.dispose();
        frame = new JFrame("Minesweeper");
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
        bottomPanel.setPreferredSize(new Dimension(buttonSize * rows, 50));

        JMenu level;
        JMenuItem lvl1, lvl2, lvl3;
        JMenuBar menuBar = new JMenuBar();
        level = new JMenu("Choose Level");
        lvl1 = new JMenuItem("Beginner");
        lvl2 = new JMenuItem("Intermediate");
        lvl3 = new JMenuItem("Expert");
        //System.out.println(lvl1.getText());
        lvl1.addActionListener(new ChangeLevel(lvl1, tiles, this));
        lvl2.addActionListener(new ChangeLevel(lvl2, tiles, this));
        lvl3.addActionListener(new ChangeLevel(lvl3, tiles, this));
        level.add(lvl1);
        level.add(lvl2);
        level.add(lvl3);
        //ChangeLevel levelListen = new ChangeLevel(level);
        //level.addMenuListener(levelListen);

        menuBar.add(level);
        menuBar.setPreferredSize(new Dimension(buttonSize * rows, 25));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(buttonSize * rows, buttonSize * cols + 125));
        frame.add(menuBar, BorderLayout.NORTH);
        frame.add(tiles, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        //frame.add(reset);


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        return frame;
    }

}
