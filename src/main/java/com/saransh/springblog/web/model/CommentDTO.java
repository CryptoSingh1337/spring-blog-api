package com.saransh.springblog.web.model;

import lombok.*;

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
    private String username;
    private String body;
    private OffsetDateTime createdAt;
}
