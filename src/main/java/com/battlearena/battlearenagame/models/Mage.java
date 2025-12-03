package com.battlearena.battlearenagame.models;

public class Mage extends Fighter {

    public Mage(String name){
        super("name",100,10);
        this.weapon =new MagicWand();
    }

}
