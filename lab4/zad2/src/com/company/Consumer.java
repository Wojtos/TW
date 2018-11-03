package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class Consumer implements Runnable {

    final private Integer maxValue;
    final private Buffer buffer;

    public Consumer(Buffer buffer, Integer maxValue) {
        this.buffer = buffer;
        this.maxValue = maxValue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            int numberOfElements = ThreadLocalRandom.current().nextInt(1, maxValue + 1);
            System.out.println(i + "Want to fetch " + numberOfElements + " number of elements");
            buffer.get(numberOfElements);
        }
    }
}
