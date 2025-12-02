package com.battlearena.battlearenagame.model;

public class Archer extends Fighter {

    public Archer(String name){
        super("name",100,14);
        this.weapon =new Bow();
    }
}
