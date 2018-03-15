package marsrovers.domain;

import marsrovers.command.RoverCommand;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

//and same could be done for all other strategies
public class EastMoveStrategyTest {
    @Test
    public void testMove() throws Exception {
        RoverCommand roverCommand = new RoverCommand("5 2 E");
        roverCommand.setInstructionsLine("M");
        Rover rover = roverCommand.parse();
        rover.setPlateau(new Plateau(new Position(10, 10)));

        rover.explore();

        assertThat(rover.getPosition()).isEqualToComparingFieldByField(new Position(6, 2));
    }
}
