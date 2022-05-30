package com.matveypenkov.spring.controller;

import com.matveypenkov.spring.service.RelativesService;
import com.matveypenkov.spring.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;


import javax.validation.Valid;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class MainController {

    Person emptyPerson = new Person("", "", "", "", "01.01.1900", 0, "", 0, 0, 0, "");

    @Autowired
    private RelativesService relativesService;

    @RequestMapping("/")
    public String showAllRelatives(Model model) {
        List<Person> allRelatives = relativesService.getAllRelatives();
        model.addAttribute("allRelatives", allRelatives);
        return "all-relatives";
    }

    @RequestMapping("/addNewRelative")
    public String addNewRelative(Model model) {
        Person person = new Person();
        model.addAttribute("relative", person);
        return "relative-info";
    }

    @RequestMapping("/saveRelative")
    public String saveRelative(@Valid @ModelAttribute("relative") Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "relative-info";
        } else {
            fillEmptyPersonFields(person);
            relativesService.saveRelative(person);
            return "redirect:/";
        }
    }

    @RequestMapping("/updateRelative")
    public String updateRelative(@RequestParam("relativeId") int id, Model model) {
        Person person = relativesService.getRelative(id);
        model.addAttribute("relative", person);
        return "relative-info";
    }

    @RequestMapping("/deleteRelative")
    public String deleteRelative(@RequestParam("relativeId") int id) {
        relativesService.deleteRelative(id);
        return "redirect:/";
    }

    @RequestMapping("/bioRelative")
    public String bioRelative(@RequestParam("relativeId") int id, Model model) {
        Person person = relativesService.getRelative(id);
        model.addAttribute("relative", person);
        Person mother;
        Person father;
        try {
            mother = relativesService.getRelative(person.getMotherID());
            father = relativesService.getRelative(person.getFatherID());
            model.addAttribute("mother", mother);
            model.addAttribute("father", father);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mother = emptyPerson;
            father = emptyPerson;
        }
        return "relative-bio";
    }

    @RequestMapping("/goParseRelative")
    public String goParseRelative(Model model) {
        Person person = new Person();
        model.addAttribute("info", person);
        return "parsing-page";
    }


    @RequestMapping("/parseRelative")
    public String parseRelative(@ModelAttribute("info") Person person2) {
        //Стиль записи для парсинга
        //      Фамилия1%%Имя1%%Отчество1%%Город%%ДД.ММ.ГГГГ%%Биография//Фамилия1%%Имя1%%....       без знаков // и %%
        String data = person2.getBio();
        String[] personStrings = data.split("//");
        for (String str : personStrings) {
            Person person=new Person("", "", "", "", "01.01.1900", 0, "", 0, 0, 0, "");
            String[] personInfoStrings = str.split("%%");
            if(personInfoStrings.length==6){
                person.setSurname(personInfoStrings[0]);
                person.setName(personInfoStrings[1]);
                person.setMiddleName(personInfoStrings[2]);
                person.setCity(personInfoStrings[3]);
                if (personInfoStrings[4].matches("\\d{2}\\.\\d{2}\\.\\d{4}")){
                    person.setDateOfBirth(personInfoStrings[4]);
                }
                person.setBio(personInfoStrings[5]);
                relativesService.saveRelative(person);
                System.out.println("-----------------------------------------------");
                System.out.println("Parsing done! Person added:"+person.toString());
            }
        }
        return "redirect:/";
    }


    public Person fillEmptyPersonFields(Person person) {
        if (person.getCity() == null || person.getCity() == "") {
            person.setCity("---");
        }
        if (person.getMiddleName() == null || person.getMiddleName() == "") {
            person.setMiddleName("---");
        }
        if (person.getBio() == null || person.getBio() == ""|| person.getBio() == "...") {
            person.setBio("Информации в базе не найдено.");
        }
        return person;
    }

}
