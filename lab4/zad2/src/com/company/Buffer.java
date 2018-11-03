package com.company;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final Lock lock = new ReentrantLock();
    private final Condition change = lock.newCondition();

    final private Integer maxLength;
    final private Queue<Integer> cells;
    final private StatisticSaver statisticSaver;

    public Buffer(Integer M, StatisticSaver statisticSaver) {
        this.maxLength = 2 * M;
        this.cells = new LinkedList<>();
        this.statisticSaver = statisticSaver;
    }

    public void put(int numberOfElements, int value) {
        lock.lock();
        long start = System.nanoTime();
        while (this.maxLength - this.cells.size() < numberOfElements) {
            try {
                change.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.nanoTime();
        this.statisticSaver.addPutStatistic(numberOfElements,end - start);

        for (int i = 0; i < numberOfElements; i++) {
            cells.add(value);
        }

        System.out.println("Added " + numberOfElements + " elements of value: " +  value);

        change.signalAll();
        lock.unlock();
    }

    public void get(int numberOfElements) {
        lock.lock();
        long start = System.nanoTime();
        while (this.cells.size() < numberOfElements) {
            try {
                change.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.nanoTime();
        this.statisticSaver.addGetStatistic(numberOfElements,end - start);

        for (int i = 0; i < numberOfElements; i++) {
            cells.remove();
        }

        System.out.println("Fetched " + numberOfElements + " elements");

        change.signalAll();
        lock.unlock();
    }
}
