package com.saransh.springblog.service;

import com.saransh.springblog.web.model.CommentDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * Created by CryptoSingh1337 on 7/30/2021
 */

public interface CommentService {
    List<CommentDTO> findAllByPostId(Pageable pageable, UUID postId);
    CommentDTO findById(UUID postId, Long id);
    CommentDTO save(UUID postId, CommentDTO commentDTO);
    CommentDTO update(UUID postId, CommentDTO commentDTO);
    void delete(UUID postId, Long id);
}
