package Interface;

import javax.swing.*;


public class StartGUI{

    private JComponent ui = null;


    StartGUI() {  Interface.mainInterface();};






    public JComponent getUI() {
        return ui;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                StartGUI o = new StartGUI();

                JFrame f = new JFrame(o.getClass().getSimpleName());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);
                f.setTitle("Склад товаров");


                f.setContentPane(o.getUI());
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setLocationRelativeTo(null);

                f.setVisible(true);

            }
        };
        SwingUtilities.invokeLater(r);

    }

    public static String firstUpperCase(String word){
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}

