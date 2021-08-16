package com.saransh.springblog.web.controller;

import com.saransh.springblog.exception.IdMismatchException;
import com.saransh.springblog.service.PostService;
import com.saransh.springblog.web.model.PostDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;
    @Value("${api.pagination.page_size}")
    private int PAGE_SIZE;

    @GetMapping
    public ResponseEntity<List<PostDTO>> findAll(@RequestParam("page") int pageNo) {
        List<PostDTO> posts = postService.findAll(getPageRequest(pageNo));
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/trending")
    public ResponseEntity<List<PostDTO>> findAllByViews(@RequestParam("page") int pageNo) {
        return ResponseEntity.ok().body(
                postService.findAll(getTrendingPageRequest(pageNo))
        );
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<PostDTO>> findAllByUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(
                postService.findAllByUsername(username)
        );
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<List<PostDTO>> findAllByCategory(
            @PathVariable String categoryName,
            @RequestParam("page") int pageNo
    ) {
        List<PostDTO> posts = postService.findAllByCategory(
                getPageRequest(pageNo),
                categoryName);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/id/{postId}")
    public ResponseEntity<PostDTO> findById(@PathVariable UUID postId) {
        return ResponseEntity.ok().body(postService.findById(postId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> findAllByTitle(
            @RequestParam String title,
            @RequestParam("page") int pageNo
    ) {
        List<PostDTO> posts = postService.findAllByTitle(
                getPageRequest(pageNo),
                title);
        return ResponseEntity.ok().body(posts);
    }

    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<PostDTO> savePost(@Validated @RequestBody PostDTO postDTO) {
        PostDTO savedPost = postService.save(postDTO);
        return ResponseEntity.created(
                URI.create(
                        ServletUriComponentsBuilder.fromCurrentRequestUri()
                                .build().toUriString() +
                                "/" + savedPost.getId().toString()
                )
        ).body(savedPost);
    }

    @PutMapping(value = "/id/{postId}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<PostDTO> updatePost(
            @PathVariable UUID postId,
            @Validated @RequestBody PostDTO postDTO
    ) {
        if (!postId.equals(postDTO.getId()))
            throw new IdMismatchException();
        PostDTO savedPost = postService.update(postId, postDTO);
        log.debug("Post with Id: {} updated successfully", postId);
        return ResponseEntity.ok().body(savedPost);
    }

    private Pageable getPageRequest(int pageNo) {
        return PageRequest.of(pageNo, PAGE_SIZE, Sort.Direction.DESC, "createdAt");
    }

    private Pageable getTrendingPageRequest(int pageNo) {
        return PageRequest.of(pageNo, PAGE_SIZE, Sort.Direction.DESC, "views");
    }
}
