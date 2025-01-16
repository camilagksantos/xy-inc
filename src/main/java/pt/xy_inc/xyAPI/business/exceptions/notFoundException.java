package pt.xy_inc.xyAPI.business.exceptions;

public class notFoundException extends RuntimeException {
    public notFoundException(String message) {
        super(message);
    }
}
