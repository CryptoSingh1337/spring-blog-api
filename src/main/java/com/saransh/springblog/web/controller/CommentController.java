package com.saransh.springblog.web.controller;

import com.saransh.springblog.service.CommentService;
import com.saransh.springblog.web.model.CommentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by CryptSingh1337 on 7/30/2021
 */
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v1/comment/{postId}")
public class CommentController {

    private final CommentService commentService;
    @Value("${api.pagination.page_size}")
    private int PAGE_SIZE;

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<CommentDTO>> findAllByPostId(
            @PathVariable UUID postId,
            @RequestParam("page") int pageNo
    ) {
        List<CommentDTO> comments = commentService.findAllByPostId(
                PageRequest.of(pageNo, PAGE_SIZE), postId);
        return ResponseEntity.ok().body(comments);
    }

    @GetMapping(value = "/{commentId}", produces = {"application/json"})
    public ResponseEntity<CommentDTO> findById(
            @PathVariable UUID postId,
            @PathVariable Long commentId) {
        return ResponseEntity.ok().body(commentService.findById(postId, commentId));
    }


    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<CommentDTO> save(
            @PathVariable UUID postId,
            @RequestBody CommentDTO commentDTO
    ) {
        CommentDTO savedComment = commentService.save(postId, commentDTO);
        return ResponseEntity.ok().body(savedComment);
    }

    @PutMapping(consumes = {"application/json"})
    public ResponseEntity<CommentDTO> update(
            @PathVariable UUID postId,
            @RequestBody CommentDTO commentDTO
    ) {
        CommentDTO savedComment = commentService.update(postId, commentDTO);
        log.debug("Comment with Id: {} updated successfully", commentDTO.getId());
        return ResponseEntity.ok().body(savedComment);
    }

    @DeleteMapping
    @ResponseStatus(OK)
    public void deleteById(
            @PathVariable UUID postId,
            @RequestParam Long commentId
    ) {
        commentService.delete(postId, commentId);
        log.debug("Comment with Id: {} deleted successfully", commentId);
    }
}
