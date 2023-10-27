package gr.aueb.cf.springschoolapp.service;


import gr.aueb.cf.springschoolapp.dto.specialitydto.SpecialityInsertDTO;
import gr.aueb.cf.springschoolapp.dto.specialitydto.SpecialityUpdateDTO;
import gr.aueb.cf.springschoolapp.model.Speciality;
import gr.aueb.cf.springschoolapp.repository.ISpecialityRepository;
import gr.aueb.cf.springschoolapp.service.exception.EntityAlreadyExistsException;
import gr.aueb.cf.springschoolapp.service.exception.EntityNotFoundException;
import gr.aueb.cf.springschoolapp.service.exception.SQLGenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This interface implements the Public API
 * of the {@link ISpecialityService} interface, for
 * the Service Layer of this application.
 * It implements services for CRUD operations in
 * {@link Speciality} objects.
 *
 * @author Thanasis Chousiadas
 */
@Service
@Slf4j
public class SpecialityServiceImpl implements ISpecialityService {

    private final ISpecialityRepository specialityRepository;

    @Autowired
    public SpecialityServiceImpl(ISpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    /**
     * This method inserts a new speciality in the database.
     *
     * @param dto the Data Transfer Object with the data for insert the record.
     * @return the inserted {@link Speciality} entity.
     * @throws EntityAlreadyExistsException handles duplicate specialities' names
     *                                      to be inserted.
     * @throws SQLGenericException          handles generic errors during database
     *                                      operations.
     */
    @Override
    public Speciality insertSpeciality(SpecialityInsertDTO dto)
            throws EntityAlreadyExistsException, SQLGenericException {
        Speciality speciality;
        try {
            speciality = specialityRepository.getSpecialityBySpecialityName(dto.getSpecialityName());
            if (speciality != null) {
                throw new EntityAlreadyExistsException(
                        Speciality.class,
                        "Speciality with name " + speciality.getSpecialityName() + " already exists");
            }
            speciality = specialityRepository.save(convertInsertDTO(dto));

            if (speciality.getId() == null) {
                throw new SQLGenericException(Speciality.class, "Inserting speciality");
            }

        } catch (EntityAlreadyExistsException | SQLGenericException e) {
            log.info("[Error]: Inserting speciality with name " + dto.getSpecialityName());
            throw e;
        }
        return speciality;
    }

    /**
     * This method updates an old student record with a new one.
     *
     * @param dto the Data Transfer Object with the data for update the record.
     * @return the updated {@link Speciality} object.
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Override
    public Speciality updateSpeciality(SpecialityUpdateDTO dto) throws EntityNotFoundException {
        Speciality speciality;
        Speciality updatedSpeciality;

        try {
            speciality = specialityRepository.getById(dto.getId());

            if (speciality == null) {
                throw new EntityNotFoundException(Speciality.class, dto.getId());
            }

            updatedSpeciality = specialityRepository.save(convertUpdateDTO(dto));
        } catch (EntityNotFoundException e) {
            log.info("Update Exception Error");
            throw e;
        }

        return updatedSpeciality;
    }

    /**
     * This method deletes a student with an id given
     * by the user.
     *
     * @param id the id given by the user.
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Override
    public Speciality deleteSpeciality(Long id) throws EntityNotFoundException {
        Speciality speciality;

        try {
            speciality = specialityRepository.getById(id);

            if (speciality == null) {
                throw new EntityNotFoundException(Speciality.class, id);
            }
            specialityRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            log.info("[Error]: Deleting Speciality with id = " + id);
            throw e;
        }
        return speciality;
    }

    /**
     * This method returns a speciality with a certain id (primary key).
     *
     * @param id the id of the speciality.
     * @return a {@link Speciality} object.
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Override
    public Speciality getSpecialityById(Long id) throws EntityNotFoundException {
        Speciality speciality;
        try {
            speciality = specialityRepository.getById(id);
            if (speciality == null) {
                throw new EntityNotFoundException(Speciality.class, id);
            }

        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        return speciality;
    }

    /**
     * Retrieve a {@link Speciality} object given speciality's name.
     *
     * @param specialityName the speciality's name
     * @return a {@link Speciality} object
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Override
    public Speciality getSpecialityByName(String specialityName) throws EntityNotFoundException {
        Speciality speciality;
        try {
            speciality = specialityRepository.getSpecialityBySpecialityName(specialityName);
            if (speciality == null) {
                throw new EntityNotFoundException(Speciality.class, 0L);
            }
        } catch (EntityNotFoundException e) {
            log.info("[Error]: Get speciality by name");
            throw e;
        }
        return speciality;
    }

    /**
     * Returns all specialities from the database.
     *
     * @return an {@link java.util.ArrayList} with {@link Speciality} objects.
     * @throws EntityNotFoundException an error is occurred where the entity is not
     *                                 found for database operations, such as
     *                                 update, delete and retrieve.
     */
    @Override
    public List<Speciality> getAllSpecialities() throws EntityNotFoundException {
        List<Speciality> specialities;
        try {
            specialities = specialityRepository.findAll();
            if (specialities.size() == 0) {
                throw new EntityNotFoundException(Speciality.class, 0L);
            }
        } catch (EntityNotFoundException e) {
            log.info("[Error]: Retrieving all Cities from the database");
            throw e;
        }
        return specialities;
    }

    private Speciality convertInsertDTO(SpecialityInsertDTO dto) {
        Speciality speciality = new Speciality();
        speciality.setId(null);
        speciality.setSpecialityName(dto.getSpecialityName());
        return speciality;
    }

    private Speciality convertUpdateDTO(SpecialityUpdateDTO dto) {
        Speciality speciality = new Speciality();
        speciality.setId(dto.getId());
        speciality.setSpecialityName(dto.getSpecialityName());
        return speciality;
    }
}
