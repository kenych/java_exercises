package marsrovers.utils;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestHelper {

    private ByteArrayOutputStream out;
    private PrintStream oldOut;

    public String getOutPut() {
        return out.toString();
    }

    public void redirectStandardOutput() {
        out = new ByteArrayOutputStream();
        oldOut = System.out;
        System.setOut(new PrintStream(out));
    }

    public void restoreOutput() {
        System.setOut(oldOut);
    }

}
