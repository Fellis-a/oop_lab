package Interface;

import com.company.Bakery;
import com.company.Dairy;
import com.company.Household;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Interface extends JFrame {
    public Interface() {super("SmallShop");}

    private static JTable tbl;
    private JScrollPane scroll;

   // private JComboBox cmbProductsType;

    private JButton add;
    private JButton search;
    private JButton delete;
    private JButton menu;


    private static JPanel panel, panel1, panel2, header;

    private String[] chooseCmb = {
                "Все",
                "Бытовая химия",
                "Молочные продукты",
                "Выпечка"
    };
    private JComboBox cmbProductsType = new JComboBox(chooseCmb);
    private JRadioButton sortFirstType, sortSecondType, sortThirdType,sortForthType;




    public StuffTable products = new StuffTable();
    public StuffTable productsFound = new StuffTable();

    public static void mainInterface() {


        Interface frame = new Interface();
        frame.panelsParameters();
        frame.pack(); //для того, чтобы окно создавалось под размер всех панелей
        frame.setResizable(false);//нельзя раздвигать окно
        frame.setSize(1000, 500);//размер
        frame.setLocationRelativeTo(null);//чтобы окно создавалось по середине экрана
        frame.setVisible(true);//показать
    }


    private JMenuBar createSubmenus()
    {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuItem1 = new JMenu("Товар");
        JMenu menuItem2 = new JMenu("Поиск");
        JMenu menuHelp = new JMenu("Справка");
        // и несколько вложенных меню
        JMenuItem aboutProgram = new JMenuItem("О программе");
        JMenuItem addItem = new JMenuItem("Добавить товар");
        JMenuItem deleteItem = new JMenuItem("Удалить товар");
        JMenuItem exitItem = new JMenuItem("Выход");

        JMenu find = new JMenu("Поиск");
        JMenuItem findCode = new JMenuItem("По коду");
        JMenuItem findName = new JMenuItem("По названию");

        JMenu sort = new JMenu("Поиск по типу товара");
        //JMenuItem sortFirstType = new JMenuItem("Сортировка по типу товара");
         sortFirstType = new JRadioButton("Бытовая химия");
         sortSecondType = new JRadioButton("Молочная продукция");
         sortThirdType = new JRadioButton("Выпечка");
         sortForthType = new JRadioButton("Все");


        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(sortFirstType);
        buttonGroup.add(sortSecondType);
        buttonGroup.add(sortThirdType);
        buttonGroup.add(sortForthType);
        sort.add(sortFirstType);
        sort.add(sortSecondType);
        sort.add(sortThirdType);
        sort.add(sortForthType);
        sortFirstType.addActionListener(this::chooseTypeOfProduct);
        sortSecondType.addActionListener(this::chooseTypeOfProduct);
        sortThirdType.addActionListener(this::chooseTypeOfProduct);
        sortForthType.addActionListener(this::chooseTypeOfProduct);


        menuHelp.add(aboutProgram);
        menuItem1.add(addItem);
        menuItem1.add(deleteItem);
        menuItem1.addSeparator();
        menuItem1.add(exitItem);

        addItem.addActionListener(this::AddStaff);
        deleteItem.addActionListener(this::deleteProduct);
        exitItem.addActionListener(this::exitAction);

        find.add(findCode);
        find.add(findName);
        menuItem2.add(find);
        menuItem2.add(sort);

        //findCode.addActionListener();
        sort.addActionListener(this::sortProduct);
        findCode.addActionListener(this::searchProduct);
        findName.addActionListener(this::searchProductName);
        menuBar.add(menuItem1);
        menuBar.add(menuItem2);
        menuBar.add(menuHelp);

        aboutProgram.addActionListener(this::info);

        return menuBar;


    }


    private void panelsParameters() //параметры панелей
    {
        Font font = new Font("Century Gothic", Font.BOLD, 17);
        //Создается главная панель(на которой все лежит)
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Устанавливаем завершение программы при закрытии окна

        header = new JPanel();
        header.setSize(1000, 100);
        JLabel headerLabel = new JLabel("Склад");
        headerLabel.setFont(font);
        header.add(headerLabel, BorderLayout.CENTER);
        header.add(headerLabel);
        panel.add(header);

        createSubmenus();


        // JMenuBar использует блочное расположение (заполнитель вполне уместен)
        createSubmenus().add(Box.createHorizontalGlue());


        // поместим меню в наше окно
        setJMenuBar(createSubmenus());
        //Панель с таблицей
        panel1 = new JPanel();
        panel1.setSize(900,500);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        //таблица
        products.Add(new Bakery("2215", "125", "115", "Булочка", "Каравай", "С наполнителем"));
        products.Add(new Bakery("2216", "50", "100", "Улитка с маком", "Хлебзавод", "Без наполнителя"));

        products.Add(new Dairy("2217", "69", "100", "Йогурт", "Активиа", "Без наполнителя"));
        products.Add(new Dairy("2218", "50", "200", "Творог", "Простоквашино", "С наполнителем"));
        products.Add(new Dairy("2219", "125", "115", "Йогурт с шоколадными шариками", "Данисимо", "С наполнителем"));

        products.Add(new Household("2220", "130", "100", "Антисептик", "Фарм"));
        products.Add(new Household("2221", "17", "50", "Влажные салфетки", "котте"));
        products.Add(new Household("2222", "60", "80", "Мыло", "Дав"));


        tbl = new JTable();
        tbl.setPreferredScrollableViewportSize(new Dimension(950, 400));
        scroll = new JScrollPane(tbl);
        tbl.setModel(products);
        panel1.add(scroll);


        panel.add(panel1);



        getContentPane().add(panel);//сделали основную панель основной

        //this.selectProduct(null);
    }

    private void AddStaff(ActionEvent actionEvent) {

        AddForm a = new AddForm(products);

    }
    private void FindProducts(ActionEvent actionEvent) {

        ResultForm m = new ResultForm(products);

    }

    //метод, для показа таблицы в зависимости от того, что выбрали в комбобоксе
    private void selectProductHousehold(ActionEvent e) {


        // add.setEnabled(selectedValue.equals("Бытовая химия") || selectedValue.equals("Молочные продукты") || selectedValue.equals("Хлебо-булочные изделия"));
    }

    //метод для удаления животного в зависимости от того, что выбрали в комбобоксе
    private void deleteProduct(ActionEvent e) {
        Integer input = Integer.valueOf(JOptionPane.showInputDialog(null, "Для удаления товара необходимо ввести его код\nВведите код товара:",
                "Удаление",JOptionPane.QUESTION_MESSAGE));
        if (input.equals("")){
            JOptionPane.showMessageDialog(null,
                    "Неверный ввод.Попробуйте еще раз",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
        else
        {
             products.delete(input.toString());
        }
    }

    private void searchProduct(ActionEvent e){

        Integer input = Integer.valueOf(JOptionPane.showInputDialog(null, "Введите код продукта:",
                "Поиск",JOptionPane.QUESTION_MESSAGE));


        if (input.equals("")){
            JOptionPane.showMessageDialog(null,
                    "Неверный ввод.Попробуйте еще раз!",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
        else
        {

            //products.search(input);
            //productsFound.setValue(input);
            ResultForm m = new ResultForm(productsFound);

        }

    }
    private void searchProductName(ActionEvent e){

        Integer input = Integer.valueOf(JOptionPane.showInputDialog(null, "Введите название продукта:",
                "Поиск",JOptionPane.QUESTION_MESSAGE));

        if (input.equals("")){
            JOptionPane.showMessageDialog(null,
                    "Неверный ввод.Попробуйте еще раз!",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
        else
        {

           // products.searchName(firstUpperCase(input));
           // productsFound.setCode(input);
        }

    }
    private void sortProduct(ActionEvent e){
        String selectedValue = (String) this.cmbProductsType.getSelectedItem();
        add.setEnabled(selectedValue.equals("Выпечка") || selectedValue.equals("Молочные продукты") || selectedValue.equals("Бытовая химия"));
        products.setType(selectedValue);


    }


    private void chooseTypeOfProduct(ActionEvent e) {

        products.setType((String) this.cmbProductsType.getSelectedItem());

        if (this.sortFirstType.isSelected()){
            products.setType( "Бытовая химия");
        } else if (this.sortSecondType.isSelected()){
            products.setType( "Молочные продукты");
        } else if(this.sortThirdType.isSelected()){
            products.setType( "Выпечка");
        } else {products.setType( "Все");}

    }

    private void info(ActionEvent actionEvent) {

        String message = "<html>Программа предназначена для работы со складом магазина.<br/>Интерфейс позволяет просматривать товары.<br/>"+
                "Также присутсвует возможность поиска товаров по названию, штрих-коду и по типу товаров.<br/>При возникновений ошибок в поиске"+
                " появляются соответсвующие подсказки.<br/>Имеется возможность добавления и удаления товаров.<br/>Добавление товара осуществляется при помощи отдельной формы.<br/>"+
                "После добавления и удаления таблица обновляется.</html>";
        JOptionPane.showMessageDialog(null , message, "Справка", JOptionPane.INFORMATION_MESSAGE);
    }

    public static String firstUpperCase(String word){
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
    private void exitAction(ActionEvent e) {
        System.exit(0);
    }


}
