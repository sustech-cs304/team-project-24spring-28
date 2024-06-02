package org.example.backend.api;

import org.example.backend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByUserId(long userId);

    List<Post> findAllByPostContentContainingIgnoreCaseOrPostTitleContainingIgnoreCase(String text, String title);
}
