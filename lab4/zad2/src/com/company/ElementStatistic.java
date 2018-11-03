package com.company;

public class ElementStatistic {
    private long summedTime = 0;
    private int numberOfElements = 0;

    public void addStatistic(long time) {
        summedTime += time;
        numberOfElements++;
    }

    public long countAverageValue() {
        return numberOfElements == 0 ? 0 : summedTime / numberOfElements;
    }
}
