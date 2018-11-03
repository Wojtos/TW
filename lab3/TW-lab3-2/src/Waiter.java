import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Waiter {

    private final Lock lock = new ReentrantLock();
    private Map<Integer, Condition> peopleInQueue = new HashMap<>();
    private Map<Integer, Integer> peopleSitting = new HashMap<>();
    private final Condition notEmpty = lock.newCondition();
    private int availableTables;

    Waiter(int availableTables){
        this.availableTables = availableTables;
    }


    public void askForTable(int pairID){
        lock.lock();
        System.out.println("Asking for a table, pair: " + pairID + ", available tables: " + availableTables);
        if(peopleInQueue.containsKey(pairID)){
            while(availableTables == 0){
                try {
                    notEmpty.await();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            peopleInQueue.get(pairID).signal();
            peopleSitting.put(pairID, 2);
            availableTables--;

        }
        else {
            Condition condition = lock.newCondition();
            peopleInQueue.put(pairID, condition);
            try {
                condition.await();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Recieved table, pair: " + pairID + ", available tables: " + availableTables);
        lock.unlock();
    }

    public void leaveTable(int pairID){
        lock.lock();
        int atTable = peopleSitting.get(pairID);
        atTable--;
        if(atTable == 1)
            peopleSitting.replace(pairID, atTable);
        else {
            peopleSitting.remove(pairID);
            availableTables++;
            notEmpty.signal();
        }
        System.out.println("Left table, pair: " + pairID + ", available tables: " + availableTables);
        lock.unlock();
    }
}
