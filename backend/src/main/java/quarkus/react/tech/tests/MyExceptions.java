package quarkus.react.tech.tests;

public class MyExceptions {
    public static class AlreadyExistsException extends RuntimeException {
        public AlreadyExistsException(String message) {
            super(message);
        }
    }
}
