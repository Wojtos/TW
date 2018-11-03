package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {
    final private Integer numberToWrite;
    final private Integer maxValue;
    final private Buffer buffer;

    public Producer(Integer numberToWrite, Buffer buffer, Integer maxValue) {
        this.numberToWrite = numberToWrite;
        this.buffer = buffer;
        this.maxValue = maxValue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            int numberOfElements = ThreadLocalRandom.current().nextInt(1, maxValue + 1);
            System.out.println(i + "Want to put " + numberOfElements + " number of elements");
            buffer.put(numberOfElements, numberToWrite);
        }
    }
}
