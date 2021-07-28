package com.saransh.springblog.repository;

import com.saransh.springblog.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("from Post p join p.categories c where c.name = :categoryName")
    List<Post> findAllByCategories_NameIgnoreCase(Pageable pageable, String categoryName);
}
