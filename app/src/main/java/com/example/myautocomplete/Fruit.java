package com.example.myautocomplete;

public class Fruit {

    int fruitid;
    String fruitname;

    public Fruit() {
    }

    public Fruit(int fruitid, String fruitname) {
        this.fruitid = fruitid;
        this.fruitname = fruitname;
    }

    public int getFruitid() {
        return fruitid;
    }

    public void setFruitid(int fruitid) {
        this.fruitid = fruitid;
    }

    public String getFruitname() {
        return fruitname;
    }

    public void setFruitname(String fruitname) {
        this.fruitname = fruitname;
    }

    @Override
    public String toString() {
        return  fruitname ;
    }
}
