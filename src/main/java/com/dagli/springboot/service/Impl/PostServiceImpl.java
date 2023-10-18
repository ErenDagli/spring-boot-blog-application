package com.dagli.springboot.service.Impl;

import com.dagli.springboot.dto.PostDto;
import com.dagli.springboot.dto.PostResponse;
import com.dagli.springboot.entity.Category;
import com.dagli.springboot.entity.Post;
import com.dagli.springboot.exception.ResourceNotFoundException;
import com.dagli.springboot.repository.CategoryRepository;
import com.dagli.springboot.repository.PostRepository;
import com.dagli.springboot.service.CategoryService;
import com.dagli.springboot.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper mapper;

    private CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException(
                "Category", "id", postDto.getCategoryId()
        ));

        //convert DTO to Entity
        Post post = mapToEntity(postDto);
        post.setCategory(category);
        Post newPost = postRepository.save(post);

        // convert entity to DTO
        PostDto postResponse = mapToDTO(newPost);

        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // create Pageable instance
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageRequest);

        // get content for page object
        List<Post> listOfPosts = posts.getContent();
        List<PostDto> content = listOfPosts.stream().map(this::mapToDTO).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = getPostEntity(id);
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        // get post by id from the database
        Post post = getPostEntity(id);

        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException(
                "Category", "id", postDto.getCategoryId()
        ));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(category);

        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = getPostEntity(id);
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getPostsByCategory(long categoryId) {

        categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category","id",categoryId));

        List<Post> posts = postRepository.findByCategoryId(categoryId);

        return posts.stream().map((post) -> mapToDTO(post)).collect(Collectors.toList());
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = mapper.map(postDto,Post.class);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }
    private PostDto mapToDTO(Post post) {
        PostDto postDto = mapper.map(post,PostDto.class);
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return postDto;
    }

    private Post getPostEntity(long postId){
        // get post by id from the database
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));
        return post;
    }
}
