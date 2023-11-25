package comps368.u5.advance.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import comps368.u5.advance.models.Contacts;

@Component
public class CustomValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Contacts.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Contacts c = (Contacts) target;

        if (c.getGender() != 'M' && c.getGender() != 'F') {
            errors.rejectValue("gender", "", "gender must be either 'M' or 'F'");
        }

        if ((c.getPhone() == null || c.getPhone().trim().equals(""))
                &&
                (c.getEmail() == null || c.getEmail().trim().equals(""))) {
            errors.reject("", "Either phone or email must be provided");
        }
    }
}