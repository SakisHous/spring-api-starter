package gr.aueb.cf.springschoolapp.repository;

import gr.aueb.cf.springschoolapp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher getTeacherBySsn(String ssn);
    Teacher getById(Long id);
    List<Teacher> getTeachersByLastnameStartingWith(String lastname);
}
