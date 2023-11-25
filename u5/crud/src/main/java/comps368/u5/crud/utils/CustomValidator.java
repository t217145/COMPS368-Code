package comps368.u5.crud.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import comps368.u5.crud.models.Contacts;
import comps368.u5.crud.repos.ContactsRepo;

@Component
public class CustomValidator implements Validator {

    @Autowired
    private ContactsRepo repo;

    @Override
    public boolean supports(Class<?> clazz) {
        return Contacts.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Contacts c = (Contacts) target;

        // Check the gender in M of F only
        if (c.getGender() != 'M' && c.getGender() != 'F') {
            errors.rejectValue("gender", "", "gender must be either 'M' or 'F'");
        }

        // Check if either phone or email is provided
        if ((c.getPhone() == null || c.getPhone().trim().equals(""))
                &&
                (c.getEmail() == null || c.getEmail().trim().equals(""))) {
            errors.reject("", "Either phone or email must be provided");
        }

        // Check is the cName is unique by use repo.customFindByCName
        if (repo.customFindByCName(c.getCName()).isPresent()) {
            errors.rejectValue("cName", "", "[" + c.getCName() + "] already exist!");
        }
    }
}