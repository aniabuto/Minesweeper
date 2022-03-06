package butowska.anna.graphics;

import butowska.anna.entities.Board;
import butowska.anna.entities.Level;
import lombok.AllArgsConstructor;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@AllArgsConstructor
@Setter
public class Reset extends JButton implements MouseListener {

    Board board;
    TileGrid tileGrid;

    private ImageIcon chooseIcon(String iconName) {
        BufferedImage BI = null;
        try {
            String fileName = "D:\\my_projects\\minesweeper\\src\\main\\java\\butowska\\anna\\graphics\\img\\" + iconName;
            //System.out.println(fileName);
            BI = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println("Error: file not found");
        }
        return new ImageIcon(BI);
    }

    public ImageIcon getIcon() {
        if(board.isHasWon())
            return chooseIcon("smug.png");
        if(board.isDead())
            return chooseIcon("dead.png");
        return chooseIcon("smile.png");
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        System.out.println("clicked!");
//        Level newLevel = board.getChosenLevel();
//        board = Board.builder()
//                .chosenLevel(newLevel)
//                .build();
//        board.Start();
//    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() != MouseEvent.BUTTON1)
            return;
        System.out.println("clicked!");
        Level newLevel = board.getChosenLevel();
        board = Board.builder()
                .chosenLevel(newLevel)
                .build();
        board.Start();
        tileGrid.ResetGame(board);
        tileGrid.Update();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
