package marsrovers.domain;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CompassTest {

    @Test
    public void testGetDirection() throws Exception {
        Compass compass = new Compass(Direction.S);
        assertThat(compass.getDirection()).isEqualTo(Direction.S);
        assertThat(compass.getPointer()).isEqualTo(4);
    }

    @Test
    public void testSetPointer() throws Exception {
        Compass compass = new Compass(Direction.S);
        compass.setPointer(5);
        assertThat(compass.getDirection()).isEqualTo(Direction.SW);
    }

}
