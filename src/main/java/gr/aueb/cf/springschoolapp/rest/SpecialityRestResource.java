package gr.aueb.cf.springschoolapp.rest;

import gr.aueb.cf.springschoolapp.dto.specialitydto.SpecialityInsertDTO;
import gr.aueb.cf.springschoolapp.dto.specialitydto.SpecialityReadOnlyDTO;
import gr.aueb.cf.springschoolapp.dto.specialitydto.SpecialityUpdateDTO;
import gr.aueb.cf.springschoolapp.model.Speciality;
import gr.aueb.cf.springschoolapp.service.ISpecialityService;
import gr.aueb.cf.springschoolapp.service.exception.EntityAlreadyExistsException;
import gr.aueb.cf.springschoolapp.service.exception.EntityNotFoundException;
import gr.aueb.cf.springschoolapp.service.exception.SQLGenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Specialities controller class.
 * Handles API calls (requests) and
 * manages the responses.
 *
 * @author Thanasis Chousiadas
 */
@RestController
@RequestMapping("/api")
public class SpecialityRestResource {
    private final ISpecialityService specialityService;

    @Autowired
    public SpecialityRestResource(ISpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @RequestMapping(path = "/specialities/{id}", method = RequestMethod.GET)
    public ResponseEntity<SpecialityReadOnlyDTO> getSpeciality(@PathVariable("id") long id) {
        try {
            Speciality speciality = specialityService.getSpecialityById(id);

            SpecialityReadOnlyDTO dto = mapFrom(speciality);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/specialities", method = RequestMethod.GET)
    public ResponseEntity<List<SpecialityReadOnlyDTO>> getAllSpecialities() {
        try {
            List<Speciality> specialities = specialityService.getAllSpecialities();

            List<SpecialityReadOnlyDTO> readOnlyDTOS = new ArrayList<>();
            for (Speciality speciality : specialities) {
                readOnlyDTOS.add(mapFrom(speciality));
            }
            return new ResponseEntity<>(readOnlyDTOS, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/specialities", method = RequestMethod.POST)
    public ResponseEntity<SpecialityReadOnlyDTO> addSpeciality(@RequestBody SpecialityInsertDTO dto) {
        try {
            Speciality speciality = specialityService.insertSpeciality(dto);
            SpecialityReadOnlyDTO specialityReadOnly = mapFrom(speciality);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(speciality.getId())
                    .toUri();
            return ResponseEntity.created(location).body(specialityReadOnly);
        } catch (EntityAlreadyExistsException | SQLGenericException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/specialities/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SpecialityReadOnlyDTO> deleteSpeciality(@PathVariable("id") long id) {
        try {
            Speciality speciality = specialityService.getSpecialityById(id);
            specialityService.deleteSpeciality(id);
            SpecialityReadOnlyDTO readOnlyDTO = mapFrom(speciality);
            return new ResponseEntity<>(readOnlyDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/specialities", method = RequestMethod.PUT)
    public ResponseEntity<SpecialityReadOnlyDTO> updateSpeciality(@RequestBody SpecialityUpdateDTO dto) {
        try {
            Speciality speciality = specialityService.updateSpeciality(dto);
            return new ResponseEntity<>(mapFrom(speciality), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method maps a {@link Speciality} object to
     * {@link SpecialityReadOnlyDTO} object.
     *
     * @param speciality a {@link Speciality} object.
     * @return a {@link SpecialityReadOnlyDTO} object.
     */
    private SpecialityReadOnlyDTO mapFrom(Speciality speciality) {
        return new SpecialityReadOnlyDTO(speciality.getId(), speciality.getSpecialityName());
    }
}
