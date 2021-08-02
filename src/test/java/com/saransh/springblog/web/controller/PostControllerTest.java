package com.saransh.springblog.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saransh.springblog.service.PostService;
import com.saransh.springblog.utility.ConstrainedFields;
import com.saransh.springblog.web.model.CategoryDTO;
import com.saransh.springblog.web.model.PostDTO;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author CryptoSingh1337
 * Created on 7/31/2021
 */

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(PostController.class)
@AutoConfigureRestDocs
class PostControllerTest {

    private final UUID POST_ID = UUID.randomUUID();
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    PostService postService;

    @Test
    void findAll() throws Exception {
        given(postService.findAll(any(Pageable.class))).willReturn(getPostList());

        mockMvc.perform(get("/api/v1/post")
                        .param("page", "0")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(document("post/{methodName}",
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("page").description("Page number of the requested page")
                        ),
                        responseFields(
                                fieldWithPath("[]").description("An array of posts"),
                                fieldWithPath("[].id").description("UUID of the post"),
                                fieldWithPath("[].title").description("Title of the post"),
                                fieldWithPath("[].body").description("Body of the post"),
                                fieldWithPath("[].createdAt")
                                        .description("Date at which post is created"),
                                fieldWithPath("[].updatedAt").type(STRING)
                                        .description("Update date of the post"),
                                fieldWithPath("[].img").type(STRING)
                                        .description("Link of the post image"),
                                fieldWithPath("[].views").type(NUMBER)
                                        .description("No. of views on the post"),
                                fieldWithPath("[].published").type(BOOLEAN)
                                        .description("Show whether the post is published or not"),
                                fieldWithPath("[].category")
                                        .description("Category of the post"),
                                fieldWithPath("[].category.id").description("Id of the category"),
                                fieldWithPath("[].category.name")
                                        .description("Name of the category")
                        )));

        verify(postService, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void findAllByCategory() throws Exception {
        given(postService.findAllByCategory(any(Pageable.class), anyString()))
                .willReturn(getPostList());

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/post/{categoryName}",
                                "Lifestyle")
                        .param("page", "0")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(document("post/{methodName}",
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("categoryName")
                                        .description("Name of the Category to which post belong")
                        ),
                        requestParameters(
                                parameterWithName("page").description("Page number of the requested page")
                        )));

        verify(postService, times(1))
                .findAllByCategory(any(Pageable.class), anyString());
    }

