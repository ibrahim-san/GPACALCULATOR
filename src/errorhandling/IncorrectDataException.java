package src.errorhandling;

public class IncorrectDataException extends Exception {

    public IncorrectDataException(String message) {
        super(message);
    }
}