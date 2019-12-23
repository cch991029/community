package life.cch.community.community.exception;

/**
 * Created by codedrinker on 2019-12-23 11:35:01
 */
public class CustomizeException extends RuntimeException{

    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
