package marsrovers.domain;

import marsrovers.command.RoverCommand;
import marsrovers.utils.Exceptions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoverTest {
    @Test
    public void testTurnRight2xFromN() {
        RoverCommand roverCommand = new RoverCommand("5 2 N");
        roverCommand.setInstructionsLine("RR");
        Rover rover = roverCommand.parse();
        rover.explore();

        assertThat(rover.getCompassDirectionValue()).isEqualTo(Direction.S);
    }

    @Test
    public void testTurnRight3XFromN() {
        RoverCommand roverCommand = new RoverCommand("5 2 N");
        roverCommand.setInstructionsLine("RRR");
        Rover rover = roverCommand.parse();
        rover.explore();

        assertThat(rover.getCompassDirectionValue()).isEqualTo(Direction.W);

    }

    @Test
    public void testTurnRight4XFromN() {
        RoverCommand roverCommand = new RoverCommand("5 2 N");
        roverCommand.setInstructionsLine("RRRR");
        Rover rover = roverCommand.parse();
        rover.explore();

        assertThat(rover.getCompassDirectionValue()).isEqualTo(Direction.N);
    }

    @Test
    public void testTurnRight5XFromN() {
        RoverCommand roverCommand = new RoverCommand("5 2 N");
        roverCommand.setInstructionsLine("RRRRR");
        Rover rover = roverCommand.parse();
        rover.explore();

        assertThat(rover.getCompassDirectionValue()).isEqualTo(Direction.E);
    }

    @Test
    public void testTurnLeft1XFromW() {
        RoverCommand roverCommand = new RoverCommand("5 2 W");
        roverCommand.setInstructionsLine("L");
        Rover rover = roverCommand.parse();
        rover.explore();

        assertThat(rover.getCompassDirectionValue()).isEqualTo(Direction.S);
    }

    @Test
    public void testTurnLeft4XFromW() {
        RoverCommand roverCommand = new RoverCommand("5 2 W");
        roverCommand.setInstructionsLine("LLLL");
        Rover rover = roverCommand.parse();
        rover.explore();

        assertThat(rover.getCompassDirectionValue()).isEqualTo(Direction.W);
    }

    @Test
    public void testMoveWest() {
        RoverCommand roverCommand = new RoverCommand("5 2 W");
        roverCommand.setInstructionsLine("M");
        Rover rover = roverCommand.parse();
        rover.setPlateau(new Plateau( new Position(10,10)));
        rover.explore();

        assertThat(rover.getCompassDirectionValue()).isEqualTo(Direction.W);
        assertThat(rover.getPosition()).isEqualToComparingFieldByField(new Position(4,2));
    }

    @Test
    public void testMoveEast() {
        RoverCommand roverCommand = new RoverCommand("5 2 E");
        roverCommand.setInstructionsLine("M");
        Rover rover = roverCommand.parse();
        rover.setPlateau(new Plateau( new Position(10,10)));
        rover.explore();

        assertThat(rover.getCompassDirectionValue()).isEqualTo(Direction.E);
        assertThat(rover.getPosition()).isEqualToComparingFieldByField(new Position(6,2));
    }

    @Test
    public void testMoveNorth() {
        RoverCommand roverCommand = new RoverCommand("5 2 N");
        roverCommand.setInstructionsLine("M");
        Rover rover = roverCommand.parse();
        rover.setPlateau(new Plateau( new Position(10,10)));
        rover.explore();

        assertThat(rover.getCompassDirectionValue()).isEqualTo(Direction.N);
        assertThat(rover.getPosition()).isEqualToComparingFieldByField(new Position(5,3));
    }

    @Test
    public void testMoveSouth() {
        RoverCommand roverCommand = new RoverCommand("5 2 S");
        roverCommand.setInstructionsLine("M");
        Rover rover = roverCommand.parse();
        rover.setPlateau(new Plateau( new Position(10,10)));
        rover.explore();

        assertThat(rover.getCompassDirectionValue()).isEqualTo(Direction.S);
        assertThat(rover.getPosition()).isEqualToComparingFieldByField(new Position(5,1));
    }

    @Test
    public void testMove2xSouth() {
        RoverCommand roverCommand = new RoverCommand("5 2 S");
        roverCommand.setInstructionsLine("MM");
        Rover rover = roverCommand.parse();
        rover.setPlateau(new Plateau( new Position(10,10)));
        rover.explore();

        assertThat(rover.getCompassDirectionValue()).isEqualTo(Direction.S);
        assertThat(rover.getPosition()).isEqualToComparingFieldByField(new Position(5, 0));
    }

    @Test
    public void testMove3xSouthFailsAsGoesBelowZero() {
        RoverCommand roverCommand = new RoverCommand("5 2 S");
        roverCommand.setInstructionsLine("MMM");
        Rover rover = roverCommand.parse();
        rover.setPlateau(new Plateau( new Position(10,10)));

        rover.setPlateau(new Plateau(new Position(5,2)));

        Exceptions.assertThat(()->rover.explore())
                .throwsException(IllegalArgumentException.class)
                .withMessageContaining("position is below 0");

    }

    @Test
    public void testMove3xNorthFailsAsGoesAbovePlateauLimits() {
        RoverCommand roverCommand = new RoverCommand("5 2 N");
        roverCommand.setInstructionsLine("MMM");
        Rover rover = roverCommand.parse();

        rover.setPlateau(new Plateau(new Position(7,2)));

        Exceptions.assertThat(()->rover.explore())
                .throwsException(IllegalArgumentException.class)
                .withMessageContaining("is above limits of plateau");

    }
}
