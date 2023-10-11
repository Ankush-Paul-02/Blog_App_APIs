package com.devmare.blog_app_apis.services.impl;

import com.devmare.blog_app_apis.configuration.ModelMapperConfiguration;
import com.devmare.blog_app_apis.entities.Category;
import com.devmare.blog_app_apis.entities.Post;
import com.devmare.blog_app_apis.entities.User;
import com.devmare.blog_app_apis.exceptions.ResourceNotFoundException;
import com.devmare.blog_app_apis.payloads.PostResponse;
import com.devmare.blog_app_apis.payloads.dto.PostDTO;
import com.devmare.blog_app_apis.repositories.CategoryRepository;
import com.devmare.blog_app_apis.repositories.PostRepository;
import com.devmare.blog_app_apis.repositories.UserRepository;
import com.devmare.blog_app_apis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapperConfiguration modelMapperConfiguration;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO, Integer usreId, Integer categoryId) {

        User user = userRepository.findById(usreId).orElseThrow(() -> new ResourceNotFoundException("User ", "id: ", usreId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category ", "id: ", categoryId));

        Post post = modelMapperConfiguration.modelMapper().map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setCreatedAt(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post createdPost = postRepository.save(post);
        return modelMapperConfiguration.modelMapper().map(createdPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post ", "id: ", id));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());
        Post updatedPost = postRepository.save(post);
        return modelMapperConfiguration.modelMapper().map(updatedPost, PostDTO.class);
    }

    @Override
    public void deletePost(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post ", "id: ", id));
        postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort;
        if (sortDirection.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> postPage = postRepository.findAll(pageable);
        List<Post> postList = postPage.getContent();

        List<PostDTO> postDTOList = postList.stream().map((post) -> modelMapperConfiguration.modelMapper().map(post, PostDTO.class)).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTOList);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());

        return postResponse;
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Category ", "id: ", postId));
        return modelMapperConfiguration.modelMapper().map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getPostByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category ", "id: ", categoryId));
        List<Post> postList = postRepository.findByCategory(category);
        return postList.stream().map((post) -> modelMapperConfiguration.modelMapper().map(post, PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getPostByUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User ", "id: ", userId));
        List<Post> postList = postRepository.findByUser(user);
        return postList.stream().map((post) -> modelMapperConfiguration.modelMapper().map(post, PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<Post> searchPost(String keyword) {
        return null;
    }
}
