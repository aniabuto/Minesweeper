package butowska.anna.entities;

import butowska.anna.graphics.GUI;

import java.util.Locale;
import java.util.Scanner;

public class Game {

    private Board board;

    public static void main(String[] args) {
        Level level = Level.builder()
                    .name("Beginner")
                    .height(9)
                    .width(9)
                    .numberOfBombs(10)
                    .build();

        Board board = Board.builder()
                .chosenLevel(level)
                .build();
        board.Start();
        GUI gui = new GUI();
        gui.CreateWindow(board, 30, true);


    }

}
