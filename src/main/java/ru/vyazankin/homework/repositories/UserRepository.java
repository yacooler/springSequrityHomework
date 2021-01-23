package ru.vyazankin.homework.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.vyazankin.homework.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<User> findUserByName(String name);
}
