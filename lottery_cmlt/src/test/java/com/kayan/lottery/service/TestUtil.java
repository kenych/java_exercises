package com.kayan.lottery.service;

public class TestUtil {

    Code code;
    String message;

    public TestUtil(Code code) {
        this.code = code;

    }


    public TestUtil withMessage(String xxx) {
        if(!xxx.equals(message))    {
            throw new IllegalStateException("wrong message ")  ;

        }
        return  this;
    }

    public TestUtil throwsException(Class illegalArgumentExceptionClass) {
        try {
            code.run();

        } catch (Exception e) {
            if (!e.getClass().equals(illegalArgumentExceptionClass)){

                 throw new IllegalStateException("wrong exc thrown ")  ;
            }
            message = e.getMessage();

        }

        return this;

    }

    public static TestUtil assertThat(Code code) {
        return new TestUtil(code);
    }


}


interface Code {
    void run();
}
