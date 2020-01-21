package com.ebpm.common.pojo;

/**
 * 产品实体类 
 * @author pengfei.xiong
 * @date 2017年11月16日
 */
public class Product {
    private int id;
    private String name;
    private int count;
    private double price;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public Product() {
        super();
    }
    public Product(int id, String name, int count, double price) {
        super();
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
    }

}