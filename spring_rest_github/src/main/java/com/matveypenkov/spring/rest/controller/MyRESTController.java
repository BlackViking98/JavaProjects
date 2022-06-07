package com.matveypenkov.spring.rest.controller;


import com.matveypenkov.spring.rest.entity.Product;
import com.matveypenkov.spring.rest.exception_handling.NoSuchProductException;
import com.matveypenkov.spring.rest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> showAllProducts(){
        List<Product> allProducts = productService.getAllProducts();
        return allProducts;
    }

    @GetMapping("/products/{id}")
    public Product getProduct (@PathVariable int id){
        Product product = productService.getProduct(id);

        if(product==null){
            throw new NoSuchProductException("There is no product with ID="+id+" in database.");
        }
        return product;
    }

    @PostMapping("/products")
    public Product addNewProduct (@RequestBody Product product){
        productService.saveProduct(product);
        return product;
    }

    @PutMapping("/products")
    public Product updateProduct (@RequestBody Product product){
        productService.saveProduct(product);
        return product;
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable int id){
        Product product = productService.getProduct(id);
        if(product==null){
            throw new NoSuchProductException("There is no product with ID="+id+" in database.");
        }
        productService.deleteProduct(id);
        return "Product with ID="+id+" was deleted!";
    }

}
