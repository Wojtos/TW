package com.company;

import java.util.ArrayList;
import java.util.List;

public class StatisticSaver {
    private int M;
    private List<ElementStatistic> putStatistic = new ArrayList<>();
    private List<ElementStatistic> getStatistic = new ArrayList<>();

    public StatisticSaver(int M) {
        this.M = M;
        for (int i = 0; i < M; i++) putStatistic.add(new ElementStatistic());
        for (int i = 0; i < M; i++) getStatistic.add(new ElementStatistic());
    }

    public void addGetStatistic(int numberOfElements, long time) {
        getStatistic.get(numberOfElements - 1).addStatistic(time);
    }

    public void addPutStatistic(int numberOfElements, long time) {
        putStatistic.get(numberOfElements - 1).addStatistic(time);
    }

    public void countStatistics() {
        System.out.println("Get Statistic:");
        for (int i = 0; i < this.M; i++) {
            System.out.println(getStatistic.get(i).countAverageValue() + " is an average number of elements: " +
                            (i + 1) + " average time.");
        }

        System.out.println("Put Statistic:");
        for (int i = 0; i < this.M; i++) {
            System.out.println(putStatistic.get(i).countAverageValue() + " is an average number of elements: " +
                    (i + 1) + " average time.");
        }
    }
}
