package org.food.ordering;

import java.util.Scanner;

public class OrderService {

    public String getUserInput() {

            Scanner sc = new Scanner(System.in);
            return sc.nextLine();
    }

}

