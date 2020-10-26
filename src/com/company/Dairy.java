package com.company;

import java.util.Date;

public class Dairy extends Product {

    private String Filling;
    private static int Quantity = 1;

    public Dairy(String code, String cost, String weight, String name, String brand, String  filling) {
        super(code,cost,weight,brand,name);
        this.Filling = filling;
        setTypeOfProduct("Молочные продукты");
        this.Number = getQuantity();
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
    public void getInfo()
    {
        System.out.println( "Товар: " + getName()+ super.getBrand()+ "\n\t Стоимость: " + getCost() );
    }
    @Override
    public String learnMore()
    {
        return "Молочные продукты:"  + getName() + " "+getBrand()+
                "\n\tСтоимость: " + getCost() + "\n\t Срок годности: " + getQuantity()+" \n\t Срок годности: " + super.getWeight();

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


