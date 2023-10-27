package gr.aueb.cf.springschoolapp.service.exception;

/**
 * This exception class is used when a record for an
 * entity is already exists in the database and an
 * operation is trying to insert a duplicate record.
 *
 * @author Thanasis Chousiadas
 */
public class EntityAlreadyExistsException extends Exception {
    private static final long serialVersionUID = 100L;

    /**
     * Overloaded constructor. It receives the Entity's name and the id of
     * the duplicated record and outputs a message.
     *
     * @param clazz the simple name of the Entity (class name).
     * @param s    the custom message to be displayed if the exception is thrown.
     */
    public EntityAlreadyExistsException(Class<?> clazz, String s) {
        super("[SQL Error]: Entity with name " + clazz.getSimpleName() + " - " + s);
    }
}
