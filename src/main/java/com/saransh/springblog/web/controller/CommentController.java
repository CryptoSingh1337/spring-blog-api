package com.saransh.springblog.web.controller;

import com.saransh.springblog.service.CommentService;
import com.saransh.springblog.web.model.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by CryptSingh1337 on 7/30/2021
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comment/{postId}")
public class CommentController {

    private final CommentService commentService;
    @Value("${api.pagination.page_size}")
    private int PAGE_SIZE;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> findAllByPostId(
            @PathVariable UUID postId,
            @RequestParam("page") int pageNo
    ) {
        List<CommentDTO> comments = commentService.findAllByPostId(
                PageRequest.of(pageNo, PAGE_SIZE), postId);
        return ResponseEntity.ok().body(comments);
    }
}
