package com.company;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Integer producersCount = 10;
        Integer consumersCount = 10;
        Integer M = 10;

        List<Thread> threads = new LinkedList<>();

        final StatisticSaver statisticSaver = new StatisticSaver(M);
        Buffer buffer = new Buffer(M, statisticSaver);

        for (int i = 0; i < producersCount; i++) {
            threads.add(new Thread(new Producer(i, buffer, M)));
        }


        for (int i = 0; i < consumersCount; i++) {
            threads.add(new Thread(new Consumer(buffer, M)));
        }


        for (Thread thread : threads)
            thread.start();
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                statisticSaver.countStatistics();
            }
        }
    }
}
