package com.wizcode.eshop.security.user;

import com.wizcode.eshop.model.User;
import com.wizcode.eshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(
                () -> new UsernameNotFoundException("User not found!")
        );
        System.out.println("Loaded encoded password from DB: " + user.getPassword());
        return ShopUserDetails.buildUserDerails(user);
    }
}
