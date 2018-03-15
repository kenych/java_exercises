package kayantest.utils;

import kayantest.domain.PlayerSession;

import java.util.concurrent.TimeUnit;

public class PlayerTestHelper {


    public static PlayerSession createPlayerSession() {
        return new PlayerSession("xxx", 1, System.nanoTime());
    }

    public static PlayerSession createPlayerSessionInThePast(int seconds) {
        return new PlayerSession("xxx", 1, System.nanoTime() - TimeUnit.SECONDS.toNanos(seconds));
    }
}
