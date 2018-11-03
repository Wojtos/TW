package com.company;

public class BinarySemaphore {
    private Boolean sempahore;

    public BinarySemaphore() {
        this.sempahore = true;
    }

    public synchronized void decrement() {
        while (!sempahore) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.sempahore = false;
    }

    public synchronized void increment() {
        this.sempahore = true;
        this.notifyAll();
    }

}
