package bookstore.bookstore;

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository; 

    @Autowired 
    private CategoryRepository categoryRepository;


    @GetMapping("/") 
    public String index(Model model) {
        return "index"; 
    }

    @GetMapping("/booklist") 
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist"; 
    }

    @GetMapping("/addbook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll()); // Käytetään injektoitua repositoryä
        return "addbook";
    }

@PostMapping("/addbook")
    public String addBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
}
@PostMapping("/booklist")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
    }
@GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId) {
        if (bookId != null) {
            bookRepository.deleteById(bookId);
}
return "redirect:/booklist";
}
@GetMapping("/edit/{id}")
public String showEditBookForm(@PathVariable("id") Long bookId, Model model) {
    Book book = bookRepository.findById(bookId).orElse(null);
    model.addAttribute("book", book);
    return "editbook";
}
@PostMapping("/editbook")
public String editBook(@ModelAttribute Book book) {
    bookRepository.save(book);
    return "redirect:/booklist";
}
@RequestMapping(value = "/api/books", method = RequestMethod.GET)
public @ResponseBody List<Book> getAllBooks() {
    return bookRepository.findAll();
}

@RequestMapping(value = "/api/books/{id}", method = RequestMethod.GET)
public @ResponseBody Book getBookById(@PathVariable Long id) {
    return bookRepository.findById(id).orElse(null);
}

@GetMapping("/login")
    public String login() {
        return "login";
    }

    // Tarkista, että tämä ei ohjaa itseään tai toista metodia, joka ohjaa takaisin tähän.
    @GetMapping("/homepage")
    public String homePage() {
        return "index";  // Esimerkiksi tämä ohjaa etusivulle.
    }
}