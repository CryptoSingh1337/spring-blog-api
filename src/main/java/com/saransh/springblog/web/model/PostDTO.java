package com.saransh.springblog.web.model;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {

    private UUID id;

    @Size(min = 3, max = 250)
    @NotNull
    private String title;

    @NotNull
    private String body;

    @Null
    private OffsetDateTime createdAt;

    @Null
    private OffsetDateTime updatedAt;

    @URL
    private String img;

    @Null
    private Long views;

    @Null
    private Boolean published;
    private CategoryDTO category;
}
