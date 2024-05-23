package hello.jdbc.repository.ex;

public class MyDuplicateKeyException extends MyDBException{
    public MyDuplicateKeyException(String message) {
        super(message);
    }

    public MyDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDuplicateKeyException(Throwable cause) {
        super(cause);
    }

    public MyDuplicateKeyException() {
        super();
    }
}
