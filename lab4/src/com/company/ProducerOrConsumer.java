package com.company;

public class ProducerOrConsumer implements Runnable {
    final private Integer numberToWrite;
    final private Buffer buffer;

    public ProducerOrConsumer(Integer numberToWrite, Buffer buffer) {
        this.numberToWrite = numberToWrite;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (Cell cell:
             buffer) {
            cell.changeToValue(this.numberToWrite);
        }
    }
}
