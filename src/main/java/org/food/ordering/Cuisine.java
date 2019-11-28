package org.food.ordering;

public interface Cuisine {
    public String getMenu(int cuisineId);
    public int selectLunch(int cuisineId);
    public int selectDessert(int cuisineId);
    public int selectDrink();
    public int totalPrice(int lunchPrice, int dessertPrice, int drinkPrice);
}
