package com.saransh.springblog.repository;

import com.saransh.springblog.domain.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * Created by CryptoSingh1337 on 7/30/2021
 */

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("from Comment c join c.post p where p.id = :postId")
    List<Comment> findAllByPost_Id(Pageable pageable, UUID postId);
}
