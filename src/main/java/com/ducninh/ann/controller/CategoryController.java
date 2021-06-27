package com.ducninh.ann.controller;

import com.ducninh.ann.extension.LogicException;
import com.ducninh.ann.service.CategoryService;
import com.ducninh.ann.service.dto.CategoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAllCategory(){
        logger.info("Controller: Controller info about findAll Category");
        List<CategoryDTO> categoryDTOS = categoryService.findAllCategory();
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findByIdCategory(@PathVariable Long id) throws LogicException {
        logger.info("Controller: Controller info about findById Category, {}", id);
        CategoryDTO categoryDTO = categoryService.findByIdCategory(id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@ModelAttribute CategoryDTO categoryDTO) throws LogicException, IOException {
        logger.info("Controller: Controller info about create Category, {}", categoryDTO);
        categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> updateCategory(@ModelAttribute CategoryDTO categoryDTO) throws LogicException, IOException {
        logger.info("Controller: Controller info about update Category, {}", categoryDTO);
        categoryService.updateCategory(categoryDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long id) throws LogicException {
        logger.info("Controller: Controller info about delete Category, {}", id);
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
