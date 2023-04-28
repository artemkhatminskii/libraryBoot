package com.example.libraryBoot.Services;

import com.example.libraryBoot.models.Book;
import com.example.libraryBoot.models.Person;
import com.example.libraryBoot.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setPerson_id(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Book> showBookByPersonId(int person_id) {
        Optional<Person> foundPerson = peopleRepository.findById(person_id);

        if (foundPerson.isPresent()) {
            Hibernate.initialize(foundPerson.get().getBooks());

            foundPerson.get().getBooks().forEach(book -> {
                long diffInMillies = Math.abs(book.getDate().getTime() - new Date().getTime());
                if (diffInMillies > 864000000)
                    book.setExpired(true);
            });

            return foundPerson.get().getBooks();
        }
        else return Collections.emptyList();

    }

}
