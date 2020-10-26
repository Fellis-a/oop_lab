package com.company;

import java.util.Date;

public class Bakery extends Product {
    private String Filling;
    private static int Quantity = 1;

    public Bakery(String code, String cost, String weight, String name, String brand, String filling) {
        super(code,cost,weight,brand, name);
        this.Filling = filling;
        setTypeOfProduct("Выпечка");
        this.Number = getQuantity();
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

        return date.toString()+1;
    }


    public String isFilling() {
        return Filling;
    }

    public void setFilling(String  filling) {
        Filling = filling;
    }

    @Override
    public void getInfo()
    {
        System.out.println( "Товар: " + getName()+ super.getBrand()+ "\n\t Стоимость: " + getCost() );
    }
    @Override
    public String learnMore()
    {
        return "Выпечка:"  + getName() + " "+getBrand()+
                "\n\tСтоимость: " + getCost() + "\n\t Срок годности: " + getQuantity()+" \n\t Вес: " + super.getWeight();

    }

    @Override
    public String toString() {
        return this.learnMore();
    }
}

