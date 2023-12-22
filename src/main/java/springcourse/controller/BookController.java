package springcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springcourse.dao.BookDAO;
import springcourse.dao.PersonDAO;
import springcourse.models.Book;
import springcourse.models.Person;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookDAO.getAll());
        return "books/showAllBook";
    }

    @GetMapping("/{id}")
    public String showByIdBook(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.getById(id));

        Optional<Person> bookOwner = bookDAO.getBookOwner(id);

        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", personDAO.index());

        return "books/showByIdBook";
    }

    @GetMapping("/new")
    public String addBook(@ModelAttribute("book") Book book) {
        return "books/addNewBook";
    }

    @PostMapping
    public String addNewBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "books/addNewBook";
        }
        bookDAO.create(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String updateBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.getById(id));
        return "books/updateBook";
    }

    @PatchMapping("/{id}")
    public String upgradeBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                              @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) {
            return "books/updateBook";
        }
        bookDAO.update(id, book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String deleteBookById(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/book";
    }

    // освобождает книку
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDAO.release(id);
        return "redirect:/book/" + id;
    }

    // добавление книги человеку
    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        bookDAO.assign(id, selectedPerson);
        return "redirect:/book/" + id;
    }
}
