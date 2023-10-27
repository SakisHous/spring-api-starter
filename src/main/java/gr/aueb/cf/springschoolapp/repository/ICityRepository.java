package gr.aueb.cf.springschoolapp.repository;

import gr.aueb.cf.springschoolapp.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityRepository extends JpaRepository<City, Long> {
    City getById(Long id);
    City getCityByCityName(String cityName);
}
