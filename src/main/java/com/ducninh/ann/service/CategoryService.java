package com.ducninh.ann.service;

import com.ducninh.ann.extension.LogicException;
import com.ducninh.ann.model.Category;
import com.ducninh.ann.service.dto.CategoryDTO;

import java.io.IOException;
import java.util.List;

public interface CategoryService  {

    List<CategoryDTO> findAllCategory();

    CategoryDTO findByIdCategory(Long id) throws LogicException;

    void addCategory(CategoryDTO categoryDTO) throws LogicException, IOException;

    void updateCategory(CategoryDTO categoryDTO) throws LogicException, IOException;

    void deleteCategory(Long id) throws LogicException;
}
