package marsrovers.domain.strategies.movement;

import marsrovers.domain.Rover;

public class SouthMoveStrategy implements MoveStrategy {

    @Override
    public void move(Rover rover) {
        rover.getPosition().setY(rover.getPosition().getY() - 1);
    }
}
