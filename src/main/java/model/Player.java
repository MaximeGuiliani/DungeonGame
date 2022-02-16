package model;



import inventory.Inventory;
import inventory.Inventory.touche;
import view.View;

public class Player extends Fighter{
    View view;
    int  step;
    private boolean isLookingSouth, isLookingNorth, isLookingEast, isLookingWest;
    public boolean isInfight;
    public Inventory inventory;

    public enum Direction {
        NORTH, EAST, SOUTH , WEST ;
     }

    public Player(View view) {
        this.isInfight = false;
        this.view = view;
        this.hp = 100;
        this.atk = 10;
        this.step = 50;
        this.inventory = new Inventory(this);
        resetLookDirection();
        this.isLookingNorth = true;

    }
    public Player() {
        this.isInfight = false;
        this.hp = 100;
        this.atk = 10;
        this.step = 50;
        this.inventory = new Inventory(this);
        resetLookDirection();
        this.isLookingNorth = true;

    }

    public void goNorth() {
        if (isLookingNorth == true) {
            view.moveCharacterModel(0, -step, Direction.NORTH);
        } else {
            view.rotateCharacterModel(0);
            resetLookDirection();
            isLookingNorth = true;
        }

    }

    public void goSouth() {
        if (isLookingSouth == true) {
            view.moveCharacterModel(0, step, Direction.SOUTH);
        } else {
            view.rotateCharacterModel(180);
            resetLookDirection();
            isLookingSouth = true;
        }

    }

    public void goEast()  {
        if (isLookingEast == true) {
            view.moveCharacterModel(step, 0,  Direction.EAST);
        } else {
            view.rotateCharacterModel(90);
            resetLookDirection();
            isLookingEast = true;
        }

    }
    public void heal(){
        this.inventory.useItem(touche.NUMPAD1);
        view.setInventory();


    }
    public void BoostATK(){
        this.inventory.useItem(touche.NUMPAD2);
        view.setInventory();

        
    }
    public void Trap(){
        this.inventory.useItem(touche.NUMPAD3);
        view.setInventory();

        
    }


    public void goWest() {
        if (isLookingWest == true) {
            view.moveCharacterModel(-step, 0,  Direction.WEST);
        } else {
            view.rotateCharacterModel(270);
            resetLookDirection();
            isLookingWest = true;
        }
    }

    public void resetLookDirection() {
        isLookingNorth = false;
        isLookingEast = false;
        isLookingSouth = false;
        isLookingWest = false;

    }

    public void fight() {
        view.startAutoFight();
    }

    public void openInventory(){
        view.openInventory();

    }

    public void closeInventory() {
        view.closeInventory();

    }



    
}
