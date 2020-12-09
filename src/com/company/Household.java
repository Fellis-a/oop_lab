package com.company;

import java.util.Date;

public class Household extends Product {

    private String Name;
    private static int Quantity = 1;

    public Household(int id, String code, String name, String brand, String cost, String weight) {
        super(id, code, name, brand, cost);
        this.Name = name;
        this.setWeight(weight);
        setTypeOfProduct("Бытовая химия");
        setQuantity(getQuantity() + 1);
    }

    public static int getQuantity() {
        return Quantity;
    }

    public static void setQuantity(int quantity) {
        Quantity = quantity;
    }

    @Override
    public String expirationDate() {
        Date date = new Date();
        return date.toString() + 1;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public void getInfo() {
        System.out.println("Товар: " + getName() + super.getBrand() + "\n\t Стоимость: " + getCost());
    }

    @Override
    public String learnMore() {
        return "Бытовая химия:" + getName() + " " + getBrand() +
                "\n\tСтоимость: " + getCost() + "\n\t Срок годности: " + getQuantity() + " \n\t Вес: " + super.getWeight();

    }

    @Override
    public String toString() {
        return this.learnMore();
    }
}

