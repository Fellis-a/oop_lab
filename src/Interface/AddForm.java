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

    public JLabel labelCode = new JLabel("Код товара: ");
    public JLabel labelName = new JLabel("Название товара: ");
    public JLabel labelBrand = new JLabel("Бренд: ");
    public JLabel labelWeight = new JLabel("Вес (грамм): ");
    public JLabel labelCost = new JLabel("Стоимость (рубли): ");
    public JLabel labelCmb = new JLabel("Тип товара: ");
    public JLabel labelExpirationDate = new JLabel("Срок годности: ");
    public JLabel labelFilling= new JLabel("Начинка: ");
    public JLabel error1 = new JLabel("Введите штрих-код товара\n(цифры указаны на товаре)");
    public JLabel error2 = new JLabel("Обязательное поле!");
    public JLabel error3 = new JLabel("Обязательное поле!");
    public JLabel error4 = new JLabel("Введите число");
    public JLabel error5 = new JLabel("Неверный ввод!");
    public JLabel error6 = new JLabel("Введите дату в формате д.м.г");

    private String[] chooseCmb = {
            "Все",
            "Бытовая химия",
            "Молочные продукты",
            "Выпечка"
    };
    private JComboBox cmbProductsType = new JComboBox(chooseCmb);
    public StuffTable products = new StuffTable();




    public JTextField txtName = new JTextField(10);
    public JTextField txtCode = new JTextField(10);
    public JTextField txtBrand = new JTextField(10);
    public JTextField txtCost = new JTextField(10);
    public JTextField txtWeight = new JTextField(10);
    public JTextField txtExpirationDate = new JTextField(10);


    ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton r1=new JRadioButton("С наполнителем", true);
    JRadioButton r2=new JRadioButton("Без наполнителя", false);

    private String rb;

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
        error1.setForeground(this.getBackground());
        error2.setForeground(this.getBackground());
        error3.setForeground(this.getBackground());
        error4.setForeground(this.getBackground());
        error5.setForeground(this.getBackground());
        error6.setForeground(this.getBackground());

