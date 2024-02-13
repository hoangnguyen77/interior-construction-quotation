package com.swp.spring.interiorconstructionquotation.service;

import com.swp.spring.interiorconstructionquotation.entity.Product;
import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product getProductById(Integer id);
    //get related product
    List<Product> getRelatedProducts(Integer categoryId);
    //create new product
    Product createProduct(Product product);
    //update product
    Product updateProduct(Product product, Integer id);
    //delete product
    public void deleteProduct(Integer id);
}
