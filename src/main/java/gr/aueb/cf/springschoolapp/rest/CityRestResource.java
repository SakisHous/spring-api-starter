package gr.aueb.cf.springschoolapp.rest;

import gr.aueb.cf.springschoolapp.dto.citydto.CityInsertDTO;
import gr.aueb.cf.springschoolapp.dto.citydto.CityReadOnlyDTO;
import gr.aueb.cf.springschoolapp.dto.citydto.CityUpdateDTO;
import gr.aueb.cf.springschoolapp.model.City;
import gr.aueb.cf.springschoolapp.service.ICityService;
import gr.aueb.cf.springschoolapp.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Cities controller class.
 * Handles API calls (requests) and
 * manages the responses.
 *
 * @author Thanasis Chousiadas
 */
@RestController
@RequestMapping("/api")
public class CityRestResource {

    private final ICityService cityService;

    @Autowired
    public CityRestResource(ICityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping(path = "/cities/{id}", method = RequestMethod.GET)
    public ResponseEntity<CityReadOnlyDTO> getCity(@PathVariable("id") long id) {
        try {
            City city = cityService.getCityById(id);
            CityReadOnlyDTO cityReadOnly = mapFrom(city);

            return new ResponseEntity<>(cityReadOnly, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/cities", method = RequestMethod.GET)
    public ResponseEntity<List<CityReadOnlyDTO>> getAllCities() {
        try {
            List<City> cities = cityService.getAllCities();

            List<CityReadOnlyDTO> citiesReadOnly = new ArrayList<>();
            for (City city : cities) {
                citiesReadOnly.add(mapFrom(city));
            }
            return new ResponseEntity<>(citiesReadOnly, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/cities", method = RequestMethod.POST)
    public ResponseEntity<CityReadOnlyDTO> addCity(@RequestBody CityInsertDTO dto) {
        try {
            City city = cityService.insertCity(dto);
            CityReadOnlyDTO cityReadOnly = mapFrom(city);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(city.getId())
                    .toUri();
            return ResponseEntity.created(location).body(cityReadOnly);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/cities/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CityReadOnlyDTO> deleteCity(@PathVariable("id") long id) {
        try {
            City city = cityService.deleteCity(id);
            return new ResponseEntity<>(mapFrom(city), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/cities", method = RequestMethod.PUT)
    public ResponseEntity<CityReadOnlyDTO> updateCity(@RequestBody CityUpdateDTO dto) {
        try {
            City city = cityService.updateCity(dto);
            return new ResponseEntity<>(mapFrom(city), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private CityReadOnlyDTO mapFrom(City city) {
        return new CityReadOnlyDTO(city.getId(), city.getCityName());
    }
}
