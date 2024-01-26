package com.swp.spring.interiorconstructionquotation.service;

import com.swp.spring.interiorconstructionquotation.entity.Blog;

import java.util.List;

public interface IBlogService {
    List<Blog> getAllBlogs();
    Blog getBlogById(Long id);
    List<Blog> getRelatedBlogs(Long id);
    Blog save(Blog blog);
}
