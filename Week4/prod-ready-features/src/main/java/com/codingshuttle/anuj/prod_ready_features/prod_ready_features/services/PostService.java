package com.codingshuttle.anuj.prod_ready_features.prod_ready_features.services;

import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.dto.PostDTO;
import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);

    PostDTO updatePost(PostDTO inputPost, Long postId);
}
