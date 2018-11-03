package com.company;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Monitor_Drukarek monitor_drukarek = new Monitor_Drukarek(5);

        int numberOfThreads = 20;

        List<Thread> watkiDrukarzy = new LinkedList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            watkiDrukarzy.add(new Thread(new Drukarz(monitor_drukarek)));
        }

        for (int i = 0; i < numberOfThreads; i++) {
            watkiDrukarzy.get(i).start();
        }


        try {
            for (int i = 0; i < numberOfThreads; i++) {
                watkiDrukarzy.get(i).join();
            }
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
    }
}
