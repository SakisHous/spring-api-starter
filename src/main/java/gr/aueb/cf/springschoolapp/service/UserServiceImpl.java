package gr.aueb.cf.springschoolapp.service;


import gr.aueb.cf.springschoolapp.dto.userdto.UserRegisterDTO;
import gr.aueb.cf.springschoolapp.dto.userdto.UserUpdateDTO;
import gr.aueb.cf.springschoolapp.model.User;
import gr.aueb.cf.springschoolapp.repository.IUserRepository;
import gr.aueb.cf.springschoolapp.service.exception.EntityAlreadyExistsException;
import gr.aueb.cf.springschoolapp.service.exception.EntityNotFoundException;
import gr.aueb.cf.springschoolapp.service.exception.SQLGenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This interface implements the Public API
 * of the {@link IUserService} interface, for
 * the Service Layer of this application.
 * It implements services for CRUD operations in
 * {@link User} objects.
 *
 * @author Thanasis Chousiadas
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method inserts a new teacher in the database.
     *
     * @param dto the Data Transfer Object with the data for insert the record.
     * @return the inserted {@link User} entity.
     * @throws EntityAlreadyExistsException handles insert error where the user
     *                                      already exists.
     */
    @Transactional
    @Override
    public User insertUser(UserRegisterDTO dto) throws EntityAlreadyExistsException, SQLGenericException {
        User user;
        try {
            user = userRepository.findUserByUsernameEquals(dto.getUsername());
            if (user != null) {
                throw new EntityAlreadyExistsException(
                        User.class,
                        "User with username " + dto.getUsername() + " already exists");
            }
            user = userRepository.save(convertInsertDTO(dto));
            if (user.getId() == null) {
                throw new SQLGenericException(
                        User.class,
                        "Error inserting user with username = " + dto.getUsername());
            }
        } catch (EntityAlreadyExistsException | SQLGenericException e) {
            log.info("Error inserting user: \n" + e.getMessage());
            throw e;
        }
        return user;
    }

    /**
     * This method updates an old user record with a new one.
     *
     * @param dto the Data Transfer Object with the data for update the record.
     * @return the updated {@link User} object.
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Transactional
    @Override
    public User updateUser(UserUpdateDTO dto) throws EntityNotFoundException {
        User user;
        try {
            user = userRepository.getById(dto.getId());
            if (user == null) {
                throw new EntityNotFoundException(User.class, dto.getId());
            }
            // Updating only password and not username
            user.setPassword(dto.getPassword());
            userRepository.save(user);
        } catch (EntityNotFoundException e) {
            log.info("Error updating user");
            throw e;
        }
        return user;
    }

    /**
     * This method deletes a student with an id given
     * by the user.
     *
     * @param id the id given by the user.
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Override
    public User deleteUser(Long id) throws EntityNotFoundException {
        User user;
        try {
            user = userRepository.getById(id);
            if (user == null) {
                throw new EntityNotFoundException(User.class, id);
            }
            userRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.info("Error deleting user with id: " + id);
            throw e;
        }
        return user;
    }

    /**
     * This method returns the users where their username begins
     * with the username parameter given by the client.
     *
     * @param username the username given by the client.
     * @return an {@link java.util.ArrayList} with {@link User} objects.
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Override
    public List<User> getUsersByUsernameLike(String username) throws EntityNotFoundException {
        List<User> users;
        try {
            users = userRepository.getUsersByUsernameStartingWith(username);
            if (users.size() == 0) {
                throw new EntityNotFoundException(User.class, 0L);

            }
        } catch (EntityNotFoundException e) {
            log.info("Error getting users with username starting with");
            throw e;
        }
        return users;
    }

    /**
     * This method returns a user if exists where the username
     * is exactly the same with the username given by the client.
     *
     * @param username the username given by the client.
     * @return a {@link User} object.
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                  found for database operations, such as
     *                                  update, delete and retrieve.
     */
    @Override
    public User getUserByName(String username) throws EntityNotFoundException {
        User user;

        try {
            user = userRepository.findUserByUsernameEquals(username);
            if (user == null) {
                throw  new EntityNotFoundException(User.class, 0L);
            }
        } catch (EntityNotFoundException e) {
            log.info("Error getting user with username");
            throw e;
        }
        return user;
    }

    /**
     * This method retrieves a user with an id given
     * by the user.
     *
     * @param id the id given by the user.
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Override
    public User getUserById(Long id) throws EntityNotFoundException {
        User user;
        try {
            user = userRepository.getById(id);
            if (user == null) {
                throw new EntityNotFoundException(User.class, id);
            }
        } catch (EntityNotFoundException e) {
            log.info("Error getting user with id");
            throw e;
        }
        return user;
    }

    /**
     * This method maps the {@link UserRegisterDTO} object
     * to {@link User} object for insert operation in the DB.
     *
     * @param dto a {@link UserRegisterDTO} object.
     * @return a {@link User} object.
     */
    private User convertInsertDTO(UserRegisterDTO dto) {
        User user = new User();
        user.setId(null);

        // Accepting usernames only in lowercase
        user.setUsername(dto.getUsername().toLowerCase());
        user.setPassword(dto.getPassword());
        return user;
    }

}
