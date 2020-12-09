package Interface;

import com.company.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class StuffTable extends AbstractTableModel {
    ArrayList<Product> products = new ArrayList<>();
    private final DB db = new DB();


    public String TypeOfProduct = "";//Долж
    public String Code = "";//Долж


    public StuffTable() {
        super();
        try {
            db.Connecting();

            products = db.read();

            db.CloseDB();
            System.out.println("Данные считаны.Подключения закрыты!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Подключения закрыты!");
        }
    }

    public void Add(Product s) {

        try {
            db.Connecting();

            db.add(s);//добавить в дб
            products.add(s);//добавить в табл

            db.CloseDB();
            System.out.println("Запись добавлена.Подключение закрыто!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Подключение закрыто!");
        }
        this.fireTableDataChanged();
    }

    public void search(String code) {

        for (Product s : this.getProduct()) {
            if (s.getName().equals(code)) {
                products.add(s);
                this.fireTableDataChanged();

            }
        }

    }

    public void searchName(String name) {
        ArrayList<Product> res = new ArrayList<>();
        for (Product s : this.getProduct()) {
            if (s.getName().equals(name)) {
                res.add(s);
            }
        }
        String found = "<html><ul>";
        for (Product s : res) {
            found += "<li>" + s.getTypeOfProduct() + " " + s.getCode() + " " + s.getName() + " " + s.getBrand() + "</li>";
        }
        found += "</ul><html>";

        if (res.size() > 1) {
            Object result = JOptionPane.showInputDialog(
                    null,
                    new String[]{"По вашему запросу найдено несколько товаров:",
                            found},
                    "Результат поиска");
            for (Product s : res) {
                if (s.getTypeOfProduct() == result) {
                    JOptionPane.showMessageDialog(null,
                            s.learnMore(),
                            "Товар",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }

        } else if (res.size() == 1) {
            for (Product s : res) {
                {
                    JOptionPane.showMessageDialog(null,
                            s.learnMore(),
                            "Товар",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Товар не найден.",
                    "Не найдено",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public String getType() {
        return TypeOfProduct;
    }

    public void setType(String typeOfProduct) {
        this.TypeOfProduct = typeOfProduct;
        this.fireTableStructureChanged();
    }


    public Product[] getProduct() {
        switch (TypeOfProduct) {
            case "Бытовая химия":
                return products.stream().filter(product -> product instanceof Household).toArray(Product[]::new);
            case "Молочные продукты":
                return products.stream().filter(product -> product instanceof Dairy).toArray(Product[]::new);
            case "Выпечка":
                return products.stream().filter(product -> product instanceof Bakery).toArray(Product[]::new);
        }
        return products.toArray(new Product[0]);
    }

    public void delete(String code) {

        ArrayList<Product> res = new ArrayList<>();
        for (Product s : this.getProduct()) {
            if (s.getCode().equals(code)) {
                res.add(s);
            }
        }



        if (res.size() > 1) {

            for (Product s : res) {
                int r = JOptionPane.showConfirmDialog(null,
                        "Вы точно хотите удалить товар " + s.getTypeOfProduct() + "  " + s.getCode() + "  " + s.getName() + "  " + s.getBrand() + "?",
                        "Подтверждение",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (r == JOptionPane.YES_OPTION) {
                    try {
                        db.Connecting();

                        db.delete(s.getId());
                        products.remove(s);
                        db.CloseDB();
                        System.out.println("Запись удалена.Подключение закрыто");
                    } catch (ClassNotFoundException | SQLException e) {
                        System.out.println("Подключение закрыто");
                    }
                    JOptionPane.showMessageDialog(null,
                            "Товар успешно удален.",
                            "Успешно",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } else if (res.size() == 1) {
            for (Product s : res) {
                int r = JOptionPane.showConfirmDialog(null,
                        "Вы точно хотите удалить товар " + s.getTypeOfProduct() + "  " + s.getCode() + "  " + s.getName() + "  " + s.getBrand() + "?",
                        "Подтверждение",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (r == JOptionPane.YES_OPTION) {
                    try {
                        db.Connecting();

                        db.delete(s.getId());
                        products.remove(s);
                        db.CloseDB();
                        System.out.println("Запись удалена.Подключение закрыто");
                    } catch (ClassNotFoundException | SQLException e) {
                        System.out.println("Подключение закрыто");
                    }
                    JOptionPane.showMessageDialog(null,
                            "Товар успешно удален.",
                            "Успешно",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Товар не найден.",
                    "Не найдено",
                    JOptionPane.ERROR_MESSAGE);
        }

        this.fireTableDataChanged();
    }


    @Override
    public int getRowCount() {
        return getProduct().length;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 1:
                return this.getProduct()[r].getCode();
            case 2:
                return this.getProduct()[r].getName();
            case 3:
                return this.getProduct()[r].getBrand();
            case 4:
                return this.getProduct()[r].getCost();

            case 5:
                return this.getProduct()[r].getWeight();

            case 0:
                return this.getProduct()[r].getTypeOfProduct();
            default:
                return "";
        }

    }

    @Override
    public String getColumnName(int c) {
        String сolumn = "";
        switch (c) {
            case 1:
                сolumn = "Код";
                break;
            case 2:
                сolumn = "Название";
                break;
            case 3:
                сolumn = "Бренд";
                break;
            case 4:
                сolumn = "Стоимость (рубли)";
                break;
            case 5:
                сolumn = "Вес(грам)";
                break;
            case 0:
                сolumn = "Тип товара";
                break;

        }
        return сolumn;
    }

    public int getID(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() > id) id = products.get(i).getId();
        }
        return id + 1;
    }
}
