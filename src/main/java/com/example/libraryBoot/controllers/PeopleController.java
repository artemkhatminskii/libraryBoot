package com.example.libraryBoot.controllers;

import com.example.libraryBoot.Services.PersonService;
import com.example.libraryBoot.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personService.findAll());
        return "people/index";
    }

    @GetMapping("/{person_id}")
    public String show(@PathVariable("person_id") int person_id, Model model) {
        model.addAttribute("person", personService.findOne(person_id));
        model.addAttribute("books", personService.showBookByPersonId(person_id));
        return "people/show";
    }

    @GetMapping("/new_person")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return "people/new";

        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{person_id}/edit")
    public String edit(Model model, @PathVariable("person_id") int person_id) {
        model.addAttribute("person", personService.findOne(person_id));
        return "people/edit";
    }

    @PatchMapping("/{person_id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("person_id") int person_id) {

        if (bindingResult.hasErrors()) return "people/edit";
        personService.update(person_id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{person_id}")
    public String delete(@PathVariable("person_id") int person_id) {
        personService.delete(person_id);
        return "redirect:/people";
    }
}
