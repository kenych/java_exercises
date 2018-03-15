package kayantest.utils;

import org.junit.Assert;

public class TestUtils {


    Code code;
    Exception exception;

    public TestUtils(Code code) {
        this.code = code;
    }

    public TestUtils throwsException(Class<? extends Exception> type) {
        try {
            code.run();
        } catch (Exception e) {
            exception = e;
            if (e.getClass().isAssignableFrom(type)) {
                return this;
            }
            Assert.fail("Expected to throw " + type.getName() + ", got " + e.getClass().getName());
        }
        Assert.fail("Expected to throw " + type.getName() + ", got nothing");

        return this;
    }

    public TestUtils withMessageContaining(String s) {
        if (exception.getMessage().contains(s)) {
            return this;
        }
        Assert.fail("Expected message contains " + s + ", but was: " + exception.getMessage());

        return this;
    }

    @FunctionalInterface
    public static interface Code {
        void run();
    }

    public static TestUtils assertThat(Code code) {
        return new TestUtils(code);
    }


}