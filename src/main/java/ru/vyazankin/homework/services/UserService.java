package ru.vyazankin.homework.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vyazankin.homework.entities.User;
import ru.vyazankin.homework.repositories.UserRepository;

import java.security.Principal;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@Profile("dao")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public Optional<User> findUserByName(String name){
        return userRepository.findUserByName(name);
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    public User getUserByPrincipal(Principal principal){
        return findUserByName(principal.getName()).orElseThrow(()->new UsernameNotFoundException(principal.getName() + " is not valid username"));
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(name)
                                            .orElseThrow(() -> new UsernameNotFoundException(name + " is not a valid username"));

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()));

    }

    public User addScore(User user, int s){
        user.getScore().setValue(user.getScore().getValue() + s);
        return user;
    }

    public User update(User user){
        return userRepository.save(user);
    }

}
