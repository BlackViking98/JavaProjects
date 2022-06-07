package com.matveypenkov.spring.rest.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "article")
    private String article;
    @Column(name = "trademark")
    private String trademark;
    @Column(name = "model")
    private String model;
    @Column(name = "sort")
    private String sort;
    @Column(name = "price")
    private int price;
    @Column(name = "amount")
    private int amount;

    public Product() {
    }

    public Product(String article, String trademark, String model, String sort, int price, int amount) {
        this.article = article;
        this.trademark = trademark;
        this.model = model;
        this.sort = sort;
        this.price = price;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
