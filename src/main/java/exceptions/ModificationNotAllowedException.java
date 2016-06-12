package exceptions;

public class ModificationNotAllowedException extends Exception {
    public ModificationNotAllowedException(String errorMsg) {
        super(errorMsg);
    }
}
