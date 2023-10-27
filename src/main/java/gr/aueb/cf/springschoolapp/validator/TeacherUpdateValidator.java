package gr.aueb.cf.springschoolapp.validator;

import gr.aueb.cf.springschoolapp.dto.teacherdto.TeacherUpdateDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TeacherUpdateValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return TeacherUpdateDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        TeacherUpdateDTO updateDTO = (TeacherUpdateDTO) target;

        // check ssn
        if (updateDTO.getSsn().length() != 6) {
            errors.rejectValue("ssn", "length");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "empty");
        if (updateDTO.getFirstname().length() < 3 || updateDTO.getFirstname().length() > 48) {
            errors.rejectValue("firstname", "size");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "empty");
        if (updateDTO.getLastname().length() < 3 || updateDTO.getLastname().length() > 48) {
            errors.rejectValue("lastname", "size");
        }
    }
}
