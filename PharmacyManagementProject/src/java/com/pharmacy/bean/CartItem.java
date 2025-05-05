package com.pharmacy.bean;
public class CartItem {
    private int medicine_id;
    private String name;
    private double total_price;
    private int quantity;

    public CartItem(int medicine_id, String name, double total_price, int quantity) {
        this.medicine_id = medicine_id;
        this.name = name;
        this.total_price = total_price;
        this.quantity = quantity;
    }

    public int getMedicine_id() {
        return medicine_id;
    }

    public String getName() {
        return name;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
