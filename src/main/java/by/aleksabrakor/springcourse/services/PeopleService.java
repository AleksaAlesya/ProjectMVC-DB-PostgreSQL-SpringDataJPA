package by.aleksabrakor.springcourse.services;

import by.aleksabrakor.springcourse.models.Mood;
import by.aleksabrakor.springcourse.models.Person;
import by.aleksabrakor.springcourse.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository personRepository) {
        this.peopleRepository = personRepository;
    }


    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(int personId) {

        return peopleRepository.findById(personId).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        person.setCreatedAt(new Date());
        person.setMood(Mood.CALM);
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Person personUpdate, int id) {
        personUpdate.setId(id);
        peopleRepository.save(personUpdate);
    }

    @Transactional
    public void deleteById(int id) {
        peopleRepository.deleteById(id);
    }

    public Optional<Person> findByEmail(String email, int id) {
        return peopleRepository.findByEmailAndIdNot(email, id).stream().findAny();
    }

    public List<Person> findByName(String name) {
        return peopleRepository.findByName(name);
    }

    ;

    public List<Person> findByNameOrderByAge(String name) {
        return peopleRepository.findByNameOrderByAge(name);
    }

    ;

    public List<Person> findByEmail(String email) {
        return peopleRepository.findByEmail(email);
    }

    ;

    public List<Person> findByNameStartingWith(String nameStartingWith) {
        return peopleRepository.findByNameStartingWith(nameStartingWith);
    }

    ;

    List<Person> findByNameOrEmail(String name, String email) {
        return peopleRepository.findByNameOrEmail(name, email);
    }

    ;

    public void test() {
        System.out.println("Testing here with debug");
    }
}
