package company;

import static java.lang.Thread.sleep;

public class Klient implements Runnable {
    private Kelner kelner;
    private Klient partner;
    private Integer numerPary;

    public Klient(Kelner kelner, int numerPary){
        this.kelner = kelner;
        this.numerPary = numerPary;
    }

    public void run() {
        kelner.chce_stolik(numerPary);
        System.out.println("Amamam!");
        kelner.zwalniam(numerPary);
    }
}
