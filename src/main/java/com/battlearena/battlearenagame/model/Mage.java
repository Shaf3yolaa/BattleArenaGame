package com.battlearena.battlearenagame.model;

public class Mage extends Fighter {

    public Mage(String name){
        super("Mage",100,10);
        this.isweapon =new MagicWand();
    }

}
