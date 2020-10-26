package Interface;

import com.company.Bakery;
import com.company.Dairy;
import com.company.Household;
import com.company.Product;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class StuffTable extends AbstractTableModel {
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Product> productsFound = new ArrayList<>();

    public String TypeOfProduct = "";//Долж
    private String[] TypeOfProducts         = {"Бытовая химия",
            "Молочные продукты",
            "Выпечка"};
    public Integer Code;//Долж
    public String Name = "";//Долж

    StuffTable() {
        super();
    }

    public void Add(Product s) {

        products.add(s);
        this.fireTableDataChanged();
    }
    public void AddFound(Product s) {

        productsFound.add(s);
        this.fireTableDataChanged();
    }





    public void search(Integer code) {

        ArrayList<Product> res = new ArrayList<>();
        for (Product s : this.getProduct()) {
            if (s.getCode().equals(code))  {
                productsFound.add(s);
                this.fireTableDataChanged();

            }
        }



    }

    public void searchName(String name) {
        ArrayList<Product> res = new ArrayList<>();
        for (Product s : this.getProduct()) {
            if (s.getName().equals(name))  {
                res.add(s);
            }
        }
        String found="<html><ul>";
        for (Product s:res)
        {
            found += "<li>"+ s.getTypeOfProduct()+" " + s.getCode() +" "+s.getName()+" "+s.getBrand()+"</li>";
        }
        found+= "</ul><html>";

        if (res.size() > 1 )
        {
            Object result = JOptionPane.showInputDialog(
                    null,
                    new String[] {"По вашему запросу найдено несколько товаров:",
                            found},
                    "Результат поиска");
            for(Product s:res)
            {
                if(s.getTypeOfProduct() == result)
                {   JOptionPane.showMessageDialog(null,
                        s.learnMore(),
                        "Товар",
                        JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }

        }
        else if (res.size() == 1 ){
            for(Product s:res)
            {
                {   JOptionPane.showMessageDialog(null,
                        s.learnMore(),
                        "Товар",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        else {JOptionPane.showMessageDialog(null,
                "Товар не найден.",
                "Не найдено",
                JOptionPane.ERROR_MESSAGE);}

    }
    public String getType() {
        return TypeOfProduct;
    }

    public void setType(String typeOfProduct) {
        this.TypeOfProduct = typeOfProduct;
        this.fireTableStructureChanged();
    }
    public void setName(String name) {
        this.Name = name;
        this.fireTableStructureChanged();
    }
    public void setCode(Integer code) {
        this.Code = code;
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

        String found="<html><ul>";
        for (Product s:res)
        {
            found += "<li>"+ s.getTypeOfProduct()+" " + s.getCode() +" "+s.getName()+" "+s.getBrand()+"</li>";
        }
        found+= "</ul><html>";

        if (res.size() > 1 )
        {
            Object result = JOptionPane.showInputDialog(
                    null,
                    new String[] {"Ошибка, ОБРАТИТЕСЬ К АДМИНИСТРАТОРУ ДЛЯ РЕШЕНИЯ ПРОБЛЕМЫ (один код для нескольких товаров)."});

        }
        else if (res.size() == 1 ){
            for(Product s:res)
            {
                int r = JOptionPane.showConfirmDialog(null,
                        "Вы точно хотите удалить товар " + s.getTypeOfProduct() + "  " +s.getCode()+ "  " +s.getName() +"  "+s.getBrand()+"?",
                        "Подтверждение",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (r == JOptionPane.YES_OPTION)
                {
                    products.remove(s);
                    JOptionPane.showMessageDialog(null,
                            "Товар успешно удален.",
                            "Успешно",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        else {JOptionPane.showMessageDialog(null,
                "Товар не найден.",
                "Не найдено",
                JOptionPane.ERROR_MESSAGE);}

        this.fireTableDataChanged();
    }



    @Override
    public int getRowCount() {
        return getProduct().length;
    }

    @Override
    public int getColumnCount() {
        return 7;
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
            case 6:
                Product s = this.getProduct()[r];

                if (s instanceof Bakery)
                    return ((Bakery) this.getProduct()[r]).expirationDate();
                if (s instanceof Dairy)
                    return ((Dairy) this.getProduct()[r]).issFilling();
                break;
            case 0:
                return this.getProduct()[r].getTypeOfProduct();
            default:
                return "";
        }
        return "";
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
            case 3: сolumn = "Бренд";
                break;
            case 4: сolumn = "Стоимость (рубли)";
                break;
            case 5: сolumn = "Вес (грамм)";
                break;

            case 6:
                switch (TypeOfProduct) {
                    case "Хлебо-булочные изделия":
                        сolumn = "Срок годности в часах";
                        break;
                    case "Молочные продукты":
                        сolumn = "Наполнитель";
                        break;
                    case "Бытовая химия":
                        сolumn = "-";
                        break;
                    default:
                        сolumn = "Наполнитель/срок годности";
                        break;
                }
                break;
            case 0: сolumn ="Тип товара";
                break;
        }
        return сolumn;
    }

}
