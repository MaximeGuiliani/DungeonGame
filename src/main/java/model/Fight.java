package model;

import java.util.concurrent.TimeUnit;

public class Fight {
    Player player;
    Enemy enemy;
    public Boolean isFight;

    public Fight(Player player, Enemy enemy)  {
        this.isFight = true;
        this.player = player;
        this.enemy = enemy;
    }

    public enum TURN{
        PLAYER,ENEMY;
    }

    public boolean fight(TURN turn) {
        try {
            player.view.setInventory();
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (player.hp <= 0 || enemy.hp <= 0) {
            player.isInfight = false;
            this.isFight = false;
            player.atk +=10;
            return player.hp > 0; 
        }else if (turn == TURN.PLAYER) {
            enemy.hp -= player.atk;
            fight(TURN.ENEMY);
        } else {
            player.hp -= enemy.atk;
            fight(TURN.PLAYER);
        }
        return false;

    }

}
