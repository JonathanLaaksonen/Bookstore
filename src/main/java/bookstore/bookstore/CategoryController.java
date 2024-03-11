package bookstore.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categorylist")
    public String categoryList(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";
    }

    @GetMapping("/addcategory")
public String showAddCategoryForm(Model model) {
    model.addAttribute("category", new Category());
    return "addcategory";
}

@PostMapping("/addcategory")
public String addCategory(Category category) {
    categoryRepository.save(category);
    return "redirect:/categorylist";
}

}