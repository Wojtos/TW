package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor_Drukarek {
    final Lock klodka = new ReentrantLock();
    final Condition nieMaAniJednejWolnejDrukarkiNaStanie  = klodka.newCondition();

    private Integer liczbaDrukarek;
    private Queue<Integer> wolneDrukarki = new LinkedList<>();
    public Monitor_Drukarek(int liczbaDrukarek) {
        this.liczbaDrukarek = liczbaDrukarek;
        for (int i = 0; i < this.liczbaDrukarek; i++) {
            wolneDrukarki.add(i);
        }
    }

    public Integer zarezerwuj() throws InterruptedException {
        klodka.lock();
        try {
            while (wolneDrukarki.isEmpty())
                nieMaAniJednejWolnejDrukarkiNaStanie.await();
            return wolneDrukarki.remove();
        } finally {
            klodka.unlock();
        }
    }

    public void zwolnij(Integer numerDrukarki) throws InterruptedException {
        klodka.lock();
        try {
            wolneDrukarki.add(numerDrukarki);
            nieMaAniJednejWolnejDrukarkiNaStanie.signal();
        } finally {
            klodka.unlock();
        }
    }
}
