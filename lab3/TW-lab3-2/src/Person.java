public class Person implements Runnable {
    private int pairID;
    private Waiter waiter;

    public Person(Waiter waiter, int pairID){
        this.waiter = waiter;
        this.pairID = pairID;
    }

    @Override
    public void run() {
        waiter.askForTable(pairID);
        eat();
        waiter.leaveTable(pairID);
    }

    private void eat(){

    }
}
