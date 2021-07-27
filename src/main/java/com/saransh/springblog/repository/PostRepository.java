package com.saransh.springblog.repository;

import com.saransh.springblog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
public interface PostRepository extends JpaRepository<Post, UUID> {
}
