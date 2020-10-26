package Interface;

import com.company.Bakery;
import com.company.Dairy;
import com.company.Household;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultForm extends JFrame implements ActionListener {
    public StuffTable productsFound = new StuffTable();
    StuffTable model;
    ResultForm() {
        super();
    }
    private static JPanel panel, panel1, panel2, header;

    private static JTable tbl;
    private JScrollPane scroll;


    public ResultForm(StuffTable model) {
        super("Добавить товар"); //Заголовок формы
        this.model = model;
        ResultForm frame = new ResultForm();
        frame.panelsParameters();
        frame.pack(); //для того, чтобы окно создавалось под размер всех панелей
        frame.setResizable(false);//нельзя раздвигать окно
        frame.setSize(1000, 500);//размер
        frame.setLocationRelativeTo(null);//чтобы окно создавалось по середине экрана
        frame.setVisible(true);//показать

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
        JButton button = new JButton("Вернуться назад");
        button.setFont(font);
        header.add(button, BorderLayout.CENTER);
        header.add(button);
        panel.add(header);


        //Панель с таблицей
        panel1 = new JPanel();
        panel1.setSize(900,500);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        //таблица
        productsFound.Add(new Bakery("2215", "125", "115", "Булочка", "Каравай", "С наполнителем"));
        productsFound.Add(new Bakery("2216", "50", "100", "Улитка с маком", "Хлебзавод", "Без наполнителя"));

        productsFound.Add(new Dairy("2217", "69", "100", "Йогурт", "Активиа", "Без наполнителя"));
        productsFound.Add(new Dairy("2218", "50", "200", "Творог", "Простоквашино", "С наполнителем"));
        productsFound.Add(new Dairy("2219", "125", "115", "Йогурт с шоколадными шариками", "Данисимо", "С наполнителем"));

        productsFound.Add(new Household("2220", "130", "100", "Антисептик", "Фарм"));
        productsFound.Add(new Household("2221", "17", "50", "Влажные салфетки", "котте"));
        productsFound.Add(new Household("2222", "60", "80", "Мыло", "Дав"));

       // productsFound.setCode();
        //productsFound.addTableModelListener();

        tbl = new JTable();
        tbl.setPreferredScrollableViewportSize(new Dimension(950, 400));
        scroll = new JScrollPane(tbl);
        tbl.setModel(productsFound);
        panel1.add(scroll);


        panel.add(panel1);


        getContentPane().add(panel);//сделали основную панель основной

        //this.selectProduct(null);
    }
    private void setProduct(ActionEvent e){
        Integer selectedValue = Integer.valueOf(JOptionPane.showInputDialog(null, "Введите название продукта:",
                "Поиск",JOptionPane.QUESTION_MESSAGE));

        productsFound.setCode(selectedValue);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Integer selectedValue = Integer.valueOf(JOptionPane.showInputDialog(null, "Введите название продукта:",
                "Поиск",JOptionPane.QUESTION_MESSAGE));

        productsFound.setCode(selectedValue);
    }
}
