package com.company;

public class Buffer {
    private Boolean empty;
    private String content;

    public Buffer() {
        this.empty = true;
    }

    public synchronized String take() {
        while(empty) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String toReturn = this.content;
        this.empty = true;
        this.notifyAll();
        return toReturn;
    }

    public synchronized void put (String message) {
        while(!empty) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.empty = false;
        this.content = message;
        this.notifyAll();
    }
}
