import java.util.ArrayList;

public final class TourOperator {

  public static void main(String[] args) {
    Travel travel = new Travel("Namibia", 40);
    ArrayList<Person> persons = new ArrayList<>();

    Thread addThread = new Thread(
        () -> {
          for (int i = 0; i < 100; i++) {
            persons.add(new Person(String.format("Person #%d", i + 1), travel));
          }
        });

    Thread runThread = new Thread(
        () -> {
          for (Person p : persons) {
            p.start();
          }
        });

    addThread.start();

    try {
      addThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    runThread.start();

    try {
      Thread.sleep(2);
    } catch (InterruptedException e) {
//      e.printStackTrace();
    }

    travel.close();
  }
}
