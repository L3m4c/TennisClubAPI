package exceptions;

public class CollisionWithAnExistingReservationException extends Exception {
    public CollisionWithAnExistingReservationException(String errorMessage) {
        super(errorMessage);
    }
}
