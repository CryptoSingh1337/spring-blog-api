package com.saransh.springblog.service;

import com.saransh.springblog.Exception.ResourceNotFoundException;
import com.saransh.springblog.domain.Post;
import com.saransh.springblog.repository.PostRepository;
import com.saransh.springblog.web.mapper.PostMapper;
import com.saransh.springblog.web.model.PostDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return postRepository.findAllByCategories_NameIgnoreCase(pageable, categoryName).stream()
                .map(postMapper::postToPostDTO)
                .collect(Collectors.toList());
    }

    public PostDTO findById(UUID postId) {
        log.debug("Retrieving the Post with Id: {}", postId);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Post with Id: %s not found", postId))
                );
        return postMapper.postToPostDTO(post);
    }

    @Override
    @Transactional
    public PostDTO save(PostDTO postDTO) {
        Post savedPost = postRepository.save(postMapper.postDTOToPost(postDTO));
        log.debug("Saved the Post with ID: {}", savedPost.getId());
        return postMapper.postToPostDTO(savedPost);
    }
}
