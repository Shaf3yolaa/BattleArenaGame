package com.battlearena.battlearenagame.models;

public class Archer extends Fighter {

    public Archer(String name){
        super("name",100,14);
        this.weapon =new Bow();
    }
}
