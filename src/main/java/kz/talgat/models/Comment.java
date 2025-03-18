package kz.talgat.models;

public record Comment(Integer postId, Integer id, String name, String email, String body) {
}
