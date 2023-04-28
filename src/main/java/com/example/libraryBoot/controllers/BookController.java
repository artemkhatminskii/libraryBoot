package com.example.libraryBoot.controllers;

import com.example.libraryBoot.Services.BookService;
import com.example.libraryBoot.Services.PersonService;
import com.example.libraryBoot.models.Book;
import com.example.libraryBoot.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final PersonService personService;

    private final BookService bookService;

    @Autowired
    public BookController(PersonService personService, BookService bookService) {
        this.personService = personService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false) boolean sortByYear) {
        if(page == null || booksPerPage == null)
        model.addAttribute("books", bookService.findAll(sortByYear));
        else model.addAttribute("books", bookService.findWithPagination(page, booksPerPage, sortByYear));
        return "books/index";
    }

    @GetMapping("/{book_id}")
    public String show(@PathVariable("book_id") int book_id, Model model,
                       @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookService.findOne(book_id));

        Optional<Person> bookOwner = bookService.showPersonByBookId(book_id);

        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", personService.findAll());

        return "books/show";
    }

    @GetMapping("/new_book")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return "books/new";

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBooks() {
        return "books/search";
    }

    @PostMapping("/search")
    public String searchBookByTitle(Model model, @RequestParam(value = "title", required = false) String title) {
        model.addAttribute("books", bookService.findByTitleStartingWith(title));
        return "books/search";
    }

    @GetMapping("/{book_id}/edit")
    public String edit(Model model, @PathVariable("book_id") int book_id) {
        model.addAttribute("book", bookService.findOne(book_id));
        return "books/edit";
    }

    @PatchMapping("/{book_id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("book_id") int book_id) {

        if (bindingResult.hasErrors()) return "books/edit";
        bookService.update(book_id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{book_id}")
    public String delete(@PathVariable("book_id") int book_id) {
        bookService.delete(book_id);
        return "redirect:/books";
    }

    @PatchMapping("/{book_id}/assign")
    public String assign(@PathVariable("book_id") int book_id,
                         @ModelAttribute("person") Person selectedPerson) {
        bookService.assignPersonByBookId(book_id, selectedPerson);
        return "redirect:/books/" + book_id;
    }

    @PatchMapping("/{book_id}/release")
    public String realease(@PathVariable("book_id") int book_id) {
        bookService.releaseBook(book_id);
        return "redirect:/books/" + book_id;
    }
}
