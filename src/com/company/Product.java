package com.company;

public abstract class Product {
    private String typeOfProduct;
    private String Cost;
    private String Weight;
    private String Brand;
    private String Name;
    private String Code;

    protected int Number;

    public Product(String code, String cost, String weight, String brand, String name ){
        this.Code = code;
        this.Cost = cost;
        this.Weight = weight;
        this.Brand = brand;
        this.Name = name;
    }

    //абстрактный класс
    public abstract String expirationDate();


    public String getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(String typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String  getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
    public String learnMore()
    {
        return "Продукт: " + getCode()+ "Вес: "+ getWeight()+"\n\t Стоимость: " + getCost();
    }
    abstract public void getInfo();//Получить информацию о товаре

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

