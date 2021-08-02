package com.saransh.springblog.web.controller;

import com.saransh.springblog.service.CategoryService;
import com.saransh.springblog.web.model.CategoryDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author CryptoSingh1337
 * Created on 8/2/2021
 */
@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(CategoryController.class)
@AutoConfigureRestDocs
class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;

    @Test
    void findAll() throws Exception {
        given(categoryService.findAll()).willReturn(getCategoryList());

        mockMvc.perform(get("/api/v1/category")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(document("category/{methodName}",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("[]").description("An array of categories"),
                                fieldWithPath("[].id").description("Id of the category"),
                                fieldWithPath("[].name").description("Name of the category")
                        )));

        verify(categoryService, times(1)).findAll();
    }

    @Test
    void findById() throws Exception {
        given(categoryService.findById(anyInt())).willReturn(getCategory());

        mockMvc.perform(RestDocumentationRequestBuilders
                        .get("/api/v1/category/{categoryId}", 1)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(document("category/{methodName}",
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("categoryId").description("Id of the category")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Id of the category"),
                                fieldWithPath("name").description("Name of the category")
                        )));
    }

    private List<CategoryDTO> getCategoryList() {
        return List.of(
                CategoryDTO.builder()
                        .id(1)
                        .name("Engineering").build(),
                CategoryDTO.builder()
                        .id(2)
                        .name("Management").build()
        );
    }

    private CategoryDTO getCategory() {
        return CategoryDTO.builder()
                .id(1)
                .name("Engineering").build();
    }
}