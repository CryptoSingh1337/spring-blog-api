package com.saransh.springblog.web.mapper;

import com.saransh.springblog.domain.Comment;
import com.saransh.springblog.web.model.CommentDTO;
import org.mapstruct.Mapper;

/**
 * Created by CryptoSingh1337 on 7/30/2021
 */
@Mapper(uses = {DateMapper.class})
public interface CommentMapper {

    Comment commentDTOToComment(CommentDTO commentDTO);
    CommentDTO postToPostDTO(Comment comment);
}
