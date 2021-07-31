package com.saransh.springblog.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Lob
    private String body;
    private LocalDateTime createdAt;
    @ManyToOne
    private Post post;

    @PrePersist
    public void updateCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}
