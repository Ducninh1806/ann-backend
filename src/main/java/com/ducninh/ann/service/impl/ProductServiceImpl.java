package com.ducninh.ann.service.impl;

import com.ducninh.ann.extension.ErrorCode;
import com.ducninh.ann.extension.LogicException;
import com.ducninh.ann.model.Product;
import com.ducninh.ann.repository.dao.impl.ProductDAOImpl;
import com.ducninh.ann.repository.jpa.ProductRepository;
import com.ducninh.ann.service.ProductService;
import com.ducninh.ann.service.dto.ProductDTO;
import com.ducninh.ann.service.dto.ProductSearchDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private ProductDAOImpl productDAO;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ProductDAOImpl productDAO) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.productDAO = productDAO;
    }

    @Override
    public List<ProductDTO> findAllProduct() {
        logger.info("Service info about findAll Product");
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = productList.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return productDTOList;
    }

    @Override
    public ProductDTO getDetailProduct(Long id) throws LogicException {
        logger.info("Service info about get detail Product :{}", id);
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()){
            throw new LogicException(ErrorCode.RECORD_NOT_EXISTED);
        }
        return modelMapper.map(productOptional.get(), ProductDTO.class);
    }

    @Override
    public void addProduct(ProductDTO productDTO) throws LogicException {
        logger.info("Service info about get create Product :{}", productDTO);
        Optional<Product> productCodeExist = productRepository.findByCode(productDTO.getCode());
        if (productCodeExist.isPresent()){
            throw  new LogicException(ErrorCode.CODE_EXISTED);
        }
        Product product = modelMapper.map(productDTO, Product.class);
        product.setCreatedDate(new Date());
        product.setCreatedBy("user login create");
        productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductDTO productDTO) throws LogicException {
        logger.info("Service info about get update Product :{}", productDTO);
        Optional<Product> productCodeExist = productRepository.findByCode(productDTO.getCode());
        if (!productCodeExist.isPresent()){
            throw  new LogicException(ErrorCode.RECORD_NOT_EXISTED);
        }
        Product product = modelMapper.map(productDTO, Product.class);
        product.setLastModifiedDate(new Date());
        product.setLastModifiedBy("user login update");
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) throws LogicException {
        logger.info("Service info about get delete Product :{}", id);
        Optional<Product> productCodeExist = productRepository.findById(id);
        if (!productCodeExist.isPresent()){
            throw new LogicException(ErrorCode.RECORD_NOT_EXISTED);
        }
        productRepository.delete(productCodeExist.get());
    }

    @Override
    public void searchProduct(ProductSearchDTO searchDTO) {
        logger.info("Service info about get search Product :{}", searchDTO);
        productDAO.searchProduct(searchDTO);
    }

    @Override
    public List<ProductDTO> getTopProductNewActive(int limit) {
        return null;
    }


}
