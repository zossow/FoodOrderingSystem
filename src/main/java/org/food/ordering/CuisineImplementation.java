package org.food.ordering;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CuisineImplementation implements Cuisine {


        OrderService orderService = new OrderService();

    public String getMenu(int cuisineId) {
        Properties prop = new Properties();
        Map map = new HashMap();
        map.put(1,"polish.properties");
        map.put(2,"mexican.properties");
        map.put(3,"italian.properties");
        try {
            prop.load(CuisineImplementation.class.getClassLoader().getResourceAsStream((String) map.get(cuisineId)));
            Set<Object> keys = prop.keySet();
            for(Object k:keys){
                String key = (String)k;
                System.out.println(key+", Price: "+ prop.getProperty(key)+"PLN");
            }

        }
        catch (IOException | NullPointerException ex) {
            ex.printStackTrace();
        }
        return (String) map.get(cuisineId);
    }

    public int selectLunch(int cuisineId) {
        Properties prop = new Properties();
        Map map = new HashMap();
        map.put(1,"polish.properties");
        map.put(2,"mexican.properties");
        map.put(3,"italian.properties");
        int price=0;
        int counter=1;
        try {
            prop.load(CuisineImplementation.class.getClassLoader().getResourceAsStream((String) map.get(cuisineId)));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        do{
        System.out.println("-----Insert the name of chosen meal to add to the order");
        String input = orderService.getUserInput().replace("\\u0020"," ");
        if(prop.containsKey(input)){
             String property = prop.getProperty(input);
             price = Integer.valueOf(property);
             System.out.println("-----Meal price: " + price + "PLN");
             counter=0;
        }else {System.out.println("Wrong name. Please try again.");
        counter++;}
        }while (counter>0);

        return price;
    }

    public int selectDessert(int cuisineId) {
        Properties prop = new Properties();
        Map mapDesserts = new HashMap();
        int dessertPrice=0;
        int counter=0;
        mapDesserts.put(1,"desserts_polish.properties");
        mapDesserts.put(2,"desserts_mexican.properties");
        mapDesserts.put(3,"desserts_italian.properties");

        System.out.println("-----Dessert Menu-----");
        try {
            prop.load(CuisineImplementation.class.getClassLoader().getResourceAsStream((String) mapDesserts.get(cuisineId)));
            Set<Object> keysD = prop.keySet();
            for(Object k:keysD){
                String keyD = (String)k;
                System.out.println(keyD+", Dessert price: "+ prop.getProperty(keyD)+"PLN");
            }

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        do{

            System.out.println("-----Please choose the dessert.");
            String input = orderService.getUserInput().replace("\\u0020"," ");
            if(prop.containsKey(input)){
                String property = prop.getProperty(input);
                dessertPrice = Integer.valueOf(property);
                System.out.println("-----Dessert price: " + dessertPrice + "PLN");
                counter=0;
            }else {System.out.println("Wrong name. Please try again.");
                counter++;}
        }while (counter>0);


        return dessertPrice;
    }

    public int selectDrink() {
        Properties prop = new Properties();
        int drinkPrice=0;
        int counter=0;
        int extra=1;
        System.out.println("-----Drink menu-----");
        try {
            prop.load(CuisineImplementation.class.getClassLoader().getResourceAsStream("drinks.properties"));
            Set<Object> keys = prop.keySet();
            for(Object k:keys){
                String key = (String)k;
                System.out.println(key+", Price: "+ prop.getProperty(key)+"PLN");
            }

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        do{
            System.out.println("-----Please choose a drink.");
            System.out.println("-----Insert 'None' to finalize without adding a drink. ");

            String input = orderService.getUserInput().replace("\\u0020"," ");

            if(prop.containsKey(input)){
                String property = prop.getProperty(input);
                drinkPrice = Integer.valueOf(property);
                counter=0;
            }else {System.out.println("Wrong name. Please try again.");
                counter++;}
        }while (counter>0);

        do{
            System.out.println("Would you like to add a lemon or ice cubes for 1PLN?");
            System.out.println("1. Lemon, " + extra + "PLN");
            System.out.println("2. Ice cubes, " + extra + "PLN");
            System.out.println("3. Lemon + ice cubes, " + 2*extra + "PLN");
            System.out.println("4. None, 0PLN");

            String input = orderService.getUserInput();

            if(input.equals("1") || input.equals("2")) {
                drinkPrice += extra;
                counter = 0;
            }else if(input.equals("3")) {
                drinkPrice += 2 * extra;
                counter = 0;
            }else if(input.equals("4")) {
                counter = 0;
            }else {System.out.println("Wrong number. Please try again.");
                counter++;}
        }while (counter>0);
        System.out.println("Drink price: " + drinkPrice + "PLN");
        return drinkPrice;
    }

    public int totalPrice(int lunchPrice, int dessertPrice, int drinkPrice) {
        int sum = lunchPrice + dessertPrice + drinkPrice;
        System.out.println("Total price of the order: " + sum + "PLN");
        return sum;
    }
}
