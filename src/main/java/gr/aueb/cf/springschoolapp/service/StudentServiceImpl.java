package gr.aueb.cf.springschoolapp.service;


import gr.aueb.cf.springschoolapp.dto.studentdto.StudentInsertDTO;
import gr.aueb.cf.springschoolapp.dto.studentdto.StudentUpdateDTO;
import gr.aueb.cf.springschoolapp.model.City;
import gr.aueb.cf.springschoolapp.model.Gender;
import gr.aueb.cf.springschoolapp.model.Student;
import gr.aueb.cf.springschoolapp.model.User;
import gr.aueb.cf.springschoolapp.repository.ICityRepository;
import gr.aueb.cf.springschoolapp.repository.IStudentRepository;
import gr.aueb.cf.springschoolapp.repository.IUserRepository;
import gr.aueb.cf.springschoolapp.service.exception.EntityNotFoundException;
import gr.aueb.cf.springschoolapp.service.exception.SQLGenericException;
import gr.aueb.cf.springschoolapp.service.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;


/**
 * This interface implements the Public API
 * of the {@link IStudentService} interface, for
 * the Service Layer of this application.
 * It implements services for CRUD operations in
 * {@link Student} objects.
 *
 * @author Thanasis Chousiadas
 */
@Service
@Slf4j
public class StudentServiceImpl implements IStudentService {

    private final ICityRepository cityRepository;
    private final IUserRepository userRepository;
    private final IStudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(
            ICityRepository cityRepository,
            IUserRepository userRepository,
            IStudentRepository studentRepository) {
        this.cityRepository = cityRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    /**
     * This method inserts a new student in the database.
     *
     * @param dto the Data Transfer Object with the data for insert the record.
     * @return the inserted {@link Student} entity.
     * @throws SQLGenericException general exception if an error is occurred during
     *                             database operations.
     * @throws ParseException      this exception in occurred while parsing the date
     *                             string to {@link java.util.Date} object for insertion
     *                             in the database.
     */
    @Transactional
    @Override
    public Student insertStudent(StudentInsertDTO dto) throws SQLGenericException, ParseException {
        Student student;
        try {
            student = studentRepository.save(convertInsertDTO(dto));
            if (student.getId() == null) {
                throw new SQLGenericException(Student.class, "[SQL Error]: Inserting student");
            }
        } catch (SQLGenericException | ParseException e) {
            log.info("Error inserting student " + e.getMessage());
            throw e;
        }
        return student;
    }

    /**
     * This method updates an old student record with a new one.
     *
     * @param dto the Data Transfer Object with the data for update the record.
     * @return the updated {@link Student} object.
     * @throws ParseException          this exception in occurred while parsing the date
     *                                 string to {@link java.util.Date} object for insertion
     *                                 in the database.
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Transactional
    @Override
    public Student updateStudent(StudentUpdateDTO dto) throws ParseException, EntityNotFoundException {
        Student student;
        Student updatedStudent;
        try {
            student = studentRepository.getById(dto.getId());
            if (student == null) {
                throw new EntityNotFoundException(Student.class, dto.getId());
            }
            updatedStudent = studentRepository.save(convertUpdateDTO(dto));
        } catch (ParseException | EntityNotFoundException e) {
            log.info("Error updating student: \n" + e.getMessage());
            throw e;
        }
        return updatedStudent;
    }

    /**
     * This method deletes a student with an id given
     * by the user.
     *
     * @param id the id given by the user.
     * @return the deleted {@link Student} entity.
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Transactional
    @Override
    public Student deleteStudent(Long id) throws EntityNotFoundException {
        Student student;
        try {
            student = studentRepository.getById(id);
            if (student == null) {
                throw new EntityNotFoundException(Student.class, id);
            }
            studentRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.info("Error deleting student: \n" + e.getMessage());
            throw e;
        }
        return student;
    }

    /**
     * This method returns the students where their lastname
     * begins with the parameter given by the user.
     *
     * @param lastname the parameter for searching the students' lastname.
     * @return an {@link java.util.ArrayList} with {@link Student} objects.
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Override
    public List<Student> getStudentsByLastname(String lastname) throws EntityNotFoundException {
        List<Student> students;
        try {
            students = studentRepository.getStudentsByLastnameStartingWith(lastname);
            if (students.size() == 0) {
                throw new EntityNotFoundException(Student.class, 0L);

            }

        } catch (EntityNotFoundException e) {
            log.info("Error getting students with lastname starting with: \n" + e.getMessage());
            throw e;
        }
        return students;
    }

    /**
     * Retrieves a {@link Student} object given the id of the
     * student.
     *
     * @param id id the id given by the user.
     * @return a {@link Student} object
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Override
    public Student getStudentById(Long id) throws EntityNotFoundException {
        Student student;
        try {
            student = studentRepository.getById(id);
            if (student == null) {
                throw new EntityNotFoundException(Student.class, id);
            }
        } catch (EntityNotFoundException e) {
            log.info("Error getting student with id: \n" + e.getMessage());
            throw e;
        }
        return student;
    }


    /**
     * Maps {@link StudentInsertDTO} object to {@link Student}
     * object to call DAO layer.
     *
     * @param dto the {@link StudentInsertDTO} object to be inserted.
     * @return {@link Student} entity object.
     * @throws ParseException this exception in occurred while parsing the date
     *                        string to {@link java.util.Date} object for insertion
     *                        in the database.
     */
    private Student convertInsertDTO(StudentInsertDTO dto) throws ParseException {
        Student student = new Student();
        student.setId(null);
        student.setFirstname(dto.getFirstname());
        student.setLastname(dto.getLastname());
        // enum gender
        Gender gender = dto.getGender().equals("M") ? Gender.M : Gender.F;
        student.setGender(gender);
        student.setBirthDate(DateUtil.toDate(dto.getBirthdate()));
        // retrieving city
        City city = cityRepository.getCityByCityName(dto.getCity());
        student.setCity(city);
//        System.out.println(city);
        // retrieving user
        User user = userRepository.findUserByUsernameEquals(dto.getUsername());
        student.setUser(user);
//        System.out.println(user);

//        System.out.println(student);
        return student;
    }

    /**
     * Converts {@link StudentUpdateDTO} object to {@link Student}
     * object to call DAO layer.
     *
     * @param dto the {@link StudentUpdateDTO} object to be updated.
     * @return {@link Student} entity object.
     * @throws ParseException this exception in occurred while parsing the date
     *                        string to {@link java.util.Date} object for insertion
     *                        in the database.
     */
    private Student convertUpdateDTO(StudentUpdateDTO dto) throws ParseException {
        Student student = new Student();

        student.setId(dto.getId());
        student.setFirstname(dto.getFirstname());
        student.setLastname(dto.getLastname());
        // enum gender
        Gender gender = dto.getGender().equals("M") ? Gender.M : Gender.F;
        student.setGender(gender);
        student.setBirthDate(DateUtil.toDate(dto.getBirthdate()));
        // retrieving city
        City city = cityRepository.getCityByCityName(dto.getCity());
        student.setCity(city);

        // retrieving user
        User user = userRepository.findUserByUsernameEquals(dto.getUsername());
        student.setUser(user);

        return student;
    }
}
