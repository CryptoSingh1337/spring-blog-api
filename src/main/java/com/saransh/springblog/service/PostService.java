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
    List<PostDTO> findAllByCategory(Pageable pageable, String categoryName);
    PostDTO findById(UUID postId);
    PostDTO save(PostDTO postDTO);
}
