package butowska.anna.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Builder
public class Tile {

    private boolean isFlagged = false;
    private boolean isDiscovered = false;
    private boolean isABomb = false;
    private short numberOfBombsInNeighbourhood;

    public String toString(){
        if(isFlagged)
            return "!";
        if(!isDiscovered)
            return "?";
        if(isABomb)
            return "X";
        if(numberOfBombsInNeighbourhood == 0)
            return " ";
        return Integer.toString(numberOfBombsInNeighbourhood);
    }

}
