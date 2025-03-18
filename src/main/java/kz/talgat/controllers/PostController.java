package kz.talgat.controllers;

import jakarta.annotation.PostConstruct;

import kz.talgat.exeptions.ItemNotFoundException;
import kz.talgat.generators.JsonGeneratorService;
import kz.talgat.models.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private final JsonGeneratorService jsonGeneratorService;
    private List<Post> posts = new ArrayList<Post>();

    public PostController(final JsonGeneratorService jsonGeneratorService) {
        this.jsonGeneratorService = jsonGeneratorService;
    }

    @GetMapping
    List<Post> findAll() {
        return posts;
    }

    @GetMapping("/{id}")
    Optional<Post> findById(@PathVariable Integer id) {
        return Optional.ofNullable(
                posts.stream()
                        .filter(post -> post.id().equals(id))
                        .findFirst()
                        .orElseThrow(() -> new ItemNotFoundException("Post with id: " + id + " not found.")));
    }

    @PostMapping
    void create(@RequestBody Post post) {
        posts.add(post);
    }

    @PutMapping("/{id}")
    void update(@RequestBody Post post, @PathVariable Integer id) {
        posts.stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .ifPresent(value -> posts.set(posts.indexOf(value), post));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        posts.removeIf(post -> post.id().equals(id));
    }

    @PostConstruct
    private void init() {
        if (posts.isEmpty()) {
            log.info("Loading Posts using JsonGeneratorService");
            posts = jsonGeneratorService.generatePosts();
        }
    }
}
