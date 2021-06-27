package com.ducninh.ann.service.impl;

import com.ducninh.ann.extension.ErrorCode;
import com.ducninh.ann.extension.LogicException;
import com.ducninh.ann.model.Category;
import com.ducninh.ann.repository.jpa.CategoryRepository;
import com.ducninh.ann.service.CategoryService;
import com.ducninh.ann.service.dto.CategoryDTO;
import com.ducninh.ann.utils.CommonUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Value("${location.resource}")
    private String locationResource;

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDTO> findAllCategory() {
        logger.info("Service info about findAll Category");
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findByIdCategory(Long id) throws LogicException {
        logger.info("Service info about findById Category, {}", id);
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()){
            throw new LogicException(ErrorCode.RECORD_NOT_EXISTED);
        }
        CategoryDTO categoryDTO = modelMapper.map(categoryOptional.get(), CategoryDTO.class);
        return categoryDTO;
    }

    @Override
    public void addCategory(CategoryDTO categoryDTO) throws LogicException, IOException {
        logger.info("Service info about Create Category, {}", categoryDTO);
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryDTO.getName());
        if (categoryOptional.isPresent()){
            throw new LogicException(ErrorCode.NAME_EXISTED);
        }
        Category category = modelMapper.map(categoryDTO, Category.class);
        if (categoryDTO.getImageCategory()!= null){
            category.setPathImage(CommonUtils.upload(locationResource, "image_category", categoryDTO.getImageCategory() ));
        }

        category.setCreatedBy("user login by admin");
        category.setCreatedDate(new Date());
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) throws LogicException, IOException {
        logger.info("Service info about update Category, {}", categoryDTO);
        Optional<Category> categoryOptional = categoryRepository.findById(categoryDTO.getId());
        if (!categoryOptional.isPresent()){
            throw new LogicException(ErrorCode.RECORD_NOT_EXISTED);
        }
        Category category = modelMapper.map(categoryDTO, Category.class);
        if (categoryDTO.getImageCategory() != null) {
            category.setPathImage(CommonUtils.upload(locationResource, "image_category", categoryDTO.getImageCategory()));
        }
        category.setLastModifiedBy("user login by admin");
        category.setLastModifiedDate(new Date());

        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) throws LogicException {
        logger.info("Service info about delete Product, {}", id);
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()){
            throw new LogicException(ErrorCode.RECORD_NOT_EXISTED);
        }
        categoryRepository.delete(category.get());
    }
}
