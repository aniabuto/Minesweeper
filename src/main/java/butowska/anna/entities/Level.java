package butowska.anna.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Level {

    private String name;
    private int width;
    private int height;
    private int numberOfBombs;

}
