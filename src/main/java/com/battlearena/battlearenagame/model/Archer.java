package com.battlearena.battlearenagame.model;

public class Archer extends Fighter {

    public Archer(String name){
        super("Archer",100,14);
        this.isweapon =new Bow();
    }
}
