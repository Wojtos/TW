package com.company;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Shop {
    private CountingSemaphore countingSemaphore;

    public Shop(Integer maxNumberOfBasket) {
        this.countingSemaphore = new CountingSemaphore(maxNumberOfBasket);

    }

    public void take(int i) {
        countingSemaphore.decrement();
        System.out.println("Taken: " + i);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void put (int i) {
        System.out.println("Put: " + i);
        countingSemaphore.increment();
    }
}
