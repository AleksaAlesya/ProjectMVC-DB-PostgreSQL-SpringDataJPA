package by.aleksabrakor.springcourse.repositories;

import by.aleksabrakor.springcourse.models.Item;
import by.aleksabrakor.springcourse.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {
    List<Item> findByTitleItem(String titleItem);

    List<Item> findByOwner(Person owner);
}
