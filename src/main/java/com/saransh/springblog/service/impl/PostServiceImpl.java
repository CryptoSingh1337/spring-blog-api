package com.saransh.springblog.service.impl;

import com.saransh.springblog.domain.Post;
import com.saransh.springblog.exception.ResourceNotFoundException;
import com.saransh.springblog.repository.PostRepository;
import com.saransh.springblog.service.PostService;
import com.saransh.springblog.web.mapper.PostMapper;
import com.saransh.springblog.web.model.PostDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public List<PostDTO> findAll(Pageable pageable) {
        log.debug("Retrieving all the Posts from Database");
        return postRepository.findAll(pageable).get()
                .map(postMapper::postToPostDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> findAllByCategory(Pageable pageable, String categoryName) {
        log.debug("Retrieving all the Posts having Category: {}", categoryName);
        return postRepository.findAllByCategories_Name(pageable, categoryName).stream()
                .map(postMapper::postToPostDTO)
                .collect(Collectors.toList());
    }

    public PostDTO findById(UUID postId) {
        log.debug("Retrieving the Post with Id: {}", postId);
        Post post = incrementViews(postId);
        return postMapper.postToPostDTO(post);
    }

    public List<PostDTO> findAllByTitle(Pageable pageable, String title) {
        log.debug("Retrieving all the Post with having {} in their title", title);
        return postRepository.findAllByTitleContainingIgnoreCase(pageable, title).stream()
                .map(postMapper::postToPostDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PostDTO save(PostDTO postDTO) {
        Post savedPost = postRepository.save(postMapper.postDTOToPost(postDTO));
        log.debug("Saved the Post with Id: {}", savedPost.getId());
        return postMapper.postToPostDTO(savedPost);
    }

    @Override
    @Transactional
    public PostDTO update(UUID postId, PostDTO postDTO) {
        log.debug("Retrieving the Post with Id: {}", postId);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not find with Id: " + postId));
        post.setTitle(postDTO.getTitle());
        post.setBody(postDTO.getBody());
        post.setUpdatedAt(LocalDateTime.now());
        return postMapper.postToPostDTO(postRepository.save(post));
    }

    private Post incrementViews(UUID postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Post with Id: %s not found", postId))
                );
        post.setViews(post.getViews() == null ? 1 : post.getViews() + 1);
        return postRepository.save(post);
    }
}
