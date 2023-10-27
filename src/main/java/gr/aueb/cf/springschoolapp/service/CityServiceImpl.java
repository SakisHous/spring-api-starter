package gr.aueb.cf.springschoolapp.service;

import gr.aueb.cf.springschoolapp.dto.citydto.CityInsertDTO;
import gr.aueb.cf.springschoolapp.dto.citydto.CityUpdateDTO;
import gr.aueb.cf.springschoolapp.model.City;
import gr.aueb.cf.springschoolapp.repository.ICityRepository;
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
 * of the {@link ICityService} interface, for
 * the Service Layer of this application.
 * It implements services for CRUD operations in
 * {@link City} objects.
 *
 * @author Thanasis Chousiadas
 */
@Service
@Slf4j
public class CityServiceImpl implements ICityService {

    private final ICityRepository cityRepository;

    @Autowired
    public CityServiceImpl(ICityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    /**
     * This method inserts a new city in the database.
     *
     * @param dto the Data Transfer Object with the data for insert the record.
     * @return the inserted {@link City} entity.
     * @throws EntityAlreadyExistsException handles insert error where the city
     *                                      already exists.
     * @throws SQLGenericException          handles generic errors during database
     *                                      operations.
     */
    @Transactional
    @Override
    public City insertCity(CityInsertDTO dto) throws Exception {
        City city = new City();

        try {
            if (cityRepository.getCityByCityName(dto.getCity()) != null) {
                throw new Exception("City with name " + dto.getCity() + " already exists");
            }
            city.setCityName(dto.getCity());
            city = cityRepository.save(city);

            if (city.getId() == null) {
                throw new Exception("Invalid insertion");
            }
        } catch (Exception e) {
            log.info("Insert Exception");
            throw e;
        }
        return city;
    }

    /**
     * This method updates an old city with a new one.
     *
     * @param dto the Data Transfer Object with the data for update the record.
     * @return the updated {@link City} entity.
     * @throws EntityNotFoundException handles update error where the city
     *                                 does not exist.
     */
    @Override
    public City updateCity(CityUpdateDTO dto) throws EntityNotFoundException {
        City city;
        City updatedCity;
        try {
            city = cityRepository.getById(dto.getId());

            if (city == null) {
                throw new EntityNotFoundException(City.class, dto.getId());
            }

            updatedCity = cityRepository.save(convertUpdateDTO(dto));
        } catch (EntityNotFoundException e) {
            log.info("Update Exception");
            throw e;
        }
        return updatedCity;
    }

    /**
     * This method deletes a city from the database.
     *
     * @param id the primary key of the city.
     * @throws EntityNotFoundException handles delete error where the city
     *                                 does not exist.
     */
    @Override
    public City deleteCity(Long id) throws EntityNotFoundException {
        City city;
        try {
            city = cityRepository.getById(id);
            if (city == null) {
                throw new EntityNotFoundException(City.class, id);
            }
            cityRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.info("[Error]: Delete city with id = " + id);
            throw e;
        }
        return city;
    }

    /**
     * This method searches for a specific city with an id given
     * by the user and returns this city.
     *
     * @param id the primary key of the city.
     * @return a {@link City} object.
     * @throws EntityNotFoundException if the city does not exist, thrown this
     *                                 exception.
     */
    @Override
    public City getCityById(Long id) throws EntityNotFoundException {
        City city;
        try {
            city = cityRepository.getById(id);
            if (city == null) {
                throw new EntityNotFoundException(City.class, id);
            }
        } catch (EntityNotFoundException e) {
            log.info("[Error]: Get City with id = " + id);
            throw e;
        }
        return city;
    }

    /**
     * Returns a city object given the name of the city.
     *
     * @param cityName city's name.
     * @return a {@link City} object.
     * @throws EntityNotFoundException handles update error where the city
     *                                 does not exist.
     */
    @Override
    public City getCityByName(String cityName) throws EntityNotFoundException {
        City city;
        try {
            city = cityRepository.getCityByCityName(cityName);
            if (city == null) {
                throw new EntityNotFoundException(City.class, 0L);
            }
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        return city;
    }

    /**
     * This method return all the cities in the database.
     *
     * @return an {@link java.util.ArrayList} with {@link City} objects.
     * @throws EntityNotFoundException handles update error where the city
     *                                 does not exist.
     */
    @Override
    public List<City> getAllCities() throws EntityNotFoundException {
        List<City> cities;

        try {
            cities = cityRepository.findAll();
            if (cities.size() == 0) {
                throw new EntityNotFoundException(City.class, 0L);
            }
        } catch (EntityNotFoundException e) {
            log.info("[Error]: Retrieving all Cities from the database.");
            throw e;
        }
        return cities;
    }

    private City convertUpdateDTO(CityUpdateDTO dto) {
        City city = new City();
        city.setId(dto.getId());
        city.setCityName(dto.getCity());
        return city;
    }
}
