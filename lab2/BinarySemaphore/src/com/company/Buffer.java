package com.company;

public class Buffer {
    private Boolean empty;
    private String content;
    private BinarySemaphore binarySemaphore;

    public Buffer() {
        this.empty = true;
        this.binarySemaphore = new BinarySemaphore();
    }

    public String take() {
        do {
            binarySemaphore.decrement();

            if (this.empty) binarySemaphore.increment();
        } while (this.empty);
        String toReturn = this.content;
        this.empty = true;
        binarySemaphore.increment();
        return toReturn;
    }

    public void put (String message) {
        do {
            binarySemaphore.decrement();
            if (!this.empty) binarySemaphore.increment();
        } while (!this.empty);
        this.empty = false;
        this.content = message;
        binarySemaphore.increment();
    }
}
