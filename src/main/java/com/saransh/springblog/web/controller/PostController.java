package com.saransh.springblog.web.controller;

import com.saransh.springblog.service.PostService;
import com.saransh.springblog.web.model.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;
    private final int PAGE_SIZE = 2;

    @GetMapping
    public ResponseEntity<List<PostDTO>> findAll(@RequestParam("page") Integer pageNo) {
        List<PostDTO> posts = postService.findAll(PageRequest.of(pageNo, PAGE_SIZE));
        return ResponseEntity.ok().body(posts);
    }
}
