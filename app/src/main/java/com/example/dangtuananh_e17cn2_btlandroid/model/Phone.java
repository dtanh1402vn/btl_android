package com.example.dangtuananh_e17cn2_btlandroid.model;

public class Phone {
    private String key;
    private String name;
    private String manufacturer;
    private int year;
    private int price;

    public Phone() {
    }

    public Phone(String key, String name, String manufacturer, int year, int price) {
        this.key = key;
        this.name = name;
        this.manufacturer = manufacturer;
        this.year = year;
        this.price = price;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
