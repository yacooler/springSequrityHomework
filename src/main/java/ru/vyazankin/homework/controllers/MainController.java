package ru.vyazankin.homework.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.vyazankin.homework.entities.User;
import ru.vyazankin.homework.exceptions.UserNotFoundException;
import ru.vyazankin.homework.services.UserService;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class MainController {
    private final UserService userService;

    @GetMapping("/score/inc")
    public String scoreInc(Principal principal) {
        User user = userService.addScore(userService.getUserByPrincipal(principal), 1);
        userService.update(user);
        return String.format("Current user is %s inc(score) = %d", user.getName(), user.getScore().getValue());
    }

    @GetMapping("/score/dec")
    public String scoreDec(Principal principal) {
        User user = userService.addScore(userService.getUserByPrincipal(principal), -1);
        userService.update(user);
        return String.format("Current user is %s dec(score) = %d", user.getName(), user.getScore().getValue());
    }

    @GetMapping("/score/get")
    public String scoreGet(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return String.format("Current user is %s score = %d", user.getName(), user.getScore().getValue());
    }

    @GetMapping("/score/get/{id}")
    public String scoreGet(@PathVariable Long id, Principal principal) {
        User user = userService.findUserById(id).orElseThrow(()-> new UserNotFoundException(id + " is not valid ID for user"));
        return String.format("Current user is %s. User %s has score = %d", principal.getName(), user.getName(), user.getScore().getValue());
    }

}

