package com.ducninh.ann.service;

import com.ducninh.ann.extension.LogicException;
import com.ducninh.ann.service.dto.ProductDTO;
import com.ducninh.ann.service.dto.ProductSearchDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAllProduct();
    ProductDTO getDetailProduct(Long id) throws LogicException;
    void addProduct(ProductDTO productDTO) throws LogicException;
    void updateProduct(ProductDTO productDTO) throws LogicException;
    void deleteProduct(Long id) throws LogicException;

    void searchProduct(ProductSearchDTO searchDTO);
    List<ProductDTO> getTopProductNewActive(int limit);
}
