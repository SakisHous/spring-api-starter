package gr.aueb.cf.springschoolapp.rest;

import gr.aueb.cf.springschoolapp.dto.userdto.UserReadOnlyDTO;
import gr.aueb.cf.springschoolapp.dto.userdto.UserRegisterDTO;
import gr.aueb.cf.springschoolapp.dto.userdto.UserUpdateDTO;
import gr.aueb.cf.springschoolapp.model.User;
import gr.aueb.cf.springschoolapp.service.IUserService;
import gr.aueb.cf.springschoolapp.service.exception.EntityAlreadyExistsException;
import gr.aueb.cf.springschoolapp.service.exception.EntityNotFoundException;
import gr.aueb.cf.springschoolapp.service.exception.SQLGenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
/**
 * Users controller class.
 * Handles API calls (requests) and
 * manages the responses.
 *
 * @author Thanasis Chousiadas
 */
@RestController
@RequestMapping("/api")
public class UserRestResource {

    private final IUserService userService;

    @Autowired
    public UserRestResource(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/users")
    public ResponseEntity<UserReadOnlyDTO> getUserByUsername(@RequestParam("username") String username) {
        try {
            User user = userService.getUserByName(username);
            return new ResponseEntity<>(mapFrom(user), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserReadOnlyDTO> getUser(@PathVariable("id") Long id) {
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(mapFrom(user), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public ResponseEntity<UserReadOnlyDTO> addUser(@RequestBody UserRegisterDTO dto) {
        try {
            User user = userService.insertUser(dto);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();
            return ResponseEntity.created(location).body(mapFrom(user));
        } catch (EntityAlreadyExistsException | SQLGenericException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserReadOnlyDTO> deleteUser(@PathVariable("id") long id) {
        try {
            User user = userService.deleteUser(id);
            return new ResponseEntity<>(mapFrom(user), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/users", method = RequestMethod.PUT)
    public ResponseEntity<UserReadOnlyDTO> updateUser(@RequestBody UserUpdateDTO dto) {
        try {
            User user = userService.updateUser(dto);
            return new ResponseEntity<>(mapFrom(user), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method maps a {@link User} to {@link UserReadOnlyDTO}.
     *
     * @param user a {@link User} object.
     * @return a {@link UserReadOnlyDTO} object.
     */
    private UserReadOnlyDTO mapFrom(User user) {
        return new UserReadOnlyDTO(user.getId(), user.getUsername());
    }
}
