package com.saransh.springblog.boostrap;

import com.saransh.springblog.domain.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by CryptoSingh1337 on 7/27/2021
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
