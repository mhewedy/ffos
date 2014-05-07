package model;

import java.util.Arrays;

/**
 * Created by mhewedy on 07/05/14.
 */
public class InvalidObjectException extends Exception {
    private String[] fields;

    public InvalidObjectException(String... fields) {
        this.fields = fields;
    }

    @Override
    public String getMessage() {
        return Arrays.toString(fields);
    }
}
