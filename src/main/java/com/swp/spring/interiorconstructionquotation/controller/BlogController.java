package com.swp.spring.interiorconstructionquotation.controller;

import com.swp.spring.interiorconstructionquotation.entity.Blog;
import com.swp.spring.interiorconstructionquotation.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @GetMapping
    public String viewAllBLogs(Model model){
        //get list of all blog id
        List<Blog> blogs = blogService.getAllBlogs();
        model.addAttribute("blogs", blogs);
        return "blog/blogList";
    }
    @GetMapping("/{id}")
    public String viewBlogDetail(@PathVariable Integer id, Model model){
        //get blog id for view
        Blog blog = blogService.getBlogById(id);
        //if blog id exist
        if(blog != null){
            //get list related blog by category id
            List<Blog> relatedBlogs = blogService.getRelatedBlogs(blog.getCategoryId());
            //remove current view blog out of the related list
            relatedBlogs.remove(blog);
            //model attribute
            model.addAttribute("blog",blog);
            model.addAttribute("relatedBlogs", relatedBlogs);
            return  "blog/blogDetail";
        } else {
            return "error/404";
        }
    }


}
