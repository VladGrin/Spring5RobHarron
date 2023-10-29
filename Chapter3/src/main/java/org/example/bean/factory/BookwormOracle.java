package org.example.bean.factory;

public class BookwormOracle implements Oracle {

    private Encyclopedia encyclopedia;


    public void setEncyclopedia(Encyclopedia encyclopedia) {
        this.encyclopedia = encyclopedia;
    }

    public String defineMeaningOfLife() {
        return "Encyclopedias are а waste of money - go see the world instead";
    }
}
