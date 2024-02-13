package com.swp.spring.interiorconstructionquotation.dao;

import com.swp.spring.interiorconstructionquotation.entity.Blog;
import com.swp.spring.interiorconstructionquotation.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByCategoryId(Integer categoryId);
}
