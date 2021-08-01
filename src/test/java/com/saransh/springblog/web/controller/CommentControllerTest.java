package com.saransh.springblog.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saransh.springblog.service.CommentService;
import com.saransh.springblog.utility.ConstrainedFields;
import com.saransh.springblog.web.model.CommentDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author CryptoSingh1337
 * Created on 7/31/2021
 */
@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(CommentController.class)
@AutoConfigureRestDocs
class CommentControllerTest {

    private final UUID POST_ID = UUID.randomUUID();
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    CommentService commentService;

    @Test
    void findAllByPostId() throws Exception {
        given(commentService.findAllByPostId(any(Pageable.class), any(UUID.class)))
                .willReturn(getCommentSet());

        mockMvc.perform(RestDocumentationRequestBuilders
                        .get("/api/v1/comment/{postId}", POST_ID)
                        .accept("application/json")
                        .contentType("application/json")
                        .param("page", "0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(document("comment/{methodName}",
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("postId").description("UUID of the post")
                        ),
                        requestParameters(
                                parameterWithName("page").description("Page number of the requested page")
                        ),
                        responseFields(
                                fieldWithPath("[]").description("An array of comments"),
                                fieldWithPath("[].id").description("Id of the comment"),
                                fieldWithPath("[].username")
                                        .description("Username of the user who created the comment"),
                                fieldWithPath("[].body").description("Body of the comment"),
                                fieldWithPath("[].createdAt").description("Date at which comment is created")
                        )));

        verify(commentService, times(1))
                .findAllByPostId(any(Pageable.class), any(UUID.class));
    }

    @Test
    void findById() throws Exception {
        given(commentService.findById(any(UUID.class), anyLong())).willReturn(getComment());

        mockMvc.perform(RestDocumentationRequestBuilders
                        .get("/api/v1/comment/{postId}/{commentId}", POST_ID, 1L)
                        .accept("application/json")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(document("comment/{methodName}",
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("postId").description("UUID of the post"),
                                parameterWithName("commentId").description("Id of the post")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Id of the comment"),
                                fieldWithPath("username")
                                        .description("Username of the user who created the comment"),
                                fieldWithPath("body").description("Body of the comment"),
                                fieldWithPath("createdAt").description("Date at which comment is created")
                        )));

        verify(commentService, times(1)).findById(any(UUID.class), anyLong());
    }

    @Test
    void save() throws Exception {
        CommentDTO commentDTO = getCommentToSave();
        String commentDTOJson = objectMapper.writeValueAsString(commentDTO);
        ConstrainedFields fields = new ConstrainedFields(CommentDTO.class);

        given(commentService.save(any(UUID.class), any(CommentDTO.class)))
                .willReturn(getComment());

        mockMvc.perform(RestDocumentationRequestBuilders
                        .post("/api/v1/comment/{postId}", POST_ID)
                        .accept("application/json")
                        .contentType("application/json")
                        .content(commentDTOJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(document("comment/{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("postId").description("UUID of the Post")
                        ),
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("username")
                                        .description("Username of the user who created the comment"),
                                fields.withPath("body").description("Body of the comment"),
                                fields.withPath("createdAt").ignored()
                        )));

        verify(commentService, times(1)).save(any(UUID.class), any(CommentDTO.class));
    }

    @Test
    void update() throws Exception {
        CommentDTO commentDTO = getCommentToUpdate();
        String commentDTOJson = objectMapper.writeValueAsString(commentDTO);
        ConstrainedFields fields = new ConstrainedFields(CommentDTO.class);

        given(commentService.update(any(UUID.class), any(CommentDTO.class)))
                .willReturn(getComment());

        mockMvc.perform(RestDocumentationRequestBuilders
                        .put("/api/v1/comment/{postId}", POST_ID)
                        .accept("application/json")
                        .contentType("application/json")
                        .content(commentDTOJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(document("comment/{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("postId").description("UUID of the post")
                        ),
                        requestFields(
                                fields.withPath("id").description("Id of the comment"),
                                fields.withPath("username")
                                        .description("Username of the user who created the comment"),
                                fields.withPath("body").description("Body of the comment"),
                                fields.withPath("createdAt").description("Date at which comment is created")
                        )));

        verify(commentService, times(1)).update(any(UUID.class), any(CommentDTO.class));
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders
                        .delete("/api/v1/comment/{postId}", POST_ID)
                        .param("commentId", "1")
                        .accept("application/json")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(document("comment/{methodName}",
                        pathParameters(
                                parameterWithName("postId").description("UUID of the post")
                        ),
                        requestParameters(
                                parameterWithName("commentId").description("Id of the comment")
                        )));

        verify(commentService, times(1)).delete(any(UUID.class), anyLong());
    }

    private CommentDTO getComment() {
        return CommentDTO.builder()
                .id(1L)
                .username("Anonymous")
                .body("Lorem Ipsum")
                .createdAt(OffsetDateTime.now().plusSeconds(12))
                .build();
    }

    private CommentDTO getCommentToSave() {
        return CommentDTO.builder()
                .username("Anonymous")
                .body("Lorem Ipsum")
                .build();
    }

    private CommentDTO getCommentToUpdate() {
        return CommentDTO.builder()
                .id(1L)
                .username("Anonymous")
                .body("Lorem Ipsum")
                .createdAt(OffsetDateTime.now())
                .build();
    }

    private List<CommentDTO> getCommentSet() {
        return List.of(
                CommentDTO.builder()
                        .id(1L)
                        .username("Anonymous")
                        .body("Lorem Ipsum")
                        .createdAt(OffsetDateTime.now())
                        .build(),
                CommentDTO.builder()
                        .id(2L)
                        .username("Anonymous")
                        .body("Lorem Ipsum is a dummy text")
                        .createdAt(OffsetDateTime.now())
                        .build()
        );
    }
}