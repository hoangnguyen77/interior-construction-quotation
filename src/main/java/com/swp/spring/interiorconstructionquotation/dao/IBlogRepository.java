package com.swp.spring.interiorconstructionquotation.dao;

import com.swp.spring.interiorconstructionquotation.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  IBlogRepository extends JpaRepository<Blog,Integer> {
List<Blog> findByCategoryId(Integer categoryId);
}
