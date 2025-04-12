package com.wizcode.eshop.data;

import com.wizcode.eshop.model.User;
import com.wizcode.eshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;

    public void onApplicationEvent(ApplicationReadyEvent event) {
        createDefaultUserIfNotExits();
    }

    private void createDefaultUserIfNotExits(){
        for (int i = 1; i < 5; i++) {
            String defaultEmail = "user" + i + "@eshop.com";
            if(userRepository.existsByEmail(defaultEmail)){
                continue;
            }
            User user = new User();
            user.setFirstName("Dummy");
            user.setLastName("User" + i);
            user.setEmail(defaultEmail);
            user.setPassword("112233");
            userRepository.save(user);
            System.out.println("Default vet user " +  i + " created successfully");
        }
    }
}
