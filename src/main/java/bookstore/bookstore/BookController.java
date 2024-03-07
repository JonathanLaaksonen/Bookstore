package bookstore.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository; 

    @GetMapping("/") 
    public String index(Model model) {
        return "index"; 
    }

    @GetMapping("/booklist") 
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist"; 
    }
}