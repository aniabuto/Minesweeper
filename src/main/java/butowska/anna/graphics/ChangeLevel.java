package butowska.anna.graphics;

import butowska.anna.entities.Board;
import butowska.anna.entities.Level;
import lombok.AllArgsConstructor;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@AllArgsConstructor
public class ChangeLevel implements ActionListener {

    JMenuItem level;
    TileGrid tileGrid;
    GUI oldGUI;


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(level.getText());
        Level newLevel;
        if(level.getText().equals("Beginner"))
            newLevel = Level.builder()
                    .name("Beginner")
                    .height(9)
                    .width(9)
                    .numberOfBombs(10)
                    .build();
        else if(level.getText().equals("Intermediate"))
            newLevel = Level.builder()
                    .name("Intermediate")
                    .height(16)
                    .width(16)
                    .numberOfBombs(40)
                    .build();
        else //if(level.getText().equals("Expert"))
            newLevel = Level.builder()
                    .name("Expert")
                    .height(30)
                    .width(16)
                    .numberOfBombs(99)
                    .build();
        Board board = Board.builder()
                .chosenLevel(newLevel).
                build();
        board.Start();
        oldGUI.CreateWindow(board, 30, false);

    }
}
