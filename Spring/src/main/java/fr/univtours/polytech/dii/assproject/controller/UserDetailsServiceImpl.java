package fr.univtours.polytech.dii.assproject.controller;

import fr.univtours.polytech.dii.assproject.models.StoreUser;
import fr.univtours.polytech.dii.assproject.repositories.StoreUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private StoreUserRepository userRepository;

    public UserDetailsServiceImpl(StoreUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StoreUser user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);

        System.out.println("username = " + user.getUsername());

        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
}
