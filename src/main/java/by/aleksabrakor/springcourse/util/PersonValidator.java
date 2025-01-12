package by.aleksabrakor.springcourse.util;

import by.aleksabrakor.springcourse.models.Person;
import by.aleksabrakor.springcourse.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (peopleService.findByEmail(person.getEmail(),person.getId()).isPresent()) {
            errors.rejectValue("email", "", "This email is already take");
        }
    }
}
