package BattleArenaGame;
public abstract class Fighter {

    protected String name;
    protected double health;
    protected double speed;
    protected double x;
    protected double y;

    //class weapon
    protected weapon isweapon;
    protected boolean shoot =true;

    public Fighter(String name,double health,double speed){
        this.name=name;
        this.health=health;
        this.speed=speed;
        this.x=0;
        this.y=0;

    }

    //move of fighter
    public void moveup(){


    }
    public void movedown(){


    }
    public void moveright(){


    }
    public void moveleft(){


    }


















}
