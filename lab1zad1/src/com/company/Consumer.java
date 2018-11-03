package com.company;

public class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {

        for(int i = 0;  i < 10;   i++) {
            String message = buffer.take();
            System.out.println(message);
        }

    }
}
