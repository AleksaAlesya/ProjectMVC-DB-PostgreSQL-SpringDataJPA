package by.aleksabrakor.springcourse.dao;

import by.aleksabrakor.springcourse.models.Item;
import by.aleksabrakor.springcourse.models.Person;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//Dao класс может использоваться для сложных запросов, если в data jpa нет кастомных для нашей реализации
// здесь неактуальные методы Hibernate, использовались до внедрения SpringDataJpa
@Component
public class PersonDao {
    private final EntityManager entityManager;

    @Autowired
    public PersonDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional(readOnly = true) // помечаем, если данные не изменяют, только читают
    public List<Person> index() {
        Session session = entityManager.unwrap(Session.class);

        return session.createQuery("select p from Person p", Person.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Person getById(int personId) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Person.class, personId);
    }

    @Transactional
    public void save(Person person) {
        Session session = entityManager.unwrap(Session.class);
        session.save(person);
    }

    @Transactional
    public void update(Person personUpdate, int id) {
        Session session = entityManager.unwrap(Session.class);
        Person person = session.get(Person.class, id);
        person.setName(personUpdate.getName());
        person.setAge(personUpdate.getAge());
        person.setEmail(personUpdate.getEmail());
        person.setAddress(personUpdate.getAddress());
    }

    @Transactional
    public void deleteById(int id) {
        Session session = entityManager.unwrap(Session.class);
        session.remove(session.get(Person.class, id));
    }

    @Transactional
    public Optional<Person> findByEmail(String email, int id) {
        Session session = entityManager.unwrap(Session.class);

        return session.createQuery("select p from Person p WHERE p.email = '" + email + "' AND p.id <> " + id, Person.class)
                .stream().findAny();
    }

    //Метод создан для тестирования проблемы N+1, можно протестить в  контроллере в методе index
    @Transactional(readOnly = true)
    public void  testProblemNPlus1(){
        Session session = entityManager.unwrap(Session.class);

//        //1 запрос
//        List<Person> people = session.createQuery("select p from Person p", Person.class)
//                .getResultList();
//
//        //N запросов к БД, т.е. б. множество отдельных запросов
//        for (Person person : people) {
//            System.out.println(person.getName() + " " + person.getItems());
//
//        }

        //решение проблемы N+1
        //SQL : A left join B
        // будет 1 запрос к БД и достанет всю инфу, которая нужна
        List<Person> people = session.createQuery("select p from Person p LEFT JOIN FETCH p.items", Person.class)
                .getResultList();

        for (Person person : people) {
            System.out.println(person.getName() + " " + person.getItems());

        }
    }
}
