package com.matveypenkov.spring.rest.dao;

import com.matveypenkov.spring.rest.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> getAllProducts() {
        Session session = sessionFactory.getCurrentSession();
        List<Product> allProducts = session.createQuery("from Product", Product.class).getResultList();
        return allProducts;
    }

    @Override
    public void saveProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
    }

    @Override
    public Product getProduct(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class,id);
        return product;
    }

    @Override
    public void deleteProduct(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("delete from Product where id =:productId");
        query.setParameter("productId", id);
        query.executeUpdate();
    }
}
