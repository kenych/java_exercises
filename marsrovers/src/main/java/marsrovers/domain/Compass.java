package marsrovers.domain;

import static marsrovers.domain.Direction.*;

public class Compass {

    private int pointer = -1;
//    private Direction[] directions = new Direction[]{N, E, S, W};
    //we use extended 8 directions which is compatible with 4 directions
    private Direction[] directions = new Direction[]{ N, NE, E, SE, S, SW, W, NW};

    public Compass(Direction direction) {
        pointer = compassPointerBy(direction);
    }

    int compassPointerBy(Direction direction) {
        for (int index = 0; index < directions.length; index++) {
            if (directions[index] == direction) {
                return index;
            }
        }
        throw new RuntimeException("compass direction not found: " + direction);
    }

    public Direction getDirection() {
        return directions[pointer];
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public int getPointer() {
        return pointer;
    }
}
