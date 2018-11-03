package com.company;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {
    private final Lock lock = new ReentrantLock();
    private final Condition change = lock.newCondition();
    private final Integer index;

    private Integer value;
    private Integer maxValue;

    public Cell(Integer index, Integer maxValue) {
        this.value = -1;
        this.maxValue = maxValue;
        this.index = index;
    }

    public void changeToValue(Integer value) {
        lock.lock();

        int expectedPreviousValue = value == -1 ? this.maxValue : value - 1;

        while (expectedPreviousValue != this.value) {
            try {
                change.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.value = value;
        change.signalAll();

        System.out.println("Cell nr: " + this.index + " value: " +  this.value);
        lock.unlock();
    }


}
