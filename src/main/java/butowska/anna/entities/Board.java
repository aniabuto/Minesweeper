package butowska.anna.entities;

import lombok.*;

import java.util.Random;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class Board {

    private Tile[][] board;
    private Level chosenLevel;
    private boolean isDead = false;
    private boolean hasWon = false;
    private int numberOfBombsLeft;

    public void Start(){
        numberOfBombsLeft = chosenLevel.getNumberOfBombs();
        board = new Tile[chosenLevel.getHeight()][chosenLevel.getWidth()];
        int t=0;

        for(int row = 0; row < chosenLevel.getHeight(); row++)
            for(int col = 0; col < chosenLevel.getWidth(); col++){
                board[row][col] = new Tile();
            }
        InitBombs();
    }

    private void InitBombs(){
        Random rand = new Random();
        int bombsToPlace = numberOfBombsLeft;
        while(bombsToPlace > 0){
            for(int row = 0; row < chosenLevel.getHeight(); row++)
                for(int col = 0; col < chosenLevel.getWidth(); col++) {
                    int int_random = rand.nextInt(10 * numberOfBombsLeft);
                    if(int_random < numberOfBombsLeft && bombsToPlace > 0) {
                        board[row][col].setABomb(true);
                        bombsToPlace--;
                    }
                }
            }


        for(int row = 0; row < chosenLevel.getHeight(); row++)
            for(int col = 0; col < chosenLevel.getWidth(); col++){
                short bombsInNeighbourhood = 0;

                int newRow = row + 1;
                int newCol = col + 1;
                if(newRow < chosenLevel.getHeight() && newCol < chosenLevel.getWidth()){
                    if(board[newRow][newCol].isABomb())
                        bombsInNeighbourhood++;
                }
                newRow = row + 1;
                newCol = col - 1;
                if(newCol >= 0 && newRow < chosenLevel.getHeight() && newCol < chosenLevel.getWidth()){
                    if(board[newRow][newCol].isABomb())
                        bombsInNeighbourhood++;
                }
                newRow = row - 1;
                newCol = col - 1;
                if(newRow >= 0 && newCol >= 0 && newRow < chosenLevel.getHeight() && newCol < chosenLevel.getWidth()){
                    if(board[newRow][newCol].isABomb())
                        bombsInNeighbourhood++;
                }
                newRow = row - 1;
                newCol = col + 1;
                if(newRow >= 0 && newRow < chosenLevel.getHeight() && newCol < chosenLevel.getWidth()){
                    if(board[newRow][newCol].isABomb())
                        bombsInNeighbourhood++;
                }
                newRow = row;
                newCol = col - 1;
                if(newCol >= 0 && newRow < chosenLevel.getHeight() && newCol < chosenLevel.getWidth()){
                    if(board[newRow][newCol].isABomb())
                        bombsInNeighbourhood++;
                }
                newRow = row;
                newCol = col + 1;
                if(newRow < chosenLevel.getHeight() && newCol < chosenLevel.getWidth()){
                    if(board[newRow][newCol].isABomb())
                        bombsInNeighbourhood++;
                }
                newRow = row - 1;
                newCol = col;
                if(newRow >= 0 && newRow < chosenLevel.getHeight() && newCol < chosenLevel.getWidth()){
                    if(board[newRow][newCol].isABomb())
                        bombsInNeighbourhood++;
                }
                newRow = row + 1;
                newCol = col;
                if(newRow < chosenLevel.getHeight() && newCol < chosenLevel.getWidth()){
                    if(board[newRow][newCol].isABomb())
                        bombsInNeighbourhood++;
                }
                board[row][col].setNumberOfBombsInNeighbourhood(bombsInNeighbourhood);
            }

        //System.out.println("Init Ended");

    }

    private void FlagTile(int row, int col){
        board[col][row].setFlagged(true);
        numberOfBombsLeft--;
    }

    private void UnFlagTile(int row, int col){
        board[col][row].setFlagged(false);
        numberOfBombsLeft++;
    }

    private boolean Die(){
        isDead = true;
        return true;
    }

    public Tile getTile(int row, int col){
        return board[col][row];
    }

    public boolean DiscoverTile(int row, int col, int lvl){
        Tile tmp = board[col][row];
        if(tmp.isFlagged())
            return false;
        if(tmp.isABomb() && lvl == 0){
            board[col][row].setDiscovered(true);
            return Die();
        }
        else if(tmp.isABomb()){
            return false;
        }
        if(board[col][row].isDiscovered())
            return false;
        board[col][row].setDiscovered(true);
        if(tmp.getNumberOfBombsInNeighbourhood() == 0){
            int newRow = row + 1;
            int newCol = col + 1;
            if(newRow >= 0 && newCol >= 0 && newCol < chosenLevel.getHeight() && newRow < chosenLevel.getWidth()){
                this.DiscoverTile(newRow, newCol, lvl + 1);
            }
            newRow = row + 1;
            newCol = col - 1;
            if(newRow >= 0 && newCol >= 0 && newCol < chosenLevel.getHeight() && newRow < chosenLevel.getWidth()){
                this.DiscoverTile(newRow, newCol, lvl + 1);
            }
            newRow = row - 1;
            newCol = col - 1;
            if(newRow >= 0 && newCol >= 0 && newCol < chosenLevel.getHeight() && newRow < chosenLevel.getWidth()){
                this.DiscoverTile(newRow, newCol, lvl + 1);
            }
            newRow = row - 1;
            newCol = col + 1;
            if(newRow >= 0 && newCol >= 0 && newCol < chosenLevel.getHeight() && newRow < chosenLevel.getWidth()){
                this.DiscoverTile(newRow, newCol, lvl + 1);
            }
            newRow = row;
            newCol = col - 1;
            if(newRow >= 0 && newCol >= 0 && newCol < chosenLevel.getHeight() && newRow < chosenLevel.getWidth()){
                this.DiscoverTile(newRow, newCol, lvl + 1);
            }
            newRow = row;
            newCol = col + 1;
            if(newRow >= 0 && newCol >= 0 && newCol < chosenLevel.getHeight() && newRow < chosenLevel.getWidth()){
                this.DiscoverTile(newRow, newCol, lvl + 1);
            }
            newRow = row - 1;
            newCol = col;
            if(newRow >= 0 && newCol >= 0 && newCol < chosenLevel.getHeight() && newRow < chosenLevel.getWidth()){
                this.DiscoverTile(newRow, newCol, lvl + 1);
            }
            newRow = row + 1;
            newCol = col;
            if(newRow >= 0 && newCol >= 0 && newCol < chosenLevel.getHeight() && newRow < chosenLevel.getWidth()){
                this.DiscoverTile(newRow, newCol, lvl + 1);
            }
        }

        return false;
    }

    public void Draw(){
        for(int i = 0; i < chosenLevel.getHeight(); i++){
            for(int j = 0; j < chosenLevel.getWidth(); j++)
                System.out.print(board[i][j]);
            System.out.print('\n');
        }
    }

    private boolean CheckIfWon(){
        int discovered = 0;
        int toDiscover = chosenLevel.getHeight() * chosenLevel.getWidth() - chosenLevel.getNumberOfBombs();
        for(int row = 0; row < chosenLevel.getHeight(); row++)
            for(int col = 0; col < chosenLevel.getWidth(); col++){
                if(board[row][col].isDiscovered())
                    discovered++;
            }
        if(discovered == toDiscover)
            return true;
        return false;
    }

    public boolean MakeMove(String move){
        if(!isDead && !hasWon){
            String kindOfMove = move.split(" ")[0];
            int row = Integer.parseInt(move.split(" ")[1]);
            int col = Integer.parseInt(move.split(" ")[2]);
            if(kindOfMove.equals("d"))
                DiscoverTile(row, col, 0);
            else if(kindOfMove.equals("f")){
                if(board[col][row].isFlagged())
                    UnFlagTile(row, col);
                else
                    FlagTile(row, col);
            }
            hasWon = CheckIfWon();
        }

        return isDead;
    }

}
