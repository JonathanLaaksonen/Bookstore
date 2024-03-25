package bookstore.bookstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new InMemoryUserDetailsManager(
            org.springframework.security.core.userdetails.User.withUsername("user")
                .password(encoder.encode("password"))
                .roles("USER")
                .build(),
            org.springframework.security.core.userdetails.User.withUsername("admin")
                .password(encoder.encode("password"))
                .roles("ADMIN")
                .build());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
           
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/api/**").permitAll() 
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")  // Määritä tämä käyttämään mukautettua kirjautumissivua
                .defaultSuccessUrl("/homepage", true)  // Ohjaa käyttäjä tähän osoitteeseen onnistuneen kirjautumisen jälkeen
                .permitAll()  // Salli pääsy kirjautumissivulle ilman autentikointia
    )
            .logout((logout) -> logout.permitAll());

        return http.build();
    }
}