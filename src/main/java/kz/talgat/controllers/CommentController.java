package kz.talgat.controllers;

import jakarta.annotation.PostConstruct;
import kz.talgat.exeptions.ItemNotFoundException;
import kz.talgat.generators.JsonGeneratorService;
import kz.talgat.models.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private static final Logger log = LoggerFactory.getLogger(CommentController.class);
    private final JsonGeneratorService jsonGeneratorService;
    private List<Comment> comments = new ArrayList<>();

    public CommentController(final JsonGeneratorService jsonGeneratorService) {
        this.jsonGeneratorService = jsonGeneratorService;
    }

    @GetMapping
    List<Comment> findAll() {
        return comments;
    }

    @GetMapping("/{id}")
    Optional<Comment> findById(@PathVariable Integer id) {
        return Optional.ofNullable(
                comments.stream()
                        .filter(comment -> comment.id().equals(id))
                        .findFirst()
                        .orElseThrow(() -> new ItemNotFoundException("Comment with id: " + id + " not found.")));
    }

    @PostMapping
    void create(@RequestBody Comment comment) {
        comments.add(comment);
    }

    @PutMapping("/{id}")
    void update(@RequestBody Comment comment, @PathVariable Integer id) {
        comments.stream()
                .filter(c -> c.id().equals(id))
                .findFirst()
                .ifPresent(value -> comments.set(comments.indexOf(value), comment));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        comments.removeIf(comment -> comment.id().equals(id));
    }

    @PostConstruct
    private void init() {
        if (comments.isEmpty()) {
            log.info("Loading Comments using JsonGeneratorService");
            comments = jsonGeneratorService.generateComments();
        }
    }
}
