package gr.aueb.cf.springschoolapp.validator;

import gr.aueb.cf.springschoolapp.dto.teacherdto.TeacherInsertDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TeacherInsertValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return TeacherInsertDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        TeacherInsertDTO insertDTO = (TeacherInsertDTO) target;

        // check ssn
        if (insertDTO.getSsn().length() != 6) {
            errors.rejectValue("ssn", "length");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "empty");
        if (insertDTO.getFirstname().length() < 3 || insertDTO.getFirstname().length() > 50) {
            errors.rejectValue("firstname", "size");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "empty");
        if (insertDTO.getLastname().length() < 3 || insertDTO.getLastname().length() > 50) {
            errors.rejectValue("lastname", "size");
        }
    }
}