package marsrovers.domain.strategies.movement;

import marsrovers.domain.*;

public class MoveInstructionStrategy implements InstructionStrategy {

    @Override
    public void follow(Rover rover, Instruction instruction) {

        Direction direction = rover.getCompass().getDirection();
        MoveFactory.strategyBy(direction).move(rover);
        validatePosition(rover.getPosition(), rover.getPlateau().getCoordinates());

    }

    void validatePosition(Position roverPosition, Position plateauPosition) {
        if (roverPosition.getX() < 0 || roverPosition.getY() < 0) {
            throw new IllegalArgumentException("position is below 0 :" + roverPosition);
        } else if (roverPosition.getX() > plateauPosition.getX() || roverPosition.getY() > plateauPosition.getY()) {
            throw new IllegalArgumentException("roverPosition position: " + roverPosition + " is above limits of plateau :" + plateauPosition);
        }
    }
}
