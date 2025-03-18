package kz.talgat.post;

import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface JsonGeneratorService {
    @GetExchange("/posts")
    List<Post> generatePosts();
}
