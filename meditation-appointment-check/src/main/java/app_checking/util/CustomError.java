package app_checking.util;

public class CustomError extends Exception {
    private Integer code;
    private String message;
    private Object details;

    public CustomError() {
    }

    public CustomError(Integer code, String message, Object details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }
}
