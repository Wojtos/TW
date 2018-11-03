package com.company;

public class Main {

    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread consumerThread1 = new Thread(new Consumer(buffer));
        Thread consumerThread2 = new Thread(new Consumer(buffer));
        Thread producerThread1 = new Thread(new Producer(buffer));
        Thread producerThread2 = new Thread(new Producer(buffer));

       // producerThread1.start();
        producerThread2.start();
        consumerThread1.start();
        //consumerThread2.start();


        try {
            consumerThread1.join();
            //consumerThread2.join();
            //producerThread1.join();
            producerThread2.join();
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
    }
}