// create a new panel with GridBagLayout manager
        JPanel newPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelCode, constraints);

        constraints.gridx = 1;
        newPanel.add(txtCode, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        newPanel.add(error1, constraints);

        ValidationNumber(txtCode,error1);

        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(labelName, constraints);

        constraints.gridx = 1;
        newPanel.add(txtName, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        newPanel.add(error2, constraints);

        ValidationText(txtName,error2);

        constraints.gridx = 0;
        constraints.gridy = 4;
        newPanel.add(labelBrand, constraints);

        constraints.gridx = 1;
        newPanel.add(txtBrand, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        newPanel.add(error3, constraints);

        ValidationText(txtBrand,error3);

        constraints.gridx = 0;
        constraints.gridy = 6;
        newPanel.add(labelCost, constraints);

        constraints.gridx = 1;
        newPanel.add(txtCost, constraints);

        constraints.gridx = 1;
        constraints.gridy = 7;
        newPanel.add(error4, constraints);

        ValidationNumber(txtCost,error4);

        constraints.gridx = 0;
        constraints.gridy = 8;
        newPanel.add(labelWeight, constraints);

        constraints.gridx = 1;
        newPanel.add(txtWeight, constraints);

        constraints.gridx = 1;
        constraints.gridy = 9;
        newPanel.add(error5, constraints);

        ValidationNumber(txtWeight,error5);

        constraints.gridx = 0;
        constraints.gridy = 10;
        newPanel.add(labelCmb, constraints);

        constraints.gridx = 1;
        newPanel.add(cmbProductsType, constraints);
        cmbProductsType.addActionListener(this::selectProduct);

        /*switch (this.model.getType()) {

            case "Молочные продукты":

                constraints.gridx = 0;
                constraints.gridy = 5;
                newPanel.add(labelFilling, constraints);

                buttonGroup.add(r1);
                buttonGroup.add(r2);
                constraints.gridx = 1;
                newPanel.add(r1, constraints);
                constraints.gridx = 2;
                newPanel.add(r2, constraints);
                newPanel.add(error6, constraints);
                ValidationNumber(txtExpirationDate,error6);


                break;
            case "Выпечка":
                constraints.gridx = 0;
                constraints.gridy = 5;
                newPanel.add(labelExpirationDate, constraints);

                constraints.gridx = 1;
                newPanel.add(txtExpirationDate, constraints);

                constraints.gridx = 2;
                newPanel.add(error6, constraints);
                ValidationNumber(txtExpirationDate,error6);
                break;
        }
        */





        constraints.gridx = 1;
        constraints.gridy = 11;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        newPanel.add(btnAdd, constraints);

        btnAdd.addActionListener(this::save);

        constraints.gridx = 1;
        constraints.gridy = 12;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        newPanel.add(btnCancel, constraints);
        btnCancel.addActionListener(this::cancel);


        add(newPanel);

        error1.addComponentListener(this);
        error2.addComponentListener(this);
        error3.addComponentListener(this);
        error4.addComponentListener(this);


        pack();
        setLocationRelativeTo(null);

        //Interface.sort(null);
        this.selectProduct(null);

    }

    public void save(ActionEvent actionEvent) {
        String rb;

            switch (products.getType()) {
                case "Бытовая химия":
                        model.Add(new Household(getInfo(txtCode), getInfo(txtCost),getInfo(txtWeight),getInfo(txtName), getInfo(txtBrand)));
                    break;
                case "Молочные продукты":
                    if(r1.isSelected()){
                        rb = "С наполнителем";
                        model.Add(new Dairy(getInfo(txtCode),getInfo(txtCost),getInfo(txtWeight), getInfo(txtName), getInfo(txtBrand), rb));
                    }
                    if(r2.isSelected()){
                        rb ="Без наполнителя";
                        model.Add(new Dairy(getInfo(txtCode),getInfo(txtCost),getInfo(txtWeight), getInfo(txtName), getInfo(txtBrand), rb));
                    }

                    break;
                case "Выпечка":
                    if(r1.isSelected()){
                        rb = "С наполнителем";
                        model.Add(new Bakery(getInfo(txtCode),getInfo(txtCost),getInfo(txtWeight), getInfo(txtName), getInfo(txtBrand), rb));
                    }
                    if(r2.isSelected()){
                        rb ="Без наполнителя";
                        model.Add(new Bakery(getInfo(txtCode),getInfo(txtCost),getInfo(txtWeight), getInfo(txtName), getInfo(txtBrand), rb));
                    }
                    break;
            }
        this.setVisible(false);
    }

    public void cancel(ActionEvent actionEvent) {
        this.setVisible(false);
    }

    public String getInfo(JTextField txt)
    {
        String str = txt.getText();
        return StartGUI.firstUpperCase(str);
    }

    public void ValidationText(JTextField txt,JLabel lbl){
        txt.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void removeUpdate(DocumentEvent e)
            {
                String text = txt.getText();
                lbl.setForeground(Color.RED);
                lbl.setVisible(text.matches(".*\\d+.*")||text.isEmpty());
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                String text = txt.getText();
                lbl.setForeground(Color.RED);
                lbl.setVisible(text.matches(".*\\d+.*")||text.isEmpty());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }
    public void ValidationNumber(JTextField txt,JLabel lbl){
        txt.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void removeUpdate(DocumentEvent e)
            {
                String text = txt.getText();
                lbl.setForeground(Color.RED);
                lbl.setVisible(!text.matches("^[0-9]+$"));
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                String text = txt.getText();
                lbl.setForeground(Color.RED);
                lbl.setVisible(!text.matches("^[0-9]+$"));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
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
        btnAdd.setEnabled(!error1.isVisible() && !error2.isVisible() && !error3.isVisible() && !error4.isVisible());


        /*switch (this.model.getType()) {

            case "Молочные продукты":
                btnAdd.setEnabled(!error1.isVisible() && !error2.isVisible() && !error3.isVisible() && !error5.isVisible());
                break;
            case "Выпечка":
                btnAdd.setEnabled(!error1.isVisible() && !error2.isVisible() && !error3.isVisible() && !error6.isVisible());
                break;
        }*/
    }
    public void selectProduct(ActionEvent e) {
        String selectedValue = (String) this.cmbProductsType.getSelectedItem();
        products.setType((String) this.cmbProductsType.getSelectedItem());
        //блокировка кнопки добавить
        btnAdd.setEnabled(selectedValue.equals("Выпечка") || selectedValue.equals("Бытовая химия") || selectedValue.equals("Молочные продукты"));
    }

}

