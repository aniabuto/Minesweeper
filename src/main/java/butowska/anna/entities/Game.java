package butowska.anna.entities;

import butowska.anna.graphics.GUI;

import java.util.Locale;
import java.util.Scanner;

public class Game {

    private Board board;

    public static void main(String[] args) {
        Level level;
        System.out.println("Choose difficulty level:");
        System.out.println("\t1 - Beginner");
        System.out.println("\t2 - Intermediate");
        System.out.println("\t3 - Expert");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if(choice.equals("1"))
            level = Level.builder()
                    .name("Beginner")
                    .height(9)
                    .width(9)
                    .numberOfBombs(10)
                    .build();
        else if(choice.equals("2"))
            level = Level.builder()
                    .name("Intermediate")
                    .height(16)
                    .width(16)
                    .numberOfBombs(40)
                    .build();
        else if(choice.equals("3"))
            level = Level.builder()
                    .name("Expert")
                    .height(30)
                    .width(16)
                    .numberOfBombs(99)
                    .build();
        else
            level = Level.builder()
                    .name("null")
                    .build();

        if (!level.getName().equals("null")){
            System.out.println(level.getName());
            Board board = Board.builder()
                    .chosenLevel(level)
                    .build();
            board.Start();
            GUI gui = new GUI();
            gui.CreateWindow(board, 30);
            while(!board.isDead()){
                ;
            }
            System.out.println("Game over!");
        }

    }

}
