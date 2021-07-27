package com.saransh.springblog.service;

import com.saransh.springblog.web.model.PostDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */

public interface PostService {
    List<PostDTO> findAll(Pageable pageable);
}
