package com.example.libraryBoot.dao;

import com.example.libraryBoot.models.Book;
import com.example.libraryBoot.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
//
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public BookDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Book> index() {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select b from Book b", Book.class).getResultList();
//    }
//
//    @Transactional(readOnly = true)
//    public Book show(int book_id) {
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.get(Book.class, book_id);
//    }
//
//    @Transactional(readOnly = true)
//    public Optional<Person> showPerson(int book_id) {
//        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, book_id);
//
//        return Optional.ofNullable(book.getOwner());
//    }
//
//    @Transactional
//    public void save(Book book) {
//        Session session = sessionFactory.getCurrentSession();
//        session.save(book);
//    }
//
//    @Transactional
//    public void update(int book_id, Book updatedBook) {
//        Session session = sessionFactory.getCurrentSession();
//        Book bookToBeUpdated = session.get(Book.class, book_id);
//        bookToBeUpdated.setTitle(updatedBook.getTitle());
//        bookToBeUpdated.setAuthor(updatedBook.getAuthor());
//        bookToBeUpdated.setYear(updatedBook.getYear());
//    }
//
//    @Transactional
//    public void delete(int book_id) {
//        Session session = sessionFactory.getCurrentSession();
//        session.remove(session.get(Book.class, book_id));;
//    }
//
//    @Transactional
//    public void assignPerson(int book_id, Person person) {
//        Session session = sessionFactory.getCurrentSession();
//        Book bookToBeUpdated = session.get(Book.class, book_id);
//
//        bookToBeUpdated.setOwner(person);
//    }
//
//    @Transactional
//    public void releaseBook(int book_id) {
//        Session session = sessionFactory.getCurrentSession();
//        Book bookToBeUpdated = session.get(Book.class, book_id);
//
//        bookToBeUpdated.setOwner(null);
//    }
}
