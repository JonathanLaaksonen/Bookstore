package bookstore.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BookstoreApplication {

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository; // Lisätty UserRepository

    @Autowired
    private PasswordEncoder passwordEncoder; // Lisätty PasswordEncoder

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            // Alustetaan kategoriat
            Category sciFiCategory = categoryRepository.save(new Category("Sci-Fi"));
            Category comicCategory = categoryRepository.save(new Category("Comic"));
       
            // Alustetaan kirjat
            bookRepository.save(new Book("How to be boss", "Bossman", 2023, "ISBN1", 19.99, sciFiCategory));
            bookRepository.save(new Book("How to be hero", "Sankari", 2024, "ISBN2", 29.99, comicCategory));
         
            log.info("Categories saved:");
            categoryRepository.findAll().forEach(category -> log.info(category.toString()));
            
            log.info("Books saved:");
            bookRepository.findAll().forEach(book -> log.info(book.toString()));
    
            // Lisätään käyttäjiä vain, jos tietokannassa ei ole yhtään käyttäjää
            if (userRepository.count() == 0) {
                // Käyttäjien luominen
                userRepository.save(new User("user", passwordEncoder.encode("password"), "user@example.com", "USER"));
                userRepository.save(new User("admin", passwordEncoder.encode("adminpassword"), "admin@example.com", "ADMIN"));
                
                // Logataan lisätyt käyttäjät
                log.info("Users saved:");
                userRepository.findAll().forEach(user -> log.info(user.toString()));
            }
        };
    }
}