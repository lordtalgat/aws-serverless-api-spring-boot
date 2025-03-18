package kz.talgat.controllers;

import jakarta.annotation.PostConstruct;
import kz.talgat.exeptions.ItemNotFoundException;
import kz.talgat.generators.JsonGeneratorService;
import kz.talgat.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final JsonGeneratorService jsonGeneratorService;
    private List<User> users = new ArrayList<>();

    public UserController(final JsonGeneratorService jsonGeneratorService) {
        this.jsonGeneratorService = jsonGeneratorService;
    }

    @GetMapping
    List<User> findAll() {
        return users;
    }

    @GetMapping("/{id}")
    Optional<User> findById(@PathVariable Integer id) {
        return Optional.ofNullable(
                users.stream()
                        .filter(user -> user.id().equals(id))
                        .findFirst()
                        .orElseThrow(() -> new ItemNotFoundException("User with id: " + id + " not found.")));
    }

    @PostMapping
    void create(@RequestBody User user) {
        users.add(user);
    }

    @PutMapping("/{id}")
    void update(@RequestBody User user, @PathVariable Integer id) {
        users.stream()
                .filter(u -> u.id().equals(id))
                .findFirst()
                .ifPresent(value -> users.set(users.indexOf(value), user));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        users.removeIf(user -> user.id().equals(id));
    }

    @PostConstruct
    private void init() {
        if (users.isEmpty()) {
            log.info("Loading Users using JsonGeneratorService");
            users = jsonGeneratorService.generateUsers();
        }
    }
}
