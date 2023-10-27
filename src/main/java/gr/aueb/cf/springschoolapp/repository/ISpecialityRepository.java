package gr.aueb.cf.springschoolapp.repository;

import gr.aueb.cf.springschoolapp.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISpecialityRepository extends JpaRepository<Speciality, Long> {
    Speciality getById(Long id);
    Speciality getSpecialityBySpecialityName(String specialityName);
}
