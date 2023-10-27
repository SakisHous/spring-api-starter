package gr.aueb.cf.springschoolapp.validator;

import gr.aueb.cf.springschoolapp.dto.studentdto.StudentInsertDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StudentInsertValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return StudentInsertDTO.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudentInsertDTO insertDTO = (StudentInsertDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "empty");
        if (insertDTO.getFirstname().length() < 3 || insertDTO.getFirstname().length() > 48) {
            errors.rejectValue("firstname", "size");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "empty");
        if (insertDTO.getLastname().length() < 3 || insertDTO.getLastname().length() > 50) {
            errors.rejectValue("lastname", "size");
        }
    }
}
