package by.aleksabrakor.springcourse.repositories;

import by.aleksabrakor.springcourse.models.Item;
import by.aleksabrakor.springcourse.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByEmailAndIdNot(String email, int id);
    List<Person> findByName(String name);

    List<Person> findByNameOrderByAge(String name);

    List<Person> findByEmail(String email);

    List<Person> findByNameStartingWith(String nameStartingWith);

    List<Person> findByNameOrEmail(String name, String email);




}
