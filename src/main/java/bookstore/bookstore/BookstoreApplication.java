package bookstore.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        return args -> {
            repository.save(new Book("How to be boss", "Bossman", 2023, "ISBN1", 19.99));
            repository.save(new Book("How to be hero", "Sankari", 2024, "ISBN2", 29.99));
            repository.save(new Book("Taistelijat", "Superman", 2024, "ISBN3", 26.99));
            // Add as many books as you want here
        };
    }

}
