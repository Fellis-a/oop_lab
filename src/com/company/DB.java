package com.company;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
//класс для базы данных
public class DB {
    public static Connection conn;
    public static Statement statement;
    public static ResultSet data;

    // --------Соединение с бд--------
    public static void Connecting() throws ClassNotFoundException, SQLException {
        conn = null;
        DriverManager.registerDriver(new JDBC());
        conn = DriverManager.getConnection("jdbc:sqlite:Warehouse.db");

        System.out.println("Подключение осуществлено!");
    }

    // --------Закрытие бд--------
    public static void CloseDB() throws ClassNotFoundException, SQLException {
        data.close();
        statement.close();
        conn.close();//закрываем соединение

        System.out.println("Соединения закрыты");
    }

    // --------Чтение бд--------
    public static ArrayList<Product> read() throws ClassNotFoundException, SQLException {

        ArrayList<Product> list = new ArrayList<>();

        statement = conn.createStatement();//создаем экземпляр statement

        data = statement.executeQuery(String.format("SELECT * FROM product"));//получаем список всех объектов, наодящихся в бд

        while (data.next()) {
            int id = data.getInt("id");
            String type = data.getString("type");
            String code = data.getString("code");
            String name = data.getString("name");
            String brand = data.getString("brand");
            String cost = data.getString("cost");
            String weight = data.getString("weight");
            // String  filling = data.getString("filling");


            if (type.equals("Бытовая химия")) {
                Household household = new Household(id, code, name, brand, cost, weight);
                list.add(household);
            } else if (type.equals("Молочные продукты")) {

                Dairy dairy = new Dairy(id, code, name, brand, cost, weight);
                list.add(dairy);
            } else if (type.equals("Выпечка")) {

                Bakery bakery = new Bakery(id, code, name, brand, cost, weight);
                list.add(bakery);
            }

        }
        return list;
    }


    //метод добавления записей в бд, разделенных по типам товаров
    public void add(Product product) throws SQLException {

        switch (product.getTypeOfProduct()) {
            case "Бытовая химия":
                addHouse(product);
                break;

            case "Молочные продукты":
                addDairy(product);
                break;
            case "Выпечка":
                addBake(product);
                break;
        }
    }

    //добавление товаров в бд, относящихся к бытовой химии
    public void addHouse(Product product) throws SQLException {

        //подготовленный запрос на добавление
        PreparedStatement statement = this.conn.prepareStatement(
                "INSERT INTO product ('type', 'code', 'name', 'brand', 'cost', 'weight') VALUES(?, ?, ?, ?, ?,?)");

        //устанавливаем значения определяемых параметров
        statement.setObject(1, product.getTypeOfProduct());
        statement.setObject(2, product.getCode());
        statement.setObject(3, product.getName());
        statement.setObject(4, product.getBrand());
        statement.setObject(5, product.getCost());
        statement.setObject(6, product.getWeight());
        //statement.setObject(8, info);
        // Выполняем запрос
        statement.execute();
    }

    //добавление товаров в бд, относящихся к молочным продуктам
    public void addDairy(Product product) throws SQLException {
        //подготовленный запрос на добавление
        PreparedStatement statement = this.conn.prepareStatement(
                "INSERT INTO product ('type', 'code', 'name', 'brand', 'cost', 'weight') VALUES(?, ?, ?, ?, ?,?)");

        statement.setObject(1, product.getTypeOfProduct());
        statement.setObject(2, product.getCode());
        statement.setObject(3, product.getName());
        statement.setObject(4, product.getBrand());
        statement.setObject(5, product.getCost());
        statement.setObject(6, product.getWeight());
        //statement.setObject(8, Dairy.issFilling());

        // Выполняем запрос
        statement.execute();


    }

    //добавление товаров в бд, относящихся к выпечке
    public void addBake(Product product) throws SQLException {
        //подготовленный запрос на добавление
        PreparedStatement statement = this.conn.prepareStatement(
                "INSERT INTO product ('type', 'code', 'name', 'brand', 'cost', 'weight') VALUES(?, ?, ?, ?, ?, ?)");

        statement.setObject(1, product.getTypeOfProduct());
        statement.setObject(2, product.getCode());
        statement.setObject(3, product.getName());
        statement.setObject(4, product.getBrand());
        statement.setObject(5, product.getCost());
        statement.setObject(6, product.getWeight());
        // Выполняем запрос
        statement.execute();


    }

    public void delete(int id) throws SQLException {
        //подготовленный запрос на удаление
        PreparedStatement statement = conn.prepareStatement(
                "DELETE FROM product WHERE id = ? "
        );
        statement.setObject(1, id);

        // Выполняем запрос
        statement.execute();
    }

}
