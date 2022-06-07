package com.matveypenkov.spring.rest.exception_handling;

public class ProductIncorrectData {
    private String info;

    public ProductIncorrectData(){
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
