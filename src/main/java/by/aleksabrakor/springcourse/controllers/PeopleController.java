package by.aleksabrakor.springcourse.controllers;

import by.aleksabrakor.springcourse.dao.PersonDao;
import by.aleksabrakor.springcourse.models.Person;
import by.aleksabrakor.springcourse.services.ItemsService;
import by.aleksabrakor.springcourse.services.PeopleService;
import by.aleksabrakor.springcourse.util.PersonValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    public  final PeopleService peopleService;
    public  final ItemsService itemsService;
    private  final PersonValidator personValidator;

    private  final PersonDao personDao;

    @Autowired
    public PeopleController(PeopleService peopleService, ItemsService itemsService, PersonValidator personValidator, PersonDao personDao) {
        this.peopleService = peopleService;
        this.itemsService = itemsService;
        this.personValidator = personValidator;
        this.personDao = personDao;
    }

    @GetMapping("/{id}")
    public String findById(@NotNull @PathVariable("id") int id,
                           Model model) {
        model.addAttribute("person", peopleService.findById(id));
        return "people/show";
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());

//        //todo это никакого отношения к методу не имеет, используем как точка входа для дебагера, помечаем остановки на этих методах, когда доходит, то можем протестировать весь сервис, нажав на Evaluate expression или  alt+F8, сделать шаг к следующей точки остановки - нажать alt+F9 или Run to cursor
//        itemsService.findByTitleItem("Заказ1");
//        itemsService.findByOwner(peopleService.findById(1));
//        peopleService.test();
//        //

        //todo тестируем проблему N+1
        personDao.testProblemNPlus1();

        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        //валидация на уникальность email
        personValidator.validate(person,bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";
        // в модель уже запишется созданный person c полями
        peopleService.save(person);
        return ("redirect:/people");
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,
                       @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findById(id));
        return ("people/edit");
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        //валидация на уникальность email
        personValidator.validate(person,bindingResult);

        if (bindingResult.hasErrors())
            return "people/edit";
        peopleService.update(person, id);
        return ("redirect:/people");
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.deleteById(id);
        return ("redirect:/people");
    }

    //Пара ключ-значение добавится в модел каждого метода этого контроллера
    @ModelAttribute("message")
    public String message() {
        return "Message for all";
    }
}
