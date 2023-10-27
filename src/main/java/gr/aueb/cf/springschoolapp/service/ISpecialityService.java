package gr.aueb.cf.springschoolapp.service;


import gr.aueb.cf.springschoolapp.dto.specialitydto.SpecialityInsertDTO;
import gr.aueb.cf.springschoolapp.dto.specialitydto.SpecialityUpdateDTO;
import gr.aueb.cf.springschoolapp.model.Speciality;
import gr.aueb.cf.springschoolapp.service.exception.EntityAlreadyExistsException;
import gr.aueb.cf.springschoolapp.service.exception.EntityNotFoundException;
import gr.aueb.cf.springschoolapp.service.exception.SQLGenericException;

import java.util.List;

/**
 * This interface declares the Public API
 * regarding CRUD operations in {@link Speciality}
 * objects for the Service Layer.
 *
 * @author Thanasis Chousiadas
 */
public interface ISpecialityService {
    Speciality insertSpeciality(SpecialityInsertDTO dto) throws EntityAlreadyExistsException, SQLGenericException;
    Speciality updateSpeciality(SpecialityUpdateDTO dto) throws EntityNotFoundException;
    Speciality deleteSpeciality(Long id) throws EntityNotFoundException;
    Speciality getSpecialityById(Long id) throws EntityNotFoundException;
    Speciality getSpecialityByName(String specialityName) throws EntityNotFoundException;
    List<Speciality> getAllSpecialities() throws EntityNotFoundException;
}
