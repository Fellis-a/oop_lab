package com.company;

import java.util.Date;

public class Dairy extends Product {

    private String Filling;
    private static int Quantity = 1;

    public Dairy(int id, String code, String name, String brand, String cost, String weight) {
        super(id, code, name, brand, cost);
        setTypeOfProduct("Молочные продукты");
        this.setWeight(weight);
        setQuantity(getQuantity() + 1);
    }

    public static int getQuantity() {
        return Quantity;
    }


    public static void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String issFilling() {
        return Filling;
    }


    @Override
    public String expirationDate() {
        Date date = new Date();
        return date.toString();
    }


    @Override
    public void getInfo() {
        System.out.println("Товар: " + getName() + super.getBrand() + "\n\t Стоимость: " + getCost());
    }

    @Override
    public String learnMore() {
        return " Молочные продукты:" + getName() + " " + getBrand() +
                "\n\tСтоимость: " + getCost() + "\n\t Срок годности: " + getQuantity() + " \n\t Срок годности: " + super.getWeight();

    }

    @Override
    public String toString() {
        return this.learnMore();
    }

    public String getFilling() {
        return Filling;
    }

    public void setFilling(String filling) {
        Filling = filling;
    }
}


