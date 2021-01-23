package ru.vyazankin.homework.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vyazankin.homework.entities.Role;

@Repository
//@Profile("dao")
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
