package com.pharmacy.bean;

public class OrderItemBean {
    private int order_item_id;
    private int order_id;
    private int medicine_id;
    private int quantity;
    private double total_price;
    private String name;

    // Default constructor
    public OrderItemBean() {
    
    }

    // Parameterized constructor
    public OrderItemBean(int order_item_id, int order_id, int medicine_id, int quantity, double total_price, String name) {
        this.order_item_id = order_item_id;
        this.order_id = order_id;
        this.medicine_id = medicine_id;
        this.quantity = quantity;
        this.total_price = total_price;
        this.name = name;
    }

    // Getters and setters
    public int getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(int order_item_id) {
        this.order_item_id = order_item_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
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
