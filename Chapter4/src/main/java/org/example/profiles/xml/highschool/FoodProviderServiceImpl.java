package org.example.profiles.xml.highschool;

import org.example.profiles.xml.Food;
import org.example.profiles.xml.FoodProviderService;

import java.util.ArrayList;
import java.util.List;

public class FoodProviderServiceImpl implements FoodProviderService {

    @Override
    public List<Food> provideLunchSet() {
        List<Food> lunchSet = new ArrayList<>();
        lunchSet. add (new Food ( "Coke"));
        lunchSet.add(new Food("Hamburger"));
        lunchSet.add(new Food("French Fries"));
        return lunchSet;
    }
}
