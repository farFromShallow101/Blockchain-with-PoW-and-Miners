package blockchain;

import java.util.concurrent.TimeUnit;

class Stopwatch {
    static private long start;

    static void reset() {
        start = System.nanoTime();
    }

    static long getElapsedSeconds() {

        long end = System.nanoTime();
        long elapsedNano = end - start;
        long elapsedSeconds = TimeUnit.SECONDS.convert(elapsedNano, TimeUnit.NANOSECONDS);
        return elapsedSeconds;
    }
}
