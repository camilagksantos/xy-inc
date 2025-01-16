package pt.xy_inc.xyAPI.business.exceptions;

public class NegativeNumberException extends RuntimeException{
    public NegativeNumberException(String message) {
        super(message);
    }
}
