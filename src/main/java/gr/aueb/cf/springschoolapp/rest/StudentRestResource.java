package gr.aueb.cf.springschoolapp.rest;

import gr.aueb.cf.springschoolapp.dto.studentdto.StudentInsertDTO;
import gr.aueb.cf.springschoolapp.dto.studentdto.StudentReadOnlyDTO;
import gr.aueb.cf.springschoolapp.dto.studentdto.StudentUpdateDTO;
import gr.aueb.cf.springschoolapp.model.Student;
import gr.aueb.cf.springschoolapp.service.IStudentService;
import gr.aueb.cf.springschoolapp.service.exception.EntityNotFoundException;
import gr.aueb.cf.springschoolapp.service.util.DateUtil;
import gr.aueb.cf.springschoolapp.validator.StudentInsertValidator;
import gr.aueb.cf.springschoolapp.validator.StudentUpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Students controller class.
 * Handles API calls (requests) and
 * manages the responses.
 *
 * @author Thanasis Chousiadas
 */
@RestController
@RequestMapping("/api")
public class StudentRestResource {

    private final IStudentService studentService;
    private final StudentInsertValidator studentInsertValidator;
    private final StudentUpdateValidator studentUpdateValidator;

    @Autowired
    public StudentRestResource(
            IStudentService studentService,
            StudentInsertValidator studentInsertValidator,
            StudentUpdateValidator studentUpdateValidator) {
        this.studentService = studentService;
        this.studentInsertValidator = studentInsertValidator;
        this.studentUpdateValidator = studentUpdateValidator;
    }

    @RequestMapping(path = "/students", method = RequestMethod.GET)
    public ResponseEntity<List<StudentReadOnlyDTO>> getStudentByLastname(
            @RequestParam("lastname") String lastname) {
        try {
            List<Student> students = studentService.getStudentsByLastname(lastname);

            List<StudentReadOnlyDTO> readOnlyDTOS = new ArrayList<>();
            for (Student student : students) {
                readOnlyDTOS.add(mapFrom(student));
            }
            return new ResponseEntity<>(readOnlyDTOS, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/students/{id}", method = RequestMethod.GET)
    public ResponseEntity<StudentReadOnlyDTO> getTeacher(@PathVariable("id") long id) {
        try {
            Student student = studentService.getStudentById(id);
            StudentReadOnlyDTO dto = mapFrom(student);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/students", method = RequestMethod.POST)
    public ResponseEntity<StudentReadOnlyDTO> addStudent(
            @RequestBody StudentInsertDTO dto,
            BindingResult bindingResult) {
        studentInsertValidator.validate(dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Student student = studentService.insertStudent(dto);

            StudentReadOnlyDTO studentReadOnly = mapFrom(student);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(student.getId())
                    .toUri();
            return ResponseEntity.created(location).body(studentReadOnly);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/students/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StudentReadOnlyDTO> deleteStudent(@PathVariable("id") long id) {
        try {
            Student student = studentService.deleteStudent(id);
            return new ResponseEntity<>(mapFrom(student), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/students/{id}", method = RequestMethod.PUT)
    public ResponseEntity<StudentReadOnlyDTO> updateStudent(
            @PathVariable("id") Long id,
            @RequestBody StudentUpdateDTO dto,
            BindingResult bindingResult) {
        if (!Objects.equals(id, dto.getId())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        studentUpdateValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            Student student = studentService.updateStudent(dto);
            return new ResponseEntity<>(mapFrom(student), HttpStatus.OK);
        } catch (EntityNotFoundException | ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method maps a {@link Student} object to
     * {@link StudentReadOnlyDTO} object.
     *
     * @param student a {@link Student} object.
     * @return a {@link StudentReadOnlyDTO} object
     */
    private StudentReadOnlyDTO mapFrom(Student student) {
        return new StudentReadOnlyDTO(
                student.getId(),
                student.getFirstname(),
                student.getLastname(),
                student.getGender().getLabel(),
                DateUtil.toString(student.getBirthDate()),
                student.getCity().getCityName(),
                student.getUser().getUsername()
        );
    }
}
