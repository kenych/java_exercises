package marsrovers.domain;

import java.util.Arrays;

public enum Instruction {
    M, L, R,
       A, B;
    //assuming we may have additional directions
    // A -> turn half left (45 degree instead of 90)
    // B -> turn half right (45 degree instead of 90)

    public static String names() {
        return Arrays.deepToString(values());
    }
}
