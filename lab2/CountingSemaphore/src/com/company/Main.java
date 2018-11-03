package com.company;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Shop shop = new Shop(5);

        int numberOfThreads = 20;

        List<Thread> consumerThreads = new LinkedList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            consumerThreads.add(new Thread(new Consumer(shop, i)));
        }

        for (int i = 0; i < numberOfThreads; i++) {
            consumerThreads.get(i).start();
        }


        try {
            for (int i = 0; i < numberOfThreads; i++) {
                consumerThreads.get(i).join();
            }
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
    }
}
