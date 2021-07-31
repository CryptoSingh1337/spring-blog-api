package com.saransh.springblog.service.impl;

import com.saransh.springblog.domain.Comment;
import com.saransh.springblog.domain.Post;
import com.saransh.springblog.exception.ResourceNotFoundException;
import com.saransh.springblog.repository.CommentRepository;
import com.saransh.springblog.repository.PostRepository;
import com.saransh.springblog.service.CommentService;
import com.saransh.springblog.web.mapper.CommentMapper;
import com.saransh.springblog.web.model.CommentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by CryptoSingh1337 on 7/30/2021
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    private void checkPostIfExists(UUID postId) {
        postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post with Id: " + postId + " not found"));
    }

    @Override
    public List<CommentDTO> findAllByPostId(Pageable pageable, UUID postId) {
        checkPostIfExists(postId);
        log.debug("Retrieving all the comments of the Post with Id: {}", postId);
        return commentRepository.findAllByPost_Id(pageable, postId).stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO findById(UUID postId, Long id) {
        checkPostIfExists(postId);
        return commentRepository.findById(id)
                .map(commentMapper::commentToCommentDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with Id: " + id + " not found"));
    }

    @Override
    @Transactional
    public CommentDTO save(UUID postId, CommentDTO commentDTO) {
        Post retrievedPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post with Id: " + postId + " not found"));
        Comment savedComment = commentRepository.save(commentMapper.commentDTOToComment(commentDTO));
        retrievedPost.addComment(savedComment);
        log.debug("Saved the comment with Id: {} of the Post with Id: {}", savedComment.getId(), postId);
        return commentMapper.commentToCommentDTO(savedComment);
    }

    @Override
    @Transactional
    public CommentDTO update(UUID postId, CommentDTO commentDTO) {
        checkPostIfExists(postId);
        log.debug("Retrieving the comment with Id: {}", commentDTO.getId());
        Comment retrievedComment = commentRepository.findById(commentDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Comment with Id: " +
                        commentDTO.getId() + " not found"));
        retrievedComment.setBody(commentDTO.getBody());
        return commentMapper.commentToCommentDTO(commentRepository.save(retrievedComment));
    }

    @Override
    public void delete(UUID postId, Long commentId) {
        checkPostIfExists(postId);
        log.debug("Retrieving the comment with Id: {}", commentId);
        commentRepository.deleteById(commentId);
    }
}
