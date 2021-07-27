package com.saransh.springblog.web.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"comments", "categories"})
public class PostDTO {

    private UUID id;
    private String title;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String img;
    private Long views;
    private Boolean published;
    private List<CommentDTO> comments;
    private Set<CategoryDTO> categories;
}
