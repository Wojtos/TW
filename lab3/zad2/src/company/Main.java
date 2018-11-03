package company;

import java.util.LinkedList;
import java.util.List;

public class Main {

    private static int liczbaPar = 10;
    private static Kelner kelner = new Kelner();

    public static void main(String[] argv) {
        List<Thread> ludzie = new LinkedList<>();



        for (int i = 0; i < liczbaPar; i++) {
            ludzie.add(new Thread(new Klient(kelner, i)));
            ludzie.add(new Thread(new Klient(kelner, i)));
        }
        for (Thread człek : ludzie)
            człek.start();
        for (Thread człek : ludzie)
            try {
                człek.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}
