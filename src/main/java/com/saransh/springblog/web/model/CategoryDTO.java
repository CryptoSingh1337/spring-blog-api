package com.saransh.springblog.web.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {

    private Integer id;

    @Size(min = 3, max = 20)
    @NotNull
    private String name;
}
