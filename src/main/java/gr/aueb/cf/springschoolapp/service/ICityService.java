package gr.aueb.cf.springschoolapp.service;


import gr.aueb.cf.springschoolapp.dto.citydto.CityInsertDTO;
import gr.aueb.cf.springschoolapp.dto.citydto.CityUpdateDTO;
import gr.aueb.cf.springschoolapp.model.City;
import gr.aueb.cf.springschoolapp.service.exception.EntityNotFoundException;

import java.util.List;

/**
 * This interface declares the Public API
 * regarding CRUD operations in {@link City}
 * objects for the Service Layer.
 *
 * @author Thanasis Chousiadas
 */
public interface ICityService {
    City insertCity(CityInsertDTO dto) throws Exception;
    City updateCity(CityUpdateDTO dto) throws EntityNotFoundException;
    City deleteCity(Long id) throws EntityNotFoundException;
    City getCityById(Long id) throws EntityNotFoundException;
    City getCityByName(String cityName) throws EntityNotFoundException;
    List<City> getAllCities() throws EntityNotFoundException;
}
