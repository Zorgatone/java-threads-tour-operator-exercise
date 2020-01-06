import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Travel {

  private final Lock locking = new ReentrantLock();

  private final Condition notPending = locking.newCondition();

  private final String destination;
  private final int minimumParticipants;

  private ArrayList<Person> bookedParticipants;
  private boolean closed;

  public Travel(String destination, int minimumParticipants) {
    this.closed = false;
    this.bookedParticipants = new ArrayList<>(minimumParticipants);

    this.destination = destination;
    this.minimumParticipants = minimumParticipants;
  }

  public String getDestination() {
    return destination;
  }

  public Condition getNotPending() {
    return notPending;
  }

  public Lock getLocking() {
    return locking;
  }

  public void close() {

    if (!closed) {
      locking.lock();

      try {
        closed = true;
        notPending.signalAll();

        if (bookedParticipants.size() >= minimumParticipants) {
          System.out.println("Booked participants:");

          for (Person p : bookedParticipants) {
            System.out.println(p.getName());
          }
        } else {
          System.out.println("Travel as been cancelled!");
        }
      } finally {
        locking.unlock();
      }
    }
  }

  public boolean book(Person p) throws TravelClosedException {
    locking.lock();

    try {
      if (closed) {
        throw new TravelClosedException("Travel is closed!");
      }

      if (!bookedParticipants.contains(p)) {
        bookedParticipants.add(p);
      }

      final int size = bookedParticipants.size();

      return closed && size >= minimumParticipants;
    } finally {
      locking.unlock();
    }
  }
}
