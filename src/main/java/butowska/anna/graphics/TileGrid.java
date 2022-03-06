package butowska.anna.graphics;

import butowska.anna.entities.Board;
import butowska.anna.entities.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TileGrid extends JPanel implements ActionListener, MouseListener
{

    static JPanel jPanel = new JPanel();
    Button[][] buttons;
    JButton[][] jButtons;
    JLabel bombsLeft;
    Reset resetButton;
    Board board;
    int width;
    int height;
    int tileSize;
    boolean ppm = false;

    public TileGrid(Board board, int buttonSize) {
        tileSize = buttonSize;
        this.board = board;
        this.width = this.board.getChosenLevel().getWidth();
        this.height = this.board.getChosenLevel().getHeight();
        setLayout(new GridLayout(this.width, this.height));
        buttons = new Button[this.width][this.height];
        jButtons = new JButton[this.width][this.height];

        for (int i = 0; i < this.width; i++)
            for (int j = 0; j < this.height; j++) {
                buttons[i][j] = new Button(board.getTile(i, j), i, j, tileSize);
                JButton jButton = buttons[i][j].getJButton();
                jButton.setActionCommand(i + "," + j);
                jButtons[i][j] = jButton;
                jButtons[i][j].addActionListener(this);
                jButtons[i][j].addMouseListener(this);
                add(jButtons[i][j]);
            }

        setVisible(true);
        setSize(new Dimension(tileSize * width, tileSize * height));

    }

    public void ResetGame(Board newBoard){
        this.board = newBoard;
//        this.width = this.board.getChosenLevel().getWidth();
//        this.height = this.board.getChosenLevel().getHeight();
//        setLayout(new GridLayout(this.width, this.height));
//        buttons = new Button[this.width][this.height];
//        jButtons = new JButton[this.width][this.height];
//
//        for (int i = 0; i < this.width; i++)
//            for (int j = 0; j < this.height; j++) {
//                buttons[i][j] = new Button(board.getTile(i, j), i, j, tileSize);
//                JButton jButton = buttons[i][j].getJButton();
//                jButton.setActionCommand(i + "," + j);
//                jButtons[i][j] = jButton;
//                jButtons[i][j].addActionListener(this);
//                jButtons[i][j].addMouseListener(this);
//                add(jButtons[i][j]);
//            }
//
//        setVisible(true);
//        setSize(new Dimension(tileSize * width, tileSize * height));
    }

    public void Update(){
        for(int i = 0; i < this.width; i++)
            for(int j = 0; j < this.height; j++){
                buttons[i][j].setTile(board.getTile(i, j));
                jButtons[i][j].setIcon(buttons[i][j].getIcon());
            }
        bombsLeft.setText("\tBombs left: " + Integer.toString(board.getNumberOfBombsLeft()));
        resetButton.setIcon(resetButton.getIcon());
    }

    public void LeftClick(int row, int col){
        if(board.MakeMove("d " + row + " " + col))
            Update();
    }

    public void RightClick(int row, int col){
        board.MakeMove("f " + row + " " + col);
    }

    public static JPanel getjPanel(){
        return jPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        System.out.println(action);
        int row = Integer.parseInt(action.split(",")[0]);
        int col = Integer.parseInt(action.split(",")[1]);
        if (ppm){
            System.out.println("PPM");
            this.RightClick(row, col);
        }
        else
            this.LeftClick(row, col);
        //if(!board.isDead())
            Update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3){
            System.out.println("PPM");
            //System.out.println(e.getPoint());
            ppm = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ppm = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("entered!");
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
