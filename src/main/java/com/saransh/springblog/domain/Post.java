package com.saransh.springblog.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(
            length = 36,
            columnDefinition = "varchar",
            updatable = false,
            nullable = false
    )
    private UUID id;
    private String title;
    private String username;

    @Lob
    private String body;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private String img;
    private Long views;
    private Boolean published;

    @Version
    private Integer version;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = LAZY)
    private List<Comment> comments;

    @ManyToOne
    private Category category;

    @PrePersist
    public void updateCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public void addComment(Comment comment) {
        if (comments == null)
            comments = new ArrayList<>();
        this.comments.add(comment);
        comment.setPost(this);
    }
}
