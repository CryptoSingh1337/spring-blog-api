package com.saransh.springblog.service;

import com.saransh.springblog.repository.PostRepository;
import com.saransh.springblog.web.mapper.PostMapper;
import com.saransh.springblog.web.model.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public List<PostDTO> findAll(Pageable pageable) {
        return postRepository.findAll(pageable).get()
                .map(postMapper::postToPostDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> findAllByCategory(Pageable pageable, String categoryName) {
        return postRepository.findAllByCategories_NameIgnoreCase(pageable, categoryName).stream()
                .map(postMapper::postToPostDTO)
                .collect(Collectors.toList());
    }
}
