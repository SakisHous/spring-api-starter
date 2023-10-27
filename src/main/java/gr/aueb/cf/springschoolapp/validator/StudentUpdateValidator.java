package gr.aueb.cf.springschoolapp.validator;

import gr.aueb.cf.springschoolapp.dto.studentdto.StudentUpdateDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StudentUpdateValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return StudentUpdateDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudentUpdateDTO insertDTO = (StudentUpdateDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "empty");
        if (insertDTO.getFirstname().length() < 3 || insertDTO.getFirstname().length() > 50) {
            errors.rejectValue("firstname", "size");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "empty");
        if (insertDTO.getLastname().length() < 3 || insertDTO.getLastname().length() > 48) {
            errors.rejectValue("lastname", "size");
        }
    }
}
