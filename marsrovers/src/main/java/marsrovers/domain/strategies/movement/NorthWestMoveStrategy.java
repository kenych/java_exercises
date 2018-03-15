package marsrovers.domain.strategies.movement;

import marsrovers.domain.Rover;

//Extended move strategy
public class NorthWestMoveStrategy implements MoveStrategy {

    @Override
    public void move(Rover rover) {
        rover.getPosition().setX(rover.getPosition().getX() - 1);
        rover.getPosition().setY(rover.getPosition().getY() + 1);
    }
}
