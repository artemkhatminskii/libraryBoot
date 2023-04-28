package com.example.libraryBoot.dao;

import com.example.libraryBoot.models.Book;
import com.example.libraryBoot.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PersonDAO {
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public PersonDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Person> index() {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select p from Person p", Person.class).getResultList();
//    }
//
//    @Transactional(readOnly = true)
//    public Person show(int person_id) {
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.get(Person.class, person_id);
//    }
//
//    @Transactional(readOnly = true)
//    public List<Book> showBookByPersonId(int person_id) {
//        Session session = sessionFactory.getCurrentSession();
//        Person person = session.get(Person.class, person_id);
//        System.out.println(person.getBooks());
//        return person.getBooks();
//    }
//
//    @Transactional
//    public void save(Person person) {
//        Session session = sessionFactory.getCurrentSession();
//        session.save(person);
//    }
//
//    @Transactional
//    public void update(int person_id, Person updatedPerson) {
//        Session session = sessionFactory.getCurrentSession();
//        Person personToBeUpdated = session.get(Person.class, person_id);
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setYear(updatedPerson.getYear());
//    }
//
//    @Transactional
//    public void delete(int person_id) {
//        Session session = sessionFactory.getCurrentSession();
//        session.remove(session.get(Person.class, person_id));
//    }
}
