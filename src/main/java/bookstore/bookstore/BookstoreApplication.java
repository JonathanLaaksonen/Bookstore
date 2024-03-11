package bookstore.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
CommandLineRunner initDatabase() {
    return args -> {
        Category sciFiCategory = categoryRepository.save(new Category("Sci-Fi"));
        Category comicCategory = categoryRepository.save(new Category("Comic"));
   
        bookRepository.save(new Book("How to be boss", "Bossman", 2023, "ISBN1", 19.99, sciFiCategory));
        bookRepository.save(new Book("How to be hero", "Sankari", 2024, "ISBN2", 29.99, comicCategory));
     
        log.info("Categories saved:");
        categoryRepository.findAll().forEach(category -> log.info(category.toString()));
        
        log.info("Books saved:");
        bookRepository.findAll().forEach(book -> log.info(book.toString()));
    };
    }

}