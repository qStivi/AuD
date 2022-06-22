package com.qStivi;

public class StopWatch {

    private long time = 0;

    public StopWatch() {
        this.time = System.nanoTime();
    }


    public long getTime() {
        return System.nanoTime() - time;
    }
}
