
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.bean;

/**
 *
 * @author LENOVO
 */
public class OrderBean {
    private int order_id;
    private int user_id;
    private int medicine_id;
    private int quantity;
    private double total_price;
    private String order_status;
    private String name;

    // Constructor
    public OrderBean(int order_id,int user_id, int medicine_id, int quantity, double total_price, String order_status, String name) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.medicine_id = medicine_id;
        this.quantity = quantity;
        this.total_price = total_price;
        this.order_status = order_status;
        this.name = name;
    }

    

    public OrderBean() {
       
    }

    // Getters and Setters
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    
}
