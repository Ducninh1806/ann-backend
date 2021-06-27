package com.ducninh.ann.repository.dao;

import com.ducninh.ann.service.dto.ProductSearchDTO;

public interface ProductDAO {

    void searchProduct(ProductSearchDTO searchDTO);
}
