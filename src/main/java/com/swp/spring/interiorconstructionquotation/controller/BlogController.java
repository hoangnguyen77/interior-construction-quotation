package com.swp.spring.interiorconstructionquotation.controller;

import com.swp.spring.interiorconstructionquotation.entity.Blog;
import com.swp.spring.interiorconstructionquotation.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @GetMapping
    public String viewAllBLogs(Model model){
        List<Blog> blogs = blogService.getAllBlogs();
        model.addAttribute("blogs", blogs);
        return "blog/blogList";
    }
    @GetMapping("/{id}")
    public String viewBlogDetail(@PathVariable Long id, Model model){
        Blog blog = blogService.getBlogById(id);
        List<Blog> relatedBlogs = blogService.getRelatedBlogs(blog.getCategoryId());

        model.addAttribute("blog",blog);
        model.addAttribute("relatedBlogs", relatedBlogs);
        return  "blog/blogDetail";
    }


}
