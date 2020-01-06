import java.io.Serializable;

public class TravelClosedException extends Exception implements Serializable, Cloneable {
  private static final long serialVersionUID = 15785L;

  public TravelClosedException() {
    super();
  }

  public TravelClosedException(String message) {
    super(message);
  }

  public TravelClosedException(Throwable cause) {
    super(cause);
  }

  public TravelClosedException(String message, Throwable cause) {
    super(message, cause);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }
}
