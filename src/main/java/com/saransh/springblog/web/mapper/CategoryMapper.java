package com.saransh.springblog.web.mapper;

import com.saransh.springblog.domain.Category;
import com.saransh.springblog.web.model.CategoryDTO;
import org.mapstruct.Mapper;

/**
 * Created by CryptSingh1337 on 8/2/2021
 */
@Mapper
public interface CategoryMapper {

    CategoryDTO categoryToCategoryDTO(Category category);
    Category categoryDTOToCategory(Category category);
}
