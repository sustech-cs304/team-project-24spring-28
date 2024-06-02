package org.example.backend.service;

import org.example.backend.api.PostRepository;
import org.example.backend.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public boolean savePost(Post post) {
        postRepository.save(post);
        return true;
    }

    @Override
    public boolean deletePostById(long id) {
        postRepository.deleteById(id);
        return true;
    }

    @Override
    public Post findPostById(long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findPostsByUserId(long userId) {
        return postRepository.findPostsByUserId(userId);
    }

    @Override
    public boolean deletePost(long postId) {
        postRepository.deleteById(postId);
        return true;
    }

    @Override
    public List<Post> searchPost(String keyword) {
        return postRepository.findAllByPostContentContainingIgnoreCaseOrPostTitleContainingIgnoreCase(keyword, keyword);
    }
}
