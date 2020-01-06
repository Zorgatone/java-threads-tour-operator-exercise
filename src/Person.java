public class Person extends Thread {

  private final Travel travel;

  public Person(String name, Travel travel) {
    super(name);

    this.travel = travel;
  }

  @Override
  public void run() {
    travel.getLocking().lock();

    try {
      while(!travel.book(this)) {
        try {
          travel.getNotPending().await();
        } catch (InterruptedException e) {
          break;
        }
      }
    } catch (TravelClosedException e) {
      // return
    } finally {
      travel.getLocking().unlock();
    }
  }
}
