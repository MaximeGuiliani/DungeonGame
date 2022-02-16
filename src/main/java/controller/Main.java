package controller;

import model.BossEnemy;
import model.Enemy;
import model.Fight;
import model.Player;
import model.Fight.TURN;
import view.JavaFXView;

public class Main {

    public static void main(String[] args){
        JavaFXView view = new JavaFXView();
        Player p = new Player(view);
        Enemy e = new BossEnemy();
        Fight fight = new Fight(p, e);
        fight.fight(TURN.PLAYER);
        
    }
}