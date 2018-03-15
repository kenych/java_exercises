package marsrovers.command;

import marsrovers.domain.Plateau;
import marsrovers.domain.Position;
import marsrovers.utils.Exceptions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlateauCommandTest {

    @Test
    public void testParseFails() throws Exception {

        Exceptions.assertThat((() -> new PlateauCommand("5     5").parse()))
                .throwsException(IllegalArgumentException.class)
                .withMessageContaining("Plateau coordinates must contain two digits separated by single space");

        Exceptions.assertThat((() -> new PlateauCommand("5 AAA").parse()))
                .throwsException(IllegalArgumentException.class)
                .withMessageContaining("Plateau coordinates must contain two positive numbers");

        Exceptions.assertThat((() -> new PlateauCommand("5 -1").parse()))
                .throwsException(IllegalArgumentException.class)
                .withMessageContaining("Plateau coordinates must be positive numbers");
    }

    @Test
    public void testParseSuccessful() throws Exception {

        PlateauCommand plateauCommand = new PlateauCommand("5 5");
        Plateau plateau = plateauCommand.parse();

        assertThat(plateau.getCoordinates()).isEqualToComparingFieldByField(new Position(5, 5));
    }
}
