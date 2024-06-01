package org.example.backend.service;

import org.example.backend.domain.Post;

import java.util.List;

public interface PostService {
    boolean savePost(Post post);

    boolean deletePostById(long id);

    Post findPostById(long id);

    List<Post> findAllPosts();

    boolean deletePost(long postId);
}
