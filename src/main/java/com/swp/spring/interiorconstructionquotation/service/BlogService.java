package com.swp.spring.interiorconstructionquotation.service;

import com.swp.spring.interiorconstructionquotation.dao.IBlogRepository;
import com.swp.spring.interiorconstructionquotation.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class BlogService implements IBlogService{
    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public List<Blog> getRelatedBlogs(Long id) {
        Blog blog = blogRepository.findById(id).orElse(null);
        if(blog == null){
            return Collections.emptyList();
        }
        return blogRepository.findByCategoryId(blog.getCategoryId());
    }

    @Override
    public Blog save(Blog blog) {
        if (blog.getId() == null){
            blog.setCreatedDate(LocalDateTime.now());
        }
        return blogRepository.save(blog);
    }
}
