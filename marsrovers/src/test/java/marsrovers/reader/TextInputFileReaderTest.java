package marsrovers.reader;

import marsrovers.command.CommandList;
import marsrovers.command.RoverCommand;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TextInputFileReaderTest {

    private CommandList commandList;

    public static final String INPUT_FILE = "INPUT.txt";

    @Before
    public void before() {
        TextInputFileReader textInputFileReader = new TextInputFileReader(INPUT_FILE);
        commandList = textInputFileReader.read();
    }

    @Test
    public void testRead() throws Exception {

        String plateauCoordinatesLine = commandList.getPlateauCommand().getCoordinatesLine();
        List<RoverCommand> roverCommands = commandList.getRoverCommands();

        assertThat(plateauCoordinatesLine).isEqualTo("5 5");

        assertThat(roverCommands).hasSize(2);

        assertThat(roverCommands.get(0).getPositionLine()).isEqualTo("1 2 N");
        assertThat(roverCommands.get(0).getInstructionsLIne()).isEqualTo("LMLMLMLMM");

        assertThat(roverCommands.get(1).getPositionLine()).isEqualTo("3 3 E");
        assertThat(roverCommands.get(1).getInstructionsLIne()).isEqualTo("MMRMMRMRRM");
    }
}
