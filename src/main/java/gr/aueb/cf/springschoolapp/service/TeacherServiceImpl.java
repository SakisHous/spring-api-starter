package gr.aueb.cf.springschoolapp.service;


import gr.aueb.cf.springschoolapp.dto.teacherdto.TeacherInsertDTO;
import gr.aueb.cf.springschoolapp.dto.teacherdto.TeacherUpdateDTO;
import gr.aueb.cf.springschoolapp.model.Speciality;
import gr.aueb.cf.springschoolapp.model.Teacher;
import gr.aueb.cf.springschoolapp.model.User;
import gr.aueb.cf.springschoolapp.repository.ISpecialityRepository;
import gr.aueb.cf.springschoolapp.repository.ITeacherRepository;
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
 * of the {@link ITeacherService} interface, for
 * the Service Layer of this application.
 * It implements services for CRUD operations in
 * {@link Teacher} objects.
 *
 * @author Thanasis Chousiadas
 */
@Service
@Slf4j
public class TeacherServiceImpl implements ITeacherService {

    private final ISpecialityRepository specialityRepository;
    private final IUserRepository userRepository;
    private final ITeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(
            ISpecialityRepository specialityRepository,
            IUserRepository userRepository,
            ITeacherRepository teacherRepository) {
        this.specialityRepository = specialityRepository;
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
    }

    /**
     * This method inserts a new teacher in the database.
     *
     * @param dto the Data Transfer Object with the data for insert the record.
     * @return the inserted {@link Teacher} entity.
     * @throws EntityAlreadyExistsException this exception is occurred when the record is
     *                                      already exists in the database, based on its
     *                                      unique fields.
     * @throws SQLGenericException          general exception if an error is occurred during
     *                                      database operations.
     */
    @Transactional
    @Override
    public Teacher insertTeacher(TeacherInsertDTO dto) throws EntityAlreadyExistsException, SQLGenericException {
        Teacher teacher;
        try {
            teacher = teacherRepository.getTeacherBySsn(dto.getSsn());

            if (teacher != null) {
                throw new EntityAlreadyExistsException(
                        Teacher.class,
                        "Teacher with SSN " + dto.getSsn() + " already exists");
            }
            teacher = teacherRepository.save(convertInsertDTO(dto));

            if (teacher.getId() == null) {
                throw new SQLGenericException(Teacher.class, "Insert Teacher");
            }
        } catch (EntityAlreadyExistsException | SQLGenericException e) {
            log.info("Error inserting Teacher");
            throw e;
        }
        return teacher;
    }

    /**
     * This method updates an old teacher record with a new one.
     *
     * @param dto the Data Transfer Object with the data for update the record.
     * @return the updated {@link Teacher} object.
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Transactional
    @Override
    public Teacher updateTeacher(TeacherUpdateDTO dto) throws EntityNotFoundException {
        Teacher teacher;
        Teacher updatedTeacher;
        try {
            teacher = teacherRepository.getById(dto.getId());

            if (teacher == null) {
                throw new EntityNotFoundException(Teacher.class, dto.getId());
            }
            updatedTeacher = teacherRepository.save(convertUpdateDTO(dto));
        } catch (EntityNotFoundException e) {
            log.info("Error updating teacher");
            throw e;
        }
        return updatedTeacher;
    }

    /**
     * This method deletes a teacher with an id given
     * by the user.
     *
     * @param id the id given by the user.
     * @return the deleted {@link Teacher} entity.
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Override
    public Teacher deleteTeacher(Long id) throws EntityNotFoundException {
        Teacher teacher;
        try {
            teacher = teacherRepository.getById(id);

            if (teacher == null) {
                throw new EntityNotFoundException(Teacher.class, id);
            }
            teacherRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.info("Error deleting teacher");
            throw e;
        }
        return teacher;
    }

    /**
     * This method returns the teachers where their lastname
     * begins with the parameter given by the client.
     *
     * @param lastname the parameter for searching the teachers' lastname.
     * @return an {@link java.util.ArrayList} with {@link Teacher} objects.
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Override
    public List<Teacher> getTeachersByLastname(String lastname) throws EntityNotFoundException {
        List<Teacher> teachers;

        try {
            teachers = teacherRepository.getTeachersByLastnameStartingWith(lastname);

            if (teachers.size() == 0) {
                throw new EntityNotFoundException(List.class, 0L);
            }

        } catch (EntityNotFoundException e) {
            log.info("Error getting all teachers");
            throw e;
        }
        return teachers;
    }

    /**
     * This method retrieves a teacher with an id given
     * by the user.
     *
     * @param id the id given by the user.
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Override
    public Teacher getTeacherById(Long id) throws EntityNotFoundException {
        Teacher teacher;

        try {
            teacher = teacherRepository.getById(id);
            if (teacher == null) {
                throw new EntityNotFoundException(Teacher.class, id);
            }

        } catch (EntityNotFoundException e) {
            log.error("Error getting teacher with id");
            throw e;
        }

        return teacher;
    }

    /**
     * Maps {@link TeacherInsertDTO} object to {@link Teacher}
     * object to call DAO layer.
     *
     * @param dto the {@link TeacherInsertDTO} object to be inserted.
     * @return {@link Teacher} entity object.
     */
    private Teacher convertInsertDTO(TeacherInsertDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setId(null);
        teacher.setSsn(dto.getSsn());
        teacher.setFirstname(dto.getFirstname());
        teacher.setLastname(dto.getLastname());
        // retrieving speciality object
        Speciality speciality = specialityRepository.getSpecialityBySpecialityName(dto.getSpeciality());
        System.out.println(speciality);

        teacher.setSpeciality(speciality);
        // retrieving user object
        User user = userRepository.findUserByUsernameEquals(dto.getUsername());
        System.out.println(user);

        teacher.setUser(user);
        return teacher;
    }

    /**
     * Maps {@link TeacherUpdateDTO} object to {@link Teacher}
     * object to call DAO layer.
     *
     * @param dto the {@link TeacherUpdateDTO} object for update.
     * @return {@link Teacher} entity object.
     */
    private Teacher convertUpdateDTO(TeacherUpdateDTO dto) {
        Teacher teacher = new Teacher();

        teacher.setId(dto.getId());
        teacher.setSsn(dto.getSsn());
        teacher.setFirstname(dto.getFirstname());
        teacher.setLastname(dto.getLastname());

        // retrieving speciality object
        Speciality speciality = specialityRepository.getSpecialityBySpecialityName(dto.getSpeciality());
        teacher.setSpeciality(speciality);
        // retrieving user object
        User user = userRepository.findUserByUsernameEquals(dto.getUsername());
        teacher.setUser(user);
        System.out.println(teacher);
        return teacher;
    }
}
