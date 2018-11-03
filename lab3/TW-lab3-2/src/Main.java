import java.util.LinkedList;
import java.util.List;

public class Main {

    private static int amountOfPairs = 5;
    private static int amountOfTables = 3;
    private static Waiter waiter = new Waiter(amountOfTables);

    public static void main(String[] argv) {
        List<Thread> people = new LinkedList<>();
        for (int i = 0; i < amountOfPairs; i++) {
            people.add(new Thread(new Person(waiter, i)));
            people.add(new Thread(new Person(waiter, i)));
        }
        for (Thread person : people)
            person.start();
        for (Thread person : people)
            try {
                person.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}
