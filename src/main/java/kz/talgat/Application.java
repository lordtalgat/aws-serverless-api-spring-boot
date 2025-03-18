package kz.talgat;

import kz.talgat.post.JsonGeneratorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    // Mock json collections
    //https://jsonplaceholder.typicode.com/
    // /posts	100 posts
    // /comments	500 comments
    // /albums	100 albums
    // /photos	5000 photos
    // /todos	200 todos
    // /users	10 users
    @Bean
    JsonGeneratorService jsonPlaceholderService() {
        WebClient client = WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder().exchangeAdapter(WebClientAdapter.create(client)).build();
        return factory.createClient(JsonGeneratorService.class);
    }
}