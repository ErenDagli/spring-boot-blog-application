package com.dagli.springboot.service.Impl;

import com.dagli.springboot.dto.CommentDto;
import com.dagli.springboot.entity.Comment;
import com.dagli.springboot.entity.Post;
import com.dagli.springboot.exception.BlogAPIException;
import com.dagli.springboot.exception.ResourceNotFoundException;
import com.dagli.springboot.repository.CommentRepository;
import com.dagli.springboot.repository.PostRepository;
import com.dagli.springboot.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);

        Post post = getPostEntity(postId);
        // set post to comment entity
        comment.setPost(post);

        // comment enttiy to db
        Comment newComment = commentRepository.save(comment);

        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        // convert list of comment entities to list of comment dto's
        return comments.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Post post = getPostEntity(postId);
        Comment comment = getCommentEntity(commentId);

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        return mapToDTO(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        Post post = getPostEntity(postId);
        Comment comment = getCommentEntity(commentId);

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belongs to post");
        }

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepository.save(comment);
        return mapToDTO(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = getPostEntity(postId);
        Comment comment = getCommentEntity(commentId);

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belongs to post");
        }

        commentRepository.delete(comment);
    }

    private CommentDto mapToDTO(Comment comment){
        CommentDto commentDto = mapper.map(comment,CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto,Comment.class);
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }

    private Post getPostEntity(long postId) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post","id",postId));
        return post;
    }

    private Comment getCommentEntity(long commentId) {
        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment","id",commentId));
        return comment;
    }
}
