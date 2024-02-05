package com.swp.spring.interiorconstructionquotation.service.blog;

import com.swp.spring.interiorconstructionquotation.dao.IBlogRepository;
import com.swp.spring.interiorconstructionquotation.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public Blog getBlogById(Integer id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public List<Blog> getRelatedBlogs(Integer id) {
        return blogRepository.findByCategoryId(id);
    }

    @Override
    public Blog createBlog(Blog blog) {
        if (blog.getId() == null){
            blog.setCreatedDate(LocalDateTime.now());
        }
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Blog blog, Integer id) {
        return null;
    }

    @Override
    public void deleteBlog(Integer id) {
        blogRepository.deleteById(id);
    }
}
