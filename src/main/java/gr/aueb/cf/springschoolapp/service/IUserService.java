package gr.aueb.cf.springschoolapp.service;


import gr.aueb.cf.springschoolapp.dto.userdto.UserRegisterDTO;
import gr.aueb.cf.springschoolapp.dto.userdto.UserUpdateDTO;
import gr.aueb.cf.springschoolapp.model.User;
import gr.aueb.cf.springschoolapp.service.exception.EntityAlreadyExistsException;
import gr.aueb.cf.springschoolapp.service.exception.EntityNotFoundException;
import gr.aueb.cf.springschoolapp.service.exception.SQLGenericException;

import java.util.List;

/**
 * This interface declares the Public API
 * regarding CRUD operations in {@link User}
 * objects for the Service Layer.
 *
 * @author Thanasis Chousiadas
 */
public interface IUserService {
    User insertUser(UserRegisterDTO dto) throws EntityAlreadyExistsException, SQLGenericException;
    User updateUser(UserUpdateDTO dto) throws EntityNotFoundException;
    User deleteUser(Long id) throws EntityNotFoundException;
    List<User> getUsersByUsernameLike(String username) throws EntityNotFoundException;
    User getUserByName(String username) throws EntityNotFoundException;
    User getUserById(Long id) throws EntityNotFoundException;
}
