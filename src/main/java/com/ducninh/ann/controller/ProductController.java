package com.ducninh.ann.controller;

import com.ducninh.ann.extension.LogicException;
import com.ducninh.ann.model.Product;
import com.ducninh.ann.service.dto.ProductDTO;
import com.ducninh.ann.service.dto.ProductSearchDTO;
import com.ducninh.ann.service.impl.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAllProduct(){
        logger.info("Controller info about findAll Product");
        List<ProductDTO> productDTOList= productService.findAllProduct();
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getDetailProduct(@PathVariable Long id) throws LogicException {
        logger.info("Controller info about findById Product, {}", id);
        ProductDTO productDTO = productService.getDetailProduct(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) throws LogicException {
        logger.info("Controller info about create Product, {}", productDTO);
        productService.addProduct(productDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) throws LogicException {
        logger.info("Controller info about update Product, {}", productDTO);
        productService.updateProduct(productDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id) throws LogicException {
        logger.info("Controller info about delete Product, {}", id);
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<ProductSearchDTO> searchProduct(@RequestBody ProductSearchDTO searchDTO){
        logger.info("Controller info about search Product, {}", searchDTO);
        productService.searchProduct(searchDTO);
        return new ResponseEntity<>(searchDTO,HttpStatus.OK);
    }
}
