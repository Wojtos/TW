package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final Lock lock = new ReentrantLock();
    private final List<List<Condition>> putAwake = new LinkedList<>();
    private final List<List<Condition>> getAwake= new LinkedList<>();

    final private Integer maxLength;
    final private Integer M;
    final private Queue<Integer> cells;
    final private StatisticSaver statisticSaver;

    public Buffer(Integer M, StatisticSaver statisticSaver) {
        this.maxLength = 2 * M;
        this.M = M;
        this.cells = new LinkedList<>();
        this.statisticSaver = statisticSaver;
        for (int i = 0; i < M; i++) {
            putAwake.add(new LinkedList<>());
        }
        for (int i = 0; i < M; i++) {
            getAwake.add(new LinkedList<>());
        }
    }

    public void put(int numberOfElements, int value) {
        lock.lock();
        final Condition newCondition = lock.newCondition();

        long start = System.nanoTime();
        while (this.maxLength - this.cells.size() < numberOfElements) {
            try {
                putAwake.get(numberOfElements - 1).add(newCondition);
                newCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.nanoTime();
        for (int i = Math.min(M, this.cells.size()) - 1; i >= 0; i--) {
            if (!getAwake.get(i).isEmpty()) {
                getAwake.get(i).remove(0).signal();
                break;
            }
        }
        this.statisticSaver.addPutStatistic(numberOfElements,end - start);

        for (int i = 0; i < numberOfElements; i++) {
            cells.add(value);
        }

        System.out.println("Added " + numberOfElements + " elements of value: " +  value);

        lock.unlock();
    }

    public void get(int numberOfElements) {
        lock.lock();
        final Condition newCondition = lock.newCondition();

        long start = System.nanoTime();
        while (this.cells.size() < numberOfElements) {
            try {
                getAwake.get(numberOfElements - 1).add(newCondition);
                newCondition.await();
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

        for (int i = Math.min(M, this.maxLength - this.cells.size()) - 1; i >= 0; i--) {
            if (!putAwake.get(i).isEmpty()) {
                putAwake.get(i).remove(0).signal();
                break;
            }
        }
        lock.unlock();
    }
}
