package uexcel.com.reswebservices.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uexcel.com.reswebservices.exception.UserNotFoundException;
import uexcel.com.reswebservices.repository.PostRepository;
import uexcel.com.reswebservices.user.Post;
import uexcel.com.reswebservices.user.User;

import java.util.List;

@Service
public class PostService {
    private static final Logger log = LoggerFactory.getLogger(PostService.class);
    private  final  UserDtoJpaService userDtoJpaService;
    private final PostRepository postRepository;

    public PostService(UserDtoJpaService userDtoJpaService, PostRepository postRepository) {
        this.userDtoJpaService = userDtoJpaService;
        this.postRepository = postRepository;
    }

    public List<Post> retrieveAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> retrieveAllUserPosts(int id) {
        List<Post> posts = userDtoJpaService.findUserById(id).getPosts();
        if (posts.isEmpty()) {
            throw  new UserNotFoundException("There is not posts associated with the user: id:"+id);
        }
        return posts;

    }

    public Post savePost(Post post,int userId) {
        User user = userDtoJpaService.findUserById(userId);
        post.setUser(user);
        post.setUser(user);
       return postRepository.save(post);
    }

    public Post findPostById(int postId) {
        return postRepository.findById(postId)
                .orElseThrow(()-> new UserNotFoundException("Post not found: id:"+postId));
    }

}
