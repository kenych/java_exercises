package marsrovers;

import static org.assertj.core.api.Assertions.assertThat;

import marsrovers.reader.TextInputFileReader;
import marsrovers.utils.TestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ApplicationMarsRoversTest {

    public static final String INPUT_FILE = "INPUT.txt";
    public static final String INPUT_FILE_EXTENDED = "INPUT_EXTENDED.txt";
    private TestHelper testHelper;

    @Before
    public void before() {
        testHelper = new TestHelper();
        testHelper.redirectStandardOutput();
    }

    @Test
    public void testDeploy() throws Exception {
        ApplicationMarsRovers marsRovers = new ApplicationMarsRovers(new TextInputFileReader(INPUT_FILE));
        marsRovers.deploy();

        assertThat(testHelper.getOutPut()).isEqualTo(
                "1 3 N\n" +
                        "5 1 E\n"

        );
    }

    @Test
    public void testDeployExtendedVersion() throws Exception {
        ApplicationMarsRovers marsRovers = new ApplicationMarsRovers(new TextInputFileReader(INPUT_FILE_EXTENDED));
        marsRovers.deploy();

        assertThat(testHelper.getOutPut()).isEqualTo(
                "1 3 N\n" +
                        "5 1 E\n" +
                        "2 4 NW\n"

        );
    }

    @After
    public void cleanup() {
        testHelper.restoreOutput();
    }

}
