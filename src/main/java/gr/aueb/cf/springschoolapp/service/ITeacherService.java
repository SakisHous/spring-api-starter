package gr.aueb.cf.springschoolapp.service;


import gr.aueb.cf.springschoolapp.dto.teacherdto.TeacherInsertDTO;
import gr.aueb.cf.springschoolapp.dto.teacherdto.TeacherUpdateDTO;
import gr.aueb.cf.springschoolapp.model.Teacher;
import gr.aueb.cf.springschoolapp.service.exception.EntityAlreadyExistsException;
import gr.aueb.cf.springschoolapp.service.exception.EntityNotFoundException;
import gr.aueb.cf.springschoolapp.service.exception.SQLGenericException;

import java.util.List;

/**
 * This interface declares the Public API
 * regarding CRUD operations in {@link Teacher}
 * objects for the Service Layer.
 *
 * @author Thanasis Chousiadas
 */
public interface ITeacherService {
    Teacher insertTeacher(TeacherInsertDTO dto) throws EntityAlreadyExistsException, SQLGenericException;
    Teacher updateTeacher(TeacherUpdateDTO dto) throws EntityNotFoundException;
    Teacher deleteTeacher(Long id) throws EntityNotFoundException;
    List<Teacher> getTeachersByLastname(String lastname) throws EntityNotFoundException;
    Teacher getTeacherById(Long id) throws EntityNotFoundException;
}
