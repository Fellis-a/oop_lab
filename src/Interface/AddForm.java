package Interface;


import com.company.Bakery;
import com.company.Dairy;
import com.company.Household;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class AddForm extends JFrame implements ComponentListener {

    private JLabel labelCode = new JLabel("Штрих-код товара: ");
    private JLabel labelName = new JLabel("Название товара: ");
    private JLabel labelBrand = new JLabel("Бренд: ");
    private JLabel labelWeight = new JLabel("Вес (грамм): ");
    private JLabel labelCost = new JLabel("Стоимость (рубли): ");
    private JLabel labelCmb = new JLabel("Тип товара: ");
    //public JLabel labelExpirationDate = new JLabel("Срок годности: ");
    public JLabel labelFilling = new JLabel("Начинка: ");
    //ImageIcon imageIcon = new ImageIcon("icon.png");
    private JButton error1 = new JButton("i");
    private JLabel error4 = new JLabel("Введите число");
    private JLabel error5 = new JLabel("Введите число");

    private String[] chooseCmb = {
            "Все",
            "Бытовая химия",
            "Молочные продукты",
            "Выпечка"
    };
    private JComboBox cmbProductsType = new JComboBox(chooseCmb);
    private StuffTable products = new StuffTable();


    private JTextField txtName = new JTextField(20);
    private JTextField txtCode = new JTextField(20);
    private JTextField txtBrand = new JTextField(20);
    private JTextField txtCost = new JTextField(20);
    private JTextField txtWeight = new JTextField(20);
    private JTextField txtFilling = new JTextField(20);

    private JButton btnAdd = new JButton("Добавить");
    private JButton btnCancel = new JButton("Отмена");

    StuffTable model;

    public AddForm(StuffTable model) {
        super("Добавить товар"); //Заголовок формы
        this.model = model;
        pack(); //для того, чтобы окно создавалось под размер всех панелей
        setResizable(false);//нельзя раздвигать окно
        setLocationRelativeTo(null);//чтобы окно создавалось по середине экрана
        setVisible(true);//показать
        panelsParameters();


    }


    private void panelsParameters() {
        btnAdd.setEnabled(false);
        error4.setForeground(this.getBackground());
        error5.setForeground(this.getBackground());
        JPanel newPanel = new JPanel(new GridBagLayout());


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelCode, constraints);

        constraints.gridx = 1;
        newPanel.add(txtCode, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        error1.setToolTipText("<html><p>Что такое штрих-код?</p></html>");
        error1.addActionListener(this::info);
        newPanel.add(error1, constraints);


        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(labelName, constraints);

        constraints.gridx = 1;
        newPanel.add(txtName, constraints);


        constraints.gridx = 0;
        constraints.gridy = 3;
        newPanel.add(labelBrand, constraints);

        constraints.gridx = 1;
        newPanel.add(txtBrand, constraints);


        constraints.gridx = 0;
        constraints.gridy = 4;
        newPanel.add(labelCost, constraints);

        constraints.gridx = 1;
        newPanel.add(txtCost, constraints);

        constraints.gridx = 2;
        constraints.gridy = 4;
        newPanel.add(error4, constraints);

        ValidationNumber(txtCost, error4);

        constraints.gridx = 0;
        constraints.gridy = 5;
        newPanel.add(labelWeight, constraints);

        constraints.gridx = 1;
        newPanel.add(txtWeight, constraints);

        constraints.gridx = 2;
        constraints.gridy = 5;
        newPanel.add(error5, constraints);

        ValidationNumber(txtWeight, error5);

        constraints.gridx = 0;
        constraints.gridy = 6;
        newPanel.add(labelCmb, constraints);

        constraints.gridx = 1;
        newPanel.add(cmbProductsType, constraints);
        cmbProductsType.addActionListener(this::selectProduct);

        constraints.gridx = 2;
        constraints.gridy = 11;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(btnAdd, constraints);

        btnAdd.addActionListener(this::save);

        constraints.gridx = 2;
        constraints.gridy = 12;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(btnCancel, constraints);
        btnCancel.addActionListener(this::cancel);


        add(newPanel);

        error1.addComponentListener(this);
        error5.addComponentListener(this);
        error4.addComponentListener(this);
        txtCode.addActionListener(this::firstEnter);
        txtName.addActionListener(this::secondEnter);
        txtBrand.addActionListener(this::thirdEnter);
        txtCost.addActionListener(this::forthEnter);


        pack();
        setLocationRelativeTo(null);
        this.selectProduct(null);

    }

    public void save(ActionEvent actionEvent) {

        int ID = model.getID(0);
        switch (products.getType()) {
            case "Бытовая химия":
                model.Add(new Household(ID, getInfo(txtCode), getInfo(txtName), getInfo(txtBrand), getInfo(txtCost), getInfo(txtWeight)));
                break;
            case "Молочные продукты":

                model.Add(new Dairy(ID, getInfo(txtCode), getInfo(txtName), getInfo(txtBrand), getInfo(txtCost), getInfo(txtWeight)));

                break;
            case "Выпечка":

                model.Add(new Bakery(ID, getInfo(txtCode), getInfo(txtName), getInfo(txtBrand), getInfo(txtCost), getInfo(txtWeight)));

                break;
        }
        this.setVisible(false);
    }

    public void cancel(ActionEvent actionEvent) {
        this.setVisible(false);
    }

    public String getInfo(JTextField txt) {
        String str = txt.getText();
        return firstUpperCase(str);
    }

    public void ValidationText(JTextField txt, JLabel lbl) {
        txt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txt.getText();
                lbl.setForeground(Color.RED);
                lbl.setVisible(text.matches(".*\\d+.*") || text.isEmpty());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txt.getText();
                lbl.setForeground(Color.RED);
                lbl.setVisible(text.matches(".*\\d+.*") || text.isEmpty());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    public void ValidationNumber(JTextField txt, JLabel lbl) {
        txt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txt.getText();
                lbl.setForeground(Color.RED);
                lbl.setVisible(!text.matches("^[0-9]+$"));

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txt.getText();
                lbl.setForeground(Color.RED);
                lbl.setVisible(!text.matches("^[0-9]+$"));

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    @Override
    public void componentResized(ComponentEvent componentEvent) {

    }

    @Override
    public void componentMoved(ComponentEvent componentEvent) {

    }

    @Override
    public void componentShown(ComponentEvent componentEvent) {
        btnAdd.setEnabled(false);
    }

    @Override
    public void componentHidden(ComponentEvent componentEvent) {
        btnAdd.setEnabled(!error1.isVisible() && !error5.isVisible() && !error4.isVisible());

    }

    public void selectProduct(ActionEvent e) {
        String selectedValue = (String) this.cmbProductsType.getSelectedItem();
        products.setType((String) this.cmbProductsType.getSelectedItem());
        //блокировка кнопки добавить
        btnAdd.setEnabled(selectedValue.equals("Выпечка") || selectedValue.equals("Бытовая химия") || selectedValue.equals("Молочные продукты"));
    }

    public static String firstUpperCase(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
    private void firstEnter(ActionEvent actionEvent) {
        txtName.requestFocusInWindow();
    }

    private void secondEnter(ActionEvent actionEvent) {
        txtBrand.requestFocusInWindow();
    }

    private void thirdEnter(ActionEvent actionEvent) {
        txtCost.requestFocusInWindow();
    }

    private void forthEnter(ActionEvent actionEvent) {
        txtWeight.requestFocusInWindow();
    }

    private void info(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(null,
                "<html><h3>Посказка</h3><i>Вам необходимо ввести цифры со штрих-кода товара</i>");
    }

}

