package com.ducninh.ann.repository.dao.impl;

import com.ducninh.ann.repository.dao.ProductDAO;
import com.ducninh.ann.service.dto.ProductDTO;
import com.ducninh.ann.service.dto.ProductDetailDTO;
import com.ducninh.ann.service.dto.ProductSearchDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductDAOImpl extends AbstractBaseDAO implements ProductDAO {
    private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

    @Override
    public void searchProduct(ProductSearchDTO searchDTO) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" SELECT DISTINCT ");

        sqlBuilder.append(" p.*, ");
        sqlBuilder.append(" group_concat(DISTINCT s.name) sizesName, ");
        sqlBuilder.append(" group_concat(DISTINCT co.name) colorsName ");

        sqlBuilder.append(" FROM product p ");
        sqlBuilder.append(" LEFT JOIN category ca ON ca.id = p.category_id ");
        sqlBuilder.append(" LEFT JOIN product_size ps ON ps.product_id = p.id ");
        sqlBuilder.append(" LEFT JOIN size s ON s.id = ps.size_id  ");
        sqlBuilder.append(" LEFT JOIN product_color pc ON pc.product_id = p.id ");
        sqlBuilder.append(" LEFT JOIN color co ON co.id = pc.color_id ");

        sqlBuilder.append(" WHERE 1=1");
        Map<String, Object> parameters = new HashMap<>();
        if(StringUtils.isNotBlank(searchDTO.getName())) {
            sqlBuilder.append(" AND p.name LIKE :p_name");
            parameters.put("p_name", "%" + searchDTO.getName().trim() + "%");
        }
        if(searchDTO.getStartPrice() != null) {
            sqlBuilder.append(" AND ifnull(p.price, 0) >= :p_price");
            parameters.put("p_price", searchDTO.getStartPrice());
        }
        if(searchDTO.getEndPrice() != null) {
            sqlBuilder.append(" AND ifnull(p.price, 0) <= :p_price");
            parameters.put("p_price", searchDTO.getEndPrice());
        }
        if(!CollectionUtils.isEmpty(searchDTO.getSizes())) {
            sqlBuilder.append(" AND s.id IN (:p_size_id)");
            parameters.put("p_size_id", searchDTO.getSizes());
        }
        if(!CollectionUtils.isEmpty(searchDTO.getSizes())) {
            sqlBuilder.append(" AND co.id IN (:p_color_id)");
            parameters.put("p_color_id", searchDTO.getColors());
        }

        sqlBuilder.append(" GROUP BY p.id, p.created_date ");
        if(!CollectionUtils.isEmpty(searchDTO.getOrders())) {
            sqlBuilder.append(" ORDER BY ");
            searchDTO.getOrders().forEach(order->{
                String property = StringUtils.trimToEmpty(order.getProperty());
                switch (property){
                    case "newest":
                        sqlBuilder.append(" p.created_date DESC ");
                        break;
                    case "oldest":
                        sqlBuilder.append(" p.created_date ASC ");
                        break;
                    case "random":
                        sqlBuilder.append(" rand() ");
                        break;
                }
            });
        } else {
            sqlBuilder.append(" ORDER BY p.created_date DESC ");
        }
        searchAndCountTotal(searchDTO, sqlBuilder.toString(), parameters, ProductDetailDTO.class);
    }
}
