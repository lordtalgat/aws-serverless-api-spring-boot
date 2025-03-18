package kz.talgat.generators;

import kz.talgat.models.Comment;
import kz.talgat.models.Post;
import kz.talgat.models.User;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface JsonGeneratorService {
    @GetExchange("/posts")
    List<Post> generatePosts();

    @GetExchange("/users")
    List<User> generateUsers();

    @GetExchange("/comments")
    List<Comment> generateComments();
}
