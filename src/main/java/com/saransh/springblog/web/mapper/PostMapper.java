package com.saransh.springblog.web.mapper;

import com.saransh.springblog.domain.Post;
import com.saransh.springblog.web.model.PostDTO;
import org.mapstruct.Mapper;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
@Mapper(uses = {DateMapper.class})
public interface PostMapper {

    Post postDTOToPost(PostDTO postDTO);
    PostDTO postToPostDTO(Post post);
}
