package com.company;

import java.util.ArrayList;

public class Shop {
    private static int cash=1000;
    private static int sum=0;
    private static int numberOfChoice;
    private static ArrayList<Product> Products = new ArrayList<Product>();//список транспорта



    /*public static void main(String[] args) {


//        new MenuGUI();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuGUI();
            }
        });

        /*printStart();
        printRange();
        getProduct(getNumberOfChoice());
        printActions();*/




    /*
    //чек, подсчет остатка
    private static void sumCheck() {
        printAllProducts();
        int balance = cash-sum;
        out.println("Cумма вашей покупки:\n"+sum);
        out.println("Сдача:\n"+balance);

    };
    //поиск среди выбранных товаров по штрих-коду
    private static void searchProduct(){
        boolean check = false;
        out.print("Введите цифры штрихкода товара: ");
        Scanner scn = new Scanner(System.in);
        Integer ProductCode = scn.nextInt();
        out.print("\n");
        for (Product product: Products) {
            if (product.Code.equals(ProductCode)){
                out.printf(product.Number + ")" + product.typeOfProduct+"цена:"+product.Cost);
                printProducts(product);
                check = true;
            }
        }
        out.print("\n");
        if (!check){
            out.print("\nУ вас нет такого товара!\n");
        }
    }


    //первое меню которое выходит
    private static void printStart(){
        out.print("\n\n| Вы собрались в магазин во время режима изоляции\n");
        out.print("| Нужно купить самые необходимые продукты и уложится в бюджет\n");
        out.print("| На вашем счету: 1000 руб.\n\n");
    }*/
    //второе меню
   /*
    //меню, выбор действия
    private static void printActions(){
        out.printf("\nНа вашем счету: %d\n",cash);
        out.print("1 - Выбрать товар; 2 - Убрать из списка; 3 - Показать все выбранные товары; " +
                "4 - Поиск; 5 - Закончить покупку;\n");
        out.print("Выберете действие: ");
        getAction(getNumberOfChoice());
    }*/
    //вывод всего списка товаров на экран
   /* private static void printAllProducts(){
        out.print("\nВаш список продуктов :\n");
        out.print("-----------------------");
        for(Product product: Products){
            out.printf("\n"+ (Products.indexOf(product) + 1) + ")" + product.typeOfProduct + " №" + product.Number);
            printProducts(product);
        }
        out.print("\n-----------------------\n");
    }*/
    /*
    //один товар
    private static void printProducts(Product product){


        if (product instanceof Bakery){
            out.print("\n\tПроизводитель: " + ((Bakery) product).getBrand());
            out.print("\n\tНаименование: " + ((Bakery) product).getName());
            out.print("\n\tВес:" + ((Bakery) product).Weigth+"\n");
            if(product.expirationDate()==0)
            {out.println("без срока годности");}
            else {out.println("Срок годности"+product.expirationDate()+"суток");}
        }

        if (product instanceof Dairy){
            out.print("\n\tНаименование: " + ((Dairy) product).Name);
            out.print("\n\tПроизводитель: " + ((Dairy) product).Brand);
            out.print("\n\tВес:" + ((Dairy) product).Weigth);
            if(product.expirationDate()==0)
            {out.println("без срока годности");}
            else {out.println("Срок годности"+product.expirationDate()+"суток");}
        }
        if (product instanceof Household){
            out.print("\n\tНаименование: " + ((Household) product).Name);
            out.print("\n\tПроизводитель: " + ((Household) product).Brand);
            out.print("\n\tВес:" + ((Household) product).Weigth);
            if(product.expirationDate()==0)
            {out.println("без срока годности");}
            else {out.println("Срок годности"+product.expirationDate()+"суток");}
        }



    }*/
    //выбор типа продукции
    /*

    private static void buy(Dairy dairy){
        out.print("\nУкажите название товара\n");
        out.print("1 - Молоко Простоквашино(60.00); 2 - Йогурт Активия(89.00); 3 - Сырок Чудо(35.00); 4 - Отмена: ");
        boolean check = true;
        boolean cancel = false;
        while (check){
            switch (getNumberOfChoice()){
                case 1:
                    dairy.Name = "Молоко";
                    dairy.typeOfProduct="Молочный продукт";
                    dairy.Cost = 60;
                    dairy.Code = 104;
                    dairy.Brand = "Простоквашино";
                    dairy.Weigth = 900;
                    dairy.Filling=false;
                    if (checkAbilityBuying(dairy.Cost)){
                        cash -= dairy.Cost;
                        check = false;
                        sum+=dairy.Cost;
                    }
                    break;
                case 2:
                    dairy.Name = "Йогурт";
                    dairy.typeOfProduct="Молочка";
                    dairy.Cost = 89;
                    dairy.Code = 105;
                    dairy.Brand = "Активиа";
                    dairy.Weigth = 120;
                    dairy.Filling=false;
                    if (checkAbilityBuying(dairy.Cost)){
                        cash -= dairy.Cost;
                        check = false;
                        sum+=dairy.Cost;
                    }
                    break;
                case 3:
                    dairy.Name = "Сырок";
                    dairy.typeOfProduct="Молочка";
                    dairy.Cost = 35;
                    dairy.Code = 106;
                    dairy.Brand = "Чудо";
                    dairy.Weigth =40;
                    dairy.Filling=true;
                    if (checkAbilityBuying(dairy.Cost)){
                        cash -= dairy.Cost;
                        check = false;
                        sum+=dairy.Cost;
                    }
                    break;
                case 4:
                    out.print("Покупка отменена\n");
                    cancel = true;
                    check = false;
                    break;
                default:
                    out.print("Такого варианта нет! Выберете ещё раз: ");
                    break;
            }
        }
        if (cancel) printActions();
        else{
            Products.add(dairy);
            out.print("Вы положили в корзину("+dairy.Name+")\n");
        }
    }

    private static void buy(Household household){
        out.print("\nУкажите название товара \n");
        out.print("1 - Антисептик(149.00); 2 - Влажные салфетки(35.00); 3 - Мыло(79.000); 4 - Отмена: ");
        boolean check = true;
        boolean cancel = false;
        while (check){
            switch (getNumberOfChoice()){
                case 1:
                    household.Name = "Антисептик";
                    household.typeOfProduct="Бытовая химия";
                    household.Cost = 149;
                    household.Code = 100;
                    household.Brand = "+";
                    household.Weigth = 50;
                    if (checkAbilityBuying(household.Cost)){
                        cash -= household.Cost;
                        check = false;
                        sum+=household.Cost;
                    }
                    break;
                case 2:
                    household.Name = "Влажные салфетки";
                    household.typeOfProduct="Бытовая химия";
                    household.Cost = 35;
                    household.Code = 101;
                    household.Brand = "Aura";
                    household.Weigth = 25;
                    if (checkAbilityBuying(household.Cost)){
                        cash -= household.Cost;
                        check = false;
                        sum+=household.Cost;
                    }
                    break;
                case 3:
                    household.Name = "Мыло";
                    household.typeOfProduct="Бытовая химия";
                    household.Cost = 79;
                    household.Code = 102;
                    household.Brand = "Palmolive";
                    household.Weigth = 120;
                    if (checkAbilityBuying(household.Cost)){
                        cash -= household.Cost;
                        check = false;
                        sum+=household.Cost;
                    }
                    break;
                case 4:
                    out.print("Отменить \n");
                    cancel = true;
                    check = false;
                    break;
                default:
                    out.print("Такого варианта нет! Выберете ещё раз: ");
                    break;
            }
        }
        if (cancel) printActions();
        else{
            Products.add(household);
            out.print("Вы положили в корзину ("+household.Name+")\n");
        }
    }

    //проверка способности покупки
    private static boolean checkAbilityBuying(int cost){
        if (cost>cash){
            out.print("У вас не достаточно денег для этой покупки! Попробуйте выбрать другое: ");
            return false;
        }
        else return true;
    }
*/
}
