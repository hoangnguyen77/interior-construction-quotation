package com.swp.spring.interiorconstructionquotation.service;

import com.swp.spring.interiorconstructionquotation.entity.Blog;

import java.util.List;

public interface IBlogService {
    //get all blog
    List<Blog> getAllBlogs();
    //get blog by id
    Blog getBlogById(Integer id);
    //get related blog
    List<Blog> getRelatedBlogs(Integer categoryId);
    //create new blog
    Blog createBlog(Blog blog);
    //update blog
    Blog updateBlog(Blog blog, Integer id);
    //delete blog
    public void deleteBlog(Integer id);
}
