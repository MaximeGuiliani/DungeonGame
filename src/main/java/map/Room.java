package map;

import java.util.HashMap;
import java.util.Random;
import map.Map.Direction;
import model.BossEnemy;
import model.Enemy;
import model.LambdaEnemy;

public class Room {
    final public HashMap<Direction, Boolean> areDoors;
    public Boolean isHeal;
    public Boolean isEnemy;
    public Boolean isBoost;
    public Enemy enemy;

    public Room(HashMap<Direction, Boolean> areDoors) {
        this.areDoors = areDoors;
        whatIsInTheRoom();

    }

    public Boolean isNorthDoor() {
        return areDoors.get(Direction.NORTH);
    }

    public Boolean isEastDoor() {
        return areDoors.get(Direction.EAST);
    }

    public Boolean isSouthDoor() {
        return areDoors.get(Direction.SOUTH);
    }

    public Boolean isWestDoor() {
        return areDoors.get(Direction.WEST);
    }

    public void whatIsInTheRoom() {
        Random random = new Random();
        int randomInt = random.nextInt(6);

        if (randomInt < 2) {
            this.enemy = new LambdaEnemy();
            this.isEnemy = true;
            this.isBoost = false;
            this.isHeal = false;

        } else if (randomInt < 4) {
            this.isEnemy = false;
            this.isBoost = false;
            this.isHeal = true;

        } else {
            this.isEnemy = false;
            this.isBoost = true;
            this.isHeal = false;

        }
        if (!isNorthDoor() && !isWestDoor()) {
            this.isEnemy = false;
            this.isBoost = false;
            this.isHeal = false;

        }

        if (!isEastDoor() && !isSouthDoor()) {
            this.enemy = new BossEnemy();
            this.isEnemy = true;
            this.isBoost = false;
            this.isHeal = false;
        }

    }
}
