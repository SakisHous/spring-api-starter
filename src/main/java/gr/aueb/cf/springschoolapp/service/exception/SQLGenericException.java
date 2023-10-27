package gr.aueb.cf.springschoolapp.service.exception;

public class SQLGenericException extends Exception {
    private static final long serialVersionUID = 1L;

    public SQLGenericException(Class<?> clazz, String s) {
        super("[SQL Error]: Class " + clazz.getSimpleName() + " - " + s);
    }
}
