package com.swp.spring.interiorconstructionquotation.controller;

import com.swp.spring.interiorconstructionquotation.entity.Product;
import com.swp.spring.interiorconstructionquotation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public String viewAllProduct(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product/productList";
    }
    @GetMapping("/{id}")
    public String viewProductDetail(@PathVariable Integer id, Model model){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product/productDetail";
    }
}
