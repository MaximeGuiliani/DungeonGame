package map;

import java.util.HashMap;
import java.util.Random;



public class Map {
    public int mapX;
    public int mapY;
    public Room[][] rooms;

    public Map(int mapX, int mapY) {
        this.mapX = mapX;
        this.mapY = mapY;
        this.rooms = new Room[mapX][mapY];
        generateMap();
    }

    public enum Direction {
        NORTH, EAST, SOUTH, WEST;
    }


    private void generateMap() {
        initializeBorder();
        for (int indexY = 1; indexY < mapY; indexY++) {
            for (int indexX = 1; indexX < mapX; indexX++) {
                HashMap<Direction, Boolean> isADoor = new HashMap<>();
                isADoor.put(Direction.NORTH, false);
                isADoor.put(Direction.WEST, false);
                if (rooms[indexX][indexY - 1].isSouthDoor()) {
                    isADoor.put(Direction.NORTH, true);

                }
                if (rooms[indexX - 1][indexY].isEastDoor()) {
                    isADoor.put(Direction.WEST, true);

                }
                Random random = new Random();
                Boolean randomBool = random.nextBoolean();
                if (randomBool) {
                    isADoor.put(Direction.EAST, true);
                    isADoor.put(Direction.SOUTH, false);
                } else {
                    isADoor.put(Direction.EAST, false);
                    isADoor.put(Direction.SOUTH, true);
                }

                if (indexX == mapX - 1) {
                    isADoor.put(Direction.EAST, false);
                    isADoor.put(Direction.SOUTH, true);
                }
                if (indexY == mapY - 1) {
                    isADoor.put(Direction.SOUTH, false);
                    isADoor.put(Direction.EAST, true);
                }
                if (indexY == mapY - 1 && indexX == mapX - 1) {
                    isADoor.put(Direction.SOUTH, false);
                    isADoor.put(Direction.EAST, false);
                }
                rooms[indexX][indexY] = new Room(isADoor);
            }
        }

    }

    private void initializeBorder() {
            for (int indexX = 0; indexX < mapX; indexX++) {
                HashMap<Direction, Boolean> isADoor = new HashMap<>();
                isADoor.put(Direction.NORTH, false);
    
                if (indexX == 0) {
                    isADoor.put(Direction.WEST, false);
                    Random rd = new Random();
                    int test = rd.nextInt(2);
                    if (test == 1) {
                        isADoor.put(Direction.SOUTH, true);
                        isADoor.put(Direction.EAST, false);
                    }
                    if (test == 0) {
                        isADoor.put(Direction.EAST, true);
                        isADoor.put(Direction.SOUTH, false);
                    }
    
                } else if (indexX == mapX - 1) {
                    isADoor.put(Direction.WEST, rooms[indexX - 1][0].isEastDoor());
                    isADoor.put(Direction.EAST, false);
                    isADoor.put(Direction.SOUTH, true);
    
                } else {
                    isADoor.put(Direction.WEST, rooms[indexX - 1][0].isEastDoor());
                    Random rd = new Random();
                    int test = rd.nextInt(2);
    
                    if (test == 0) {
                        isADoor.put(Direction.EAST, true);
                        isADoor.put(Direction.SOUTH, false);
                    }
                    if (test == 1) {
                        isADoor.put(Direction.EAST, false);
                        isADoor.put(Direction.SOUTH, true);
                    }
    
                }
                if (indexX != 0) {
                    isADoor.put(Direction.WEST, rooms[indexX - 1][0].areDoors.get(Direction.EAST));
                }
    
                rooms[indexX][0] = new Room(isADoor);
    
            }
    
            for (int indexY = 1; indexY < mapY; indexY++) {
                HashMap<Direction, Boolean> isADoor = new HashMap<>();
                isADoor.put(Direction.WEST, false);
                isADoor.put(Direction.NORTH, rooms[0][indexY - 1].areDoors.get(Direction.SOUTH));
    
                Random rd = new Random();
                int test = rd.nextInt(2);
    
                if (test == 0) {
                    isADoor.put(Direction.EAST, false);
                    isADoor.put(Direction.SOUTH, true);
                }
                if (test == 1) {
                    isADoor.put(Direction.EAST, true);
                    isADoor.put(Direction.SOUTH, false);
    
                }
    
                if (indexY == mapX - 1) {
                    isADoor.put(Direction.SOUTH, false);
                    isADoor.put(Direction.EAST, true);
    
                }
                this.rooms[0][indexY] = new Room(isADoor);
            }
        }
    

   

    public String mapInString() {
        String map = "";

        for (int index = 0; index < mapX; index++) {
            map += "____";
        }
        map += '\n';

        for (int indey = 0; indey < mapY; indey++) {
            String eAndW = "|";
            for (int index = 0; index < mapX; index++) {
                if (rooms[index][indey].areDoors.get(Direction.EAST)) {
                    if (rooms[index][indey].areDoors.get(Direction.SOUTH)) {
                        eAndW += "   _";
                    } else {
                        eAndW += "____";
    
                    }

                } else {

                    if (rooms[index][indey].areDoors.get(Direction.SOUTH)) {
                        eAndW += "   |";
                    } else {
                        eAndW += "___|";
    
                    }
                }
            }
            map += eAndW + '\n';
        }

        return map;

    }

}
