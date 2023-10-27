package gr.aueb.cf.springschoolapp.service;


import gr.aueb.cf.springschoolapp.dto.studentdto.StudentInsertDTO;
import gr.aueb.cf.springschoolapp.dto.studentdto.StudentUpdateDTO;
import gr.aueb.cf.springschoolapp.model.Student;
import gr.aueb.cf.springschoolapp.service.exception.EntityNotFoundException;
import gr.aueb.cf.springschoolapp.service.exception.SQLGenericException;

import java.text.ParseException;
import java.util.List;

/**
 * This interface declares the Public API
 * regarding CRUD operations in {@link Student}
 * objects for the Service Layer.
 *
 * @author Thanasis Chousiadas
 */
public interface IStudentService {
    Student insertStudent(StudentInsertDTO dto) throws SQLGenericException, ParseException;
    Student updateStudent(StudentUpdateDTO dto) throws ParseException, EntityNotFoundException;
    Student deleteStudent(Long id) throws EntityNotFoundException;
    List<Student> getStudentsByLastname(String lastname) throws EntityNotFoundException;
    Student getStudentById(Long id) throws EntityNotFoundException;
}
