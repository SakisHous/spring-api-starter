package gr.aueb.cf.springschoolapp.repository;

import gr.aueb.cf.springschoolapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Long> {
    Student getById(Long id);
    List<Student> getStudentsByLastnameStartingWith(String lastname);
}
