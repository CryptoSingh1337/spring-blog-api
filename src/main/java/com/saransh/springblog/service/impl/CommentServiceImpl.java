package com.saransh.springblog.service.impl;

import com.saransh.springblog.repository.CommentRepository;
import com.saransh.springblog.service.CommentService;
import com.saransh.springblog.web.mapper.CommentMapper;
import com.saransh.springblog.web.model.CommentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public List<CommentDTO> findAllByPostId(Pageable pageable, UUID postId) {
        return commentRepository.findAllByPost_Id(pageable, postId).stream()
                .map(commentMapper::commentToCommentDTO)
                .collect(Collectors.toList());
    }
}
