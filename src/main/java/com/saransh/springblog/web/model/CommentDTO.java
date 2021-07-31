package com.saransh.springblog.web.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {

    private Long id;

    @Size(min = 3, max = 50)
    @NotNull
    private String username;

    @NotNull
    private String body;

    @Null
    private OffsetDateTime createdAt;
}
