package api.shopping.execption;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class JunaException extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public JunaException(String message) {
        super(message);
    }

    public JunaException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
