package com.saransh.springblog.service;

import com.saransh.springblog.web.model.PostDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */

public interface PostService {
    List<PostDTO> findAll(Pageable pageable);

    List<PostDTO> findAllByUsername(String username);

    List<PostDTO> findAllByCategory(Pageable pageable, String categoryName);

    PostDTO findById(UUID postId);

    List<PostDTO> findAllByTitle(Pageable pageable, String title);

    PostDTO save(PostDTO postDTO);

    PostDTO update(UUID postId, PostDTO postDTO);
}
