package uexcel.com.reswebservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uexcel.com.reswebservices.service.PostService;
import uexcel.com.reswebservices.user.Post;
import uexcel.com.reswebservices.user.User;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {
    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("jpa-users/post")
    public List<Post> getPosts() {
       return postService.retrieveAllPosts();
    }

    @GetMapping("jpa-users/post/{postId}")
    public Post findOnePost(@PathVariable int postId) {
        return postService.findPostById(postId);
    }

    @GetMapping("jpa-users/{userId}/post/{postId}")
    public Post findNewPost(@PathVariable int postId, @PathVariable int userId) {
        return postService.findPostById(postId);
    }

    @GetMapping("jpa-users/{userId}/post")
    public List<Post> retrieverUserPosts(@PathVariable int userId) {
        return postService.retrieveAllUserPosts(userId);
    }

    @PostMapping("jpa-users/{userId}/post")
    public ResponseEntity<String> addPosts(@RequestBody Post post, @PathVariable int userId) {
        log.info("adding new post {}", post);
        Post pst = postService.savePost(post, userId);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pst.getId())
                .toUri();
       return ResponseEntity.created(location).build();
    }

    @PutMapping("jpa-users/{userId}/post/{postId}")
    public void updatePost(@RequestBody Post post,
                           @PathVariable int userId, @PathVariable int postId){
        post.setId(postId);
        postService.savePost(post,userId);
    }
}
