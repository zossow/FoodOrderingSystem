package org.food.ordering;

public class Main {
    public static void main(String[] args) {
        int counter=1;
        int counterMenu=1;
        OrderService orderService = new OrderService();
        System.out.println("Welcome to the FoodOrderingSystem!");
        System.out.println("Please choose the option 1 or 2.");

        do {
            System.out.println("1. MENU");
            System.out.println("2. EXIT");
            String userChoice = orderService.getUserInput();
            if (userChoice.equals("1")) {
                System.out.println("Please choose preferable cuisine ");
                System.out.println("1. Polish");
                System.out.println("2. Mexican");
                System.out.println("3. Italian");

                do {
                    try {

                        String userChoiceCuisine = orderService.getUserInput();
                        int intUserChoiceCuisine = Integer.valueOf(userChoiceCuisine);
                        Cuisine cuisine = new CuisineImplementation();

                        if (intUserChoiceCuisine < 4) {
                            cuisine.getMenu(intUserChoiceCuisine);
                            int lunchPrice = cuisine.selectLunch(intUserChoiceCuisine);
                            int dessertPrice = cuisine.selectDessert(intUserChoiceCuisine);
                            int drinkPrice = cuisine.selectDrink();
                            int sum = cuisine.totalPrice(lunchPrice, dessertPrice, drinkPrice);
                        } else {
                            System.out.println("Please try again");
                            counterMenu++;
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Please try again.");
                    }
                }while (counterMenu > 0);

            } else if (userChoice.equals("2")) {
                counter = -1;
            } else System.out.println("Please try again.");
            counter++;
        }while (counter > 0) ;


        System.out.println("Thank you for the order.");

    }
}
