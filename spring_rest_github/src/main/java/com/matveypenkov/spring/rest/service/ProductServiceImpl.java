package com.matveypenkov.spring.rest.service;

import com.matveypenkov.spring.rest.dao.ProductDAO;
import com.matveypenkov.spring.rest.dao.ProductDAOImpl;
import com.matveypenkov.spring.rest.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDAO productDAO;


    @Override
    @Transactional
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    @Transactional
    public void saveProduct(Product product) {
        productDAO.saveProduct(product);
    }

    @Override
    @Transactional
    public Product getProduct(int id) {
        return productDAO.getProduct(id);
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }
}
