package gr.aueb.cf.springschoolapp.service.exception;

/**
 * This exception is used when trying to update or delete
 * a record of an Entity in the database but there is not
 * exists.
 *
 * @author Thanasis Chousiadas
 */
public class EntityNotFoundException extends Exception {
    private static final long serialVersionUID = 100L;

    /**
     * Overloaded constructor. It receives the Entity's name and the id of the
     * record to be updated or deleted and outputs a message.
     *
     * @param clazz the simple name of the Entity (class name).
     * @param id    the PK that a client has given, but it does not exist in the database.
     */
    public EntityNotFoundException(Class<?> clazz, Long id) {
        super("Entity " + clazz.getSimpleName() + " with id " + id + " does not exist");
    }
}
