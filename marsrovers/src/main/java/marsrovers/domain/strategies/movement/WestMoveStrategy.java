package marsrovers.domain.strategies.movement;

import marsrovers.domain.Rover;

public class WestMoveStrategy implements MoveStrategy {

    @Override
    public void move(Rover rover) {
        rover.getPosition().setX(rover.getPosition().getX() - 1);
    }
}
