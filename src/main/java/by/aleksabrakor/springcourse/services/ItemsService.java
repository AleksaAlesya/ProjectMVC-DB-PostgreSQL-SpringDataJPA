package by.aleksabrakor.springcourse.services;

import by.aleksabrakor.springcourse.models.Item;
import by.aleksabrakor.springcourse.models.Person;
import by.aleksabrakor.springcourse.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemsService {
    private  final ItemsRepository itemsRepository;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> findByTitleItem(String titleItem){
        return itemsRepository.findByTitleItem(titleItem);
    }

    public List<Item> findByOwner(Person owner){
        return itemsRepository.findByOwner(owner);
    }

}
