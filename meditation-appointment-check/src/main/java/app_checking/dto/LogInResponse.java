package app_checking.dto;

import java.io.Serializable;

public class LogInResponse implements Serializable {

    private final String token;

    public LogInResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
