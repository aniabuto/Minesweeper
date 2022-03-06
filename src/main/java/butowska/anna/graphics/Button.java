package butowska.anna.graphics;

import butowska.anna.entities.Tile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@AllArgsConstructor
@Getter
@Setter
public class Button {

    private Tile tile;
    private int row;
    private int col;
    private int buttonSize;

    public JButton getJButton(){
        JButton jButton = new JButton();
        if(!tile.isDiscovered()){
            if(tile.isFlagged())
                jButton = new JButton(chooseIcon("flag.png"));
            else
                jButton.setIcon(chooseIcon("greenTile.png"));
        } else{
            if(tile.isABomb())
                jButton = new JButton(chooseIcon("mine.png"));
            else{
                if(tile.getNumberOfBombsInNeighbourhood() == 0)
                    jButton = new JButton(chooseIcon("blankTile.png"));
                if(tile.getNumberOfBombsInNeighbourhood() > 0)
                    jButton = new JButton(chooseIcon(Integer.toString(tile.getNumberOfBombsInNeighbourhood()) + ".png"));
            }
        }
        jButton.setPreferredSize(new Dimension(buttonSize, buttonSize));
        return jButton;
    }

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
        if(!tile.isDiscovered()){
            if(tile.isFlagged())
                return chooseIcon("flag.png");
            else
                return chooseIcon("greenTile.png");
        } else{
            if(tile.isABomb())
                return chooseIcon("mine.png");
            else{
                if(tile.getNumberOfBombsInNeighbourhood() == 0)
                    return chooseIcon("blankTile.png");
            }
            return chooseIcon(Integer.toString(tile.getNumberOfBombsInNeighbourhood()) + ".png");
        }
    }

}
