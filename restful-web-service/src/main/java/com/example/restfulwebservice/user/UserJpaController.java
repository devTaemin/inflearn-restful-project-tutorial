package com.example.restfulwebservice.user;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping("/jpa")
public class UserJpaController {

    Logger logger = LoggerFactory.getLogger(UserJpaController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    // http://localhost:8088/jpa/users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] NOT FOUND", id));
        }

        // Hateoas
        Resource<User> resource = new Resource<>(user.get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

//        return user.get();
        return resource;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    // /jpa/users/90001/posts
    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return user.get().getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<User> createPost(@PathVariable int id, @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        post.setUser(user.get());
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    // Post 조회
    @GetMapping("/users/{id}/posts/{postId}")
    public Resource<Post> retrievePost(@PathVariable int id, @PathVariable int postId) {
        Optional<User> user = userRepository.findById(id);

        logger.info("Before: User");
        if (!user.isPresent()) {
            logger.info("Not Present: User");
            throw new UserNotFoundException(String.format("ID[%s] NOT FOUND", id));
        }
        logger.info("After: User");

        Optional<Post> post = postRepository.findById(postId);

        logger.info("Before: Post");
        if (!post.isPresent()) {
            logger.info("Not Present: Post");
            throw new PostNotFoundException(String.format("ID[%s] NOT FOUND", postId));
        }

        logger.info("After: Post");

        // Hateoas
        Resource<Post> resource = new Resource<>(post.get());

        return resource;
    }


    // Post 삭제
    @DeleteMapping("/users/{id}/posts/{postId}")
    public void deletePost(@PathVariable int id, @PathVariable int postId) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] NOT FOUND", id));
        }

        Optional<Post> post = postRepository.findById(postId);

        if (!post.isPresent()) {
            throw new PostNotFoundException(String.format("ID[%s] NOT FOUND", postId));
        }

        postRepository.deleteById(postId);
    }

}
