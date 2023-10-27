package gr.aueb.cf.springschoolapp.rest;

import gr.aueb.cf.springschoolapp.dto.teacherdto.TeacherInsertDTO;
import gr.aueb.cf.springschoolapp.dto.teacherdto.TeacherReadOnlyDTO;
import gr.aueb.cf.springschoolapp.dto.teacherdto.TeacherUpdateDTO;
import gr.aueb.cf.springschoolapp.model.Teacher;
import gr.aueb.cf.springschoolapp.service.ITeacherService;
import gr.aueb.cf.springschoolapp.service.exception.EntityNotFoundException;
import gr.aueb.cf.springschoolapp.validator.TeacherInsertValidator;
import gr.aueb.cf.springschoolapp.validator.TeacherUpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Teachers controller class.
 * Handles API calls (requests) and
 * manages the responses.
 *
 * @author Thanasis Chousiadas
 */
@RestController
@RequestMapping("/api")
public class TeacherRestResource {

    private final ITeacherService teacherService;
    private final TeacherInsertValidator teacherInsertValidator;
    private final TeacherUpdateValidator teacherUpdateValidator;

    @Autowired
    public TeacherRestResource(
            ITeacherService teacherService,
            TeacherInsertValidator teacherInsertValidator,
            TeacherUpdateValidator teacherUpdateValidator) {
        this.teacherService = teacherService;
        this.teacherInsertValidator = teacherInsertValidator;
        this.teacherUpdateValidator = teacherUpdateValidator;
    }

    @RequestMapping(path = "/teachers", method = RequestMethod.GET)
    public ResponseEntity<List<TeacherReadOnlyDTO>> getTeacherByLastname(
            @RequestParam("lastname") String lastname) {
        try {
            List<Teacher> teachers = teacherService.getTeachersByLastname(lastname);
            List<TeacherReadOnlyDTO> readOnlyDTOS = new ArrayList<>();
            for (Teacher teacher : teachers) {
                readOnlyDTOS.add(mapFrom(teacher));
            }
            return new ResponseEntity<>(readOnlyDTOS, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/teachers/{id}", method = RequestMethod.GET)
    public ResponseEntity<TeacherReadOnlyDTO> getTeacher(@PathVariable("id") long id) {
        try {
            Teacher teacher = teacherService.getTeacherById(id);
            TeacherReadOnlyDTO dto = mapFrom(teacher);

            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/teachers", method = RequestMethod.POST)
    public ResponseEntity<TeacherReadOnlyDTO> addTeacher(
            @RequestBody TeacherInsertDTO dto,
            BindingResult bindingResult) {
        teacherInsertValidator.validate(dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Teacher teacher = teacherService.insertTeacher(dto);

            TeacherReadOnlyDTO teacherReadOnly = mapFrom(teacher);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(teacher.getId())
                    .toUri();
            return ResponseEntity.created(location).body(teacherReadOnly);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/teachers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TeacherReadOnlyDTO> deleteTeacher(@PathVariable("id") long id) {
        try {
            Teacher teacher = teacherService.deleteTeacher(id);
            return new ResponseEntity<>(mapFrom(teacher), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/teachers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TeacherReadOnlyDTO> updateTeacher(
            @PathVariable("id") Long id,
            @Valid @RequestBody TeacherUpdateDTO dto,
            BindingResult bindingResult) {
        if (!Objects.equals(id, dto.getId())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        teacherUpdateValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Teacher teacher = teacherService.updateTeacher(dto);

            return new ResponseEntity<>(mapFrom(teacher), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method maps a {@link Teacher} object to
     * {@link TeacherReadOnlyDTO} object.\
     *
     * @param teacher a {@link Teacher} object.
     * @return a {@link TeacherReadOnlyDTO} object.
     */
    private TeacherReadOnlyDTO mapFrom(Teacher teacher) {
        return new TeacherReadOnlyDTO(
                teacher.getId(),
                teacher.getSsn(),
                teacher.getFirstname(),
                teacher.getLastname(),
                teacher.getSpeciality().getSpecialityName(),
                teacher.getUser().getUsername()
        );
    }
}
