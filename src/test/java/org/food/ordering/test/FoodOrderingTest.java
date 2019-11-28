package org.food.ordering.test;

import org.food.ordering.Cuisine;
import org.food.ordering.CuisineImplementation;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class FoodOrderingTest {

    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();


    @Test
    public void getMenuTest() {
        Cuisine cuisine = new CuisineImplementation();
        assertEquals("polish.properties", cuisine.getMenu(1));
        assertEquals("mexican.properties", cuisine.getMenu(2));
        assertEquals("italian.properties", cuisine.getMenu(3));
        assertEquals(null, cuisine.getMenu(5));

    }

    @Test
    public void selectLunchTest() {
        Cuisine cuisine = new CuisineImplementation();
        systemInMock.provideLines("Barszcz");
        assertEquals(12,cuisine.selectLunch(1));
        systemInMock.provideLines("Enmolada");
        assertEquals(16,cuisine.selectLunch(2));
        systemInMock.provideLines("Carbonara");
        assertEquals(21,cuisine.selectLunch(3));

    }

    @Test
    public void selectDessertTest() {
        Cuisine cuisine = new CuisineImplementation();
        systemInMock.provideLines("Sernik");
        assertEquals(9,cuisine.selectDessert(1));
        systemInMock.provideLines("Churros");
        assertEquals(8,cuisine.selectDessert(2));
        systemInMock.provideLines("Tiramisu");
        assertEquals(8,cuisine.selectDessert(3));
    }

    @Test
    public void selectDrinkTest() {
        Cuisine cuisine = new CuisineImplementation();
        systemInMock.provideLines("Water","1");
        assertEquals(6,cuisine.selectDrink());
        systemInMock.provideLines("Tea","3");
        assertEquals(8,cuisine.selectDrink());
        systemInMock.provideLines("Orange Juice","2");
        assertEquals(8,cuisine.selectDrink());
        systemInMock.provideLines("None","4");
        assertEquals(0,cuisine.selectDrink());

    }

    @Test
    public void totalPriceTest() {
        Cuisine cuisine = new CuisineImplementation();
        assertEquals(30,cuisine.totalPrice(9,11,10));
        assertEquals(0,cuisine.totalPrice(0,0,0));
        assertEquals(-8,cuisine.totalPrice(-1,-2,-5));
    }
}
