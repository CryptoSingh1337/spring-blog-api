package com.saransh.springblog.web.mapper;

import com.saransh.springblog.domain.Post;
import com.saransh.springblog.web.model.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
@Mapper(uses = {DateMapper.class})
public interface PostMapper {

    @Mapping(target = "updatedAt", ignore = true)
    Post postDTOToPost(PostDTO postDTO);

    @Mapping(target = "updatedAt", ignore = true)
    PostDTO postToPostDTO(Post post);
}
