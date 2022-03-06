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

    }

    private void FlagTile(int row, int col){
        board[row][col].setFlagged(true);
        numberOfBombsLeft--;
    }

    private void UnFlagTile(int row, int col){
        board[row][col].setFlagged(false);
        numberOfBombsLeft++;
    }

    private boolean Die(int row, int col){
        isDead = true;
        return true;
    }

    public Tile getTile(int row, int col){
        return board[row][col];
    }

    public boolean DiscoverTile(int row, int col, int lvl){
        Tile tmp = board[row][col];
        if(tmp.isFlagged())
            return false;
        if(tmp.isABomb() && lvl == 0){
            board[row][col].setDiscovered(true);
            return Die(row, col);
        }
        else if(tmp.isABomb()){
            return false;
        }
        if(board[row][col].isDiscovered())
            return false;
        board[row][col].setDiscovered(true);
        if(tmp.getNumberOfBombsInNeighbourhood() == 0){
            int newRow = row + 1;
            int newCol = col + 1;
            if(newRow >= 0 && newCol >= 0 && newRow < chosenLevel.getHeight() && newCol < chosenLevel.getWidth()){
                this.DiscoverTile(newRow, newCol, lvl + 1);
            }
            newRow = row + 1;
            newCol = col - 1;
            if(newRow >= 0 && newCol >= 0 && newRow < chosenLevel.getHeight() && newCol < chosenLevel.getWidth()){
                this.DiscoverTile(newRow, newCol, lvl + 1);
            }
            newRow = row - 1;
            newCol = col - 1;
            if(newRow >= 0 && newCol >= 0 && newRow < chosenLevel.getHeight() && newCol < chosenLevel.getWidth()){
                this.DiscoverTile(newRow, newCol, lvl + 1);
            }
            newRow = row - 1;
            newCol = col + 1;
            if(newRow >= 0 && newCol >= 0 && newRow < chosenLevel.getHeight() && newCol < chosenLevel.getWidth()){
                this.DiscoverTile(newRow, newCol, lvl + 1);
            }
            newRow = row;
            newCol = col - 1;
            if(newRow >= 0 && newCol >= 0 && newRow < chosenLevel.getHeight() && newCol < chosenLevel.getWidth()){
                this.DiscoverTile(newRow, newCol, lvl + 1);
            }
            newRow = row;
            newCol = col + 1;
            if(newRow >= 0 && newCol >= 0 && newRow < chosenLevel.getHeight() && newCol < chosenLevel.getWidth()){
                this.DiscoverTile(newRow, newCol, lvl + 1);
            }
            newRow = row - 1;
            newCol = col;
            if(newRow >= 0 && newCol >= 0 && newRow < chosenLevel.getHeight() && newCol < chosenLevel.getWidth()){
                this.DiscoverTile(newRow, newCol, lvl + 1);
            }
            newRow = row + 1;
            newCol = col;
            if(newRow >= 0 && newCol >= 0 && newRow < chosenLevel.getHeight() && newCol < chosenLevel.getWidth()){
                this.DiscoverTile(newRow, newCol, lvl + 1);
            }
        }

        return true;
    }

    public void Draw(){
        for(int i = 0; i < chosenLevel.getHeight(); i++){
            for(int j = 0; j < chosenLevel.getWidth(); j++)
                System.out.print(board[i][j]);
            System.out.print('\n');
        }
    }

    public void MakeMove(String move){
        String kindOfMove = move.split(" ")[0];
        int row = Integer.parseInt(move.split(" ")[1]);
        int col = Integer.parseInt(move.split(" ")[2]);
        if(kindOfMove.equals("d"))
            DiscoverTile(row, col, 0);
        else if(kindOfMove.equals("f")){
            if(board[row][col].isFlagged())
                board[row][col].setFlagged(false);
            else
                board[row][col].setFlagged(true);
        }
    }

}
