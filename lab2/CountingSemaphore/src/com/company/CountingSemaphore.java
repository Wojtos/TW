package com.company;

public class CountingSemaphore {
    private Integer sempahore;
    private Integer N;

    public CountingSemaphore(int N) {
        this.N = N;
        this.sempahore = N;
    }

    public synchronized void decrement() {
        while (sempahore <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.sempahore--;
    }

    public synchronized void increment() {
        this.sempahore++;
        this.notifyAll();
    }

}