    @Test
    void findById() throws Exception {
        given(postService.findById(any(UUID.class))).willReturn(getPost());

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/post/id/{postId}",
                                POST_ID)
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(document("post/{methodName}",
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("postId").description("UUID of the post")
                        ),
                        responseFields(
                                fieldWithPath("id").description("UUID of the post"),
                                fieldWithPath("title").description("Title of the post"),
                                fieldWithPath("body").description("Body of the post"),
                                fieldWithPath("createdAt")
                                        .description("Date at which post is created"),
                                fieldWithPath("updatedAt").type(STRING)
                                        .description("Update date of the post"),
                                fieldWithPath("img").type(STRING)
                                        .description("Link of the post image"),
                                fieldWithPath("views").type(NUMBER)
                                        .description("No. of views on the post"),
                                fieldWithPath("published").type(BOOLEAN)
                                        .description("Show whether the post is published or not"),
                                fieldWithPath("category")
                                        .description("Category of the post"),
                                fieldWithPath("category.id").description("Id of the category"),
                                fieldWithPath("category.name")
                                        .description("Name of the category")
                        )));

        verify(postService, times(1)).findById(any(UUID.class));
    }

    @Test
    void findAllByTitle() throws Exception {
        given(postService.findAllByTitle(any(Pageable.class), anyString()))
                .willReturn(getPostList());

        mockMvc.perform(get("/api/v1/post/search")
                        .param("title", "Wh")
                        .param("page", "0")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(document("post/{methodName}",
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("title").description("Title of the post that is to be search"),
                                parameterWithName("page").description("Page number of the requested page")
                        )));

        verify(postService, times(1)).findAllByTitle(any(Pageable.class), anyString());
    }

    @Test
    void savePost() throws Exception {
        PostDTO postDTO = getPostToSave();
        String postDTOJson = objectMapper.writeValueAsString(postDTO);
        ConstrainedFields fields = new ConstrainedFields(PostDTO.class);

        given(postService.save(any(PostDTO.class))).willReturn(getPost());

        mockMvc.perform(post("/api/v1/post")
                        .accept("application/json")
                        .contentType("application/json")
                        .content(postDTOJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(header().exists("Location"))
                .andDo(document("post/{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("title").description("Title of the post"),
                                fields.withPath("body").description("Body of the post"),
                                fields.withPath("createdAt").ignored(),
                                fields.withPath("updatedAt").ignored(),
                                fields.withPath("img").description("Link of the post image"),
                                fields.withPath("views").ignored(),
                                fields.withPath("published").ignored(),
                                fields.withPath("category")
                                        .description("Category of the post"),
                                fields.withPath("category.id")
                                        .description("Id of the category"),
                                fields.withPath("category.name")
                                        .description("Name of the category")
                        )));

        verify(postService, times(1)).save(any(PostDTO.class));
    }

    @Test
    void updatePost() throws Exception {
        PostDTO postDTO = getPostToUpdate();
        String postDTOJson = objectMapper.writeValueAsString(postDTO);
        ConstrainedFields fields = new ConstrainedFields(PostDTO.class);

        given(postService.update(any(UUID.class), any(PostDTO.class))).willReturn(getPost());

        mockMvc.perform(RestDocumentationRequestBuilders.put("/api/v1/post/id/{postId}",
                                POST_ID)
                        .accept("application/json")
                        .contentType("application/json").content(postDTOJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(document("post/{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("postId").description("UUID of the post")
                        ),
                        requestFields(
                                fields.withPath("id").description("UUID of the post"),
                                fields.withPath("title").description("Title of the post"),
                                fields.withPath("body").description("Body of the post"),
                                fields.withPath("createdAt")
                                        .description("Date at which post is created"),
                                fields.withPath("updatedAt")
                                        .description("Update date of the post"),
                                fields.withPath("img").description("Link of the post image"),
                                fields.withPath("views").ignored(),
                                fields.withPath("published").ignored(),
                                fields.withPath("category").ignored(),
                                fields.withPath("category.id").ignored(),
                                fields.withPath("category.name").ignored()
                        )));

        verify(postService, times(1)).update(any(UUID.class), any(PostDTO.class));
    }

    private PostDTO getPost() {
        return PostDTO.builder()
                .id(POST_ID)
                .title("What is Lorem Ipsum?")
                .body("Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .img("https://www.google.com")
                .views(15987L)
                .published(true)
                .category(getCategory("News"))
                .build();
    }

    private PostDTO getPostToUpdate() {
        return PostDTO.builder()
                .id(POST_ID)
                .title("What is Lorem Ipsum?")
                .body("Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
                .category(getCategory("News"))
                .build();
    }

    private PostDTO getPostToSave() {
        return PostDTO.builder()
                .title("What is Lorem Ipsum?")
                .body("Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
                .img("https://www.google.com")
                .category(getCategory("News"))
                .build();
    }

    private List<PostDTO> getPostList() {
        return List.of(
                PostDTO.builder()
                        .id(UUID.randomUUID())
                        .title("Where does it come from?")
                        .body("Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots " +
                                "in a piece of classical Latin literature from 45 BC, making it over 2000 years old. " +
                                "Richard McClintock")
                        .createdAt(OffsetDateTime.now())
                        .updatedAt(OffsetDateTime.now())
                        .img("https://www.google.com")
                        .views(15987L)
                        .published(true)
                        .category(getCategory("Lifestyle"))
                        .build(),
                PostDTO.builder()
                        .id(UUID.randomUUID())
                        .title("What is Lorem Ipsum?")
                        .body("Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
                        .createdAt(OffsetDateTime.now())
                        .updatedAt(OffsetDateTime.now())
                        .img("https://www.google.com")
                        .views(15987L)
                        .published(true)
                        .category(getCategory("Lifestyle"))
                        .build()
        );
    }

    private CategoryDTO getCategory(String name) {
        return CategoryDTO.builder()
                .id(1)
                .name(name)
                .build();
    }
}