package marsrovers.command;

import marsrovers.domain.Direction;
import marsrovers.domain.Instruction;
import marsrovers.domain.Position;
import marsrovers.domain.Rover;
import marsrovers.utils.Exceptions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoverCommandTest {

    @Test
    public void testParseFail() {
        Exceptions.assertThat((() -> new RoverCommand("5     5").parse()))
                .throwsException(IllegalArgumentException.class)
                .withMessageContaining("rover position must contain three characters separated by single space");

        Exceptions.assertThat((() -> new RoverCommand("5 N 5").parse()))
                .throwsException(IllegalArgumentException.class)
                .withMessageContaining("two numbers followed by compass direction");

        Exceptions.assertThat((() -> new RoverCommand("5 -1 N").parse()))
                .throwsException(IllegalArgumentException.class)
                .withMessageContaining("rover position must be two positive numbers");

        Exceptions.assertThat((() -> new RoverCommand("5 2 K").parse()))
                .throwsException(IllegalArgumentException.class)
                .withMessageContaining("rover direction must be within");

        Exceptions.assertThat((() -> new RoverCommand("5 2 N").parse()))
                .throwsException(IllegalArgumentException.class)
                .withMessageContaining("instructions: null is wrong, rover instructions must be within ");

        Exceptions.assertThat(() -> {
            RoverCommand roverCommand = new RoverCommand("5 2 N");
            roverCommand.setInstructionsLine("NNM");
            roverCommand.parse();
        }).throwsException(IllegalArgumentException.class)
                .withMessageContaining("instructions: NNM is wrong, rover instructions must be within ");

    }

    @Test
    public void testParseSuccessful() {
        RoverCommand roverCommand = new RoverCommand("5 2 N");
        roverCommand.setInstructionsLine("MMMRL");
        Rover rover = roverCommand.parse();

        assertThat(rover.getCompassDirectionValue()).isEqualTo(Direction.N);
        assertThat(rover.getPosition()).isEqualToComparingFieldByField(new Position(5, 2));
        assertThat(rover.getInstructions()).containsSequence(Instruction.M, Instruction.M, Instruction.M, Instruction.R, Instruction.L);
    }


}
