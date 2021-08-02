package com.saransh.springblog.service;

import com.saransh.springblog.web.model.CategoryDTO;

import java.util.List;

/**
 * Created by CryptSingh1337 on 8/2/2021
 */
public interface CategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findById(int categoryId);
}
