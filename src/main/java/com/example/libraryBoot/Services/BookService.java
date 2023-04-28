package com.example.libraryBoot.Services;

import com.example.libraryBoot.models.Book;
import com.example.libraryBoot.models.Person;
import com.example.libraryBoot.repositories.BooksRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BooksRepository booksRepository;

    @Autowired
    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(Sort.by("year"));
        else
            return booksRepository.findAll();
    }

    public List<Book> findWithPagination(int page, int booksPerPage,
                                         boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        else
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public Book findOne(int book_id) {
        Optional<Book> foundBook = booksRepository.findById(book_id);
        return foundBook.orElse(null);
    }

    public Optional<Person> showPersonByBookId(int book_id) {
        Optional<Book> foundBook = booksRepository.findById(book_id);
        if (foundBook.isPresent()) {
            Hibernate.initialize(foundBook.get().getOwner());
            return Optional.ofNullable(foundBook.get().getOwner());
        }
        return Optional.empty();
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int book_id, Book updatedBook) {
        updatedBook.setBook_id(book_id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int book_id) {
        booksRepository.deleteById(book_id);
    }

    @Transactional
    public void assignPersonByBookId(int book_id, Person person) {
        Optional<Book> foundBook = booksRepository.findById(book_id);
        foundBook.ifPresent(book -> {
            book.setOwner(person);
            book.setDate(new Date());
        });
    }

    @Transactional
    public void releaseBook(int book_id) {
        Optional<Book> foundBook = booksRepository.findById(book_id);

        foundBook.ifPresent(book -> {
            book.setOwner(null);
            book.setDate(null);
        });
    }

    public List<Book> findByTitleStartingWith(String title) {
        return booksRepository.findByTitleStartingWith(title);
    }
}
