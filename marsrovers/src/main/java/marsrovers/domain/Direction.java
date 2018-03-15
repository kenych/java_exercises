package marsrovers.domain;

import java.util.Arrays;

public enum Direction {

//    N, E, S, W;
//we use extended 8 directions which is compatible with 4 directions
    N, NE, E, SE, S, SW, W, NW;

    public static String names() {
        return Arrays.deepToString(values());
    }

}
