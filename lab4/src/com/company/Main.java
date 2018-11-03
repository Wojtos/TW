package com.company;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Integer producersCount = 5;
        Integer consumersCount = 1;
        Integer cellsCount = 10;

        List<Thread> threads = new LinkedList<>();

        Buffer buffer = new Buffer(cellsCount, producersCount - 1);

        for (int i = 0; i < producersCount; i++) {
            threads.add(new Thread(new ProducerOrConsumer(i, buffer)));
        }

        for (int i = 0; i < consumersCount; i++) {
            threads.add(new Thread(new ProducerOrConsumer(-1, buffer)));
        }

        for (Thread thread : threads)
            thread.start();
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
