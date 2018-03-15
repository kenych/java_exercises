package com.kayan.lottery.service;


import org.junit.Test;

import static com.kayan.lottery.service.TestUtil.assertThat;

public class TestTestUtil {

    @Test
    public void test(){

        Code code = new Code() {
            @Override
            public void run() {

                System.out.println("i have been run");
                throw new IllegalArgumentException("TestUtil");


            }
        };

        assertThat(code)
                .throwsException(IllegalArgumentException.class)
                .withMessage("TestUtil");


    }
}
