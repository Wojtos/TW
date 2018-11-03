package company;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class Kelner {

    private final Lock kłódka = new ReentrantLock();
    private Map<Integer, Condition> ludzieWKolejce = new HashMap<>();
    private Integer ludzieSiedzący = 0;
    private final Condition niePuste = kłódka.newCondition();
    private Boolean dostępnyStolik = true;

    

    public void chce_stolik(int numberPary){
        kłódka.lock();
        System.out.println("Prośba o stolik, para: " + numberPary );
        if(ludzieWKolejce.containsKey(numberPary)){
            while(!dostępnyStolik){
                try {
                    niePuste.await();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            ludzieWKolejce.get(numberPary).signal();
            ludzieSiedzący = 2;
            dostępnyStolik = false;

        }
        else {
            Condition czekajNaPartnera = kłódka.newCondition();
            ludzieWKolejce.put(numberPary, czekajNaPartnera);
            try {
                czekajNaPartnera.await();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Dostaliśmy stolik, para: " + numberPary);
        kłódka.unlock();
    }

    public void zwalniam(int numerPary){
        kłódka.lock();
        ludzieSiedzący--;
        if(ludzieSiedzący == 0) {
            dostępnyStolik = true;
            niePuste.signal();
        }
        System.out.println("Dowidzenia, para: " + numerPary);
        kłódka.unlock();
    }
}