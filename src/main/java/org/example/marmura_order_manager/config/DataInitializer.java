package org.example.marmura_order_manager.config;


import org.example.marmura_order_manager.model.User;
import org.example.marmura_order_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Value("${app.admin.password}")
    private String adminPassword;

    @Value("${app.demo.password}")
    private String demoPassword;
    @Bean
    public CommandLineRunner initUser(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            if(userRepository.findByUsername("admin").isEmpty()){
                User user = new User();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode(adminPassword));
                userRepository.save(user);
                System.out.println("User admin created");
            }

            if(userRepository.findByUsername("demo").isEmpty()){
                User demo = new User();
                demo.setUsername("demo");
                demo.setPassword(passwordEncoder.encode(demoPassword));
                userRepository.save(demo);
            }
        };
    }

}
