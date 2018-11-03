package com.company;

import static java.lang.Thread.sleep;

public class Drukarz implements Runnable {
    private Monitor_Drukarek monitor_drukarek;

    public Drukarz(Monitor_Drukarek monitor_drukarek) {
        this.monitor_drukarek = monitor_drukarek;

    }

    public void run() {
        int nr_drukarki;
        try {
            while(true) {
                sleep(100);
                nr_drukarki=monitor_drukarek.zarezerwuj();
                System.out.println("Wziąłem: " + nr_drukarki);
                sleep(1000);
                monitor_drukarek.zwolnij(nr_drukarki);
                System.out.println("Oddałem: " + nr_drukarki);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
