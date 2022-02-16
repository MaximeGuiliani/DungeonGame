package view;

import javafx.scene.text.*;
import map.Room;
import map.Wall;
import model.Fight;
import model.Player;
import model.Fight.TURN;
import model.Player.Direction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controller.App;
import inventory.BoostATK;
import inventory.Heal;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class JavaFXView implements View {
    public Text dot = new Text();
    public Player player = new Player(this);
    public App app = new App();
    public ImageView playerImageView = new ImageView();
    public ImageView enemyImageView = new ImageView();
    public TURN diffilculty = TURN.PLAYER;
    private Room[][] rooms = app.map.rooms;
    public Text playerHP = new Text();
    private int mapX = 0;
    private int mapY = 0;
    public int hp = 100;
    double playerX = App.canvasWidth / 2;
    double playerY = App.canvasHeight / 2;
    public Text nbHeal = new Text("You have " + player.inventory.getHeal() + "  healing items");
    public Text nbATK = new Text("You have " + player.inventory.getAtkBoost() + " Atack boost Item");
    public int dotX = (int) (3 * App.canvasWidth / 4 - 100 + 24);
    public int dotY = 120;
    public double charSize = (App.canvasHeight / 4) / (app.map.mapX * 2);

    public void setInventory() {
        setHpposition();
        nbHeal.setText("You have " + player.inventory.getHeal() + " healing items");
        nbATK.setText("You have " + player.inventory.getAtkBoost() + " Atack boost Item");

    }

    public void moveCharacterModel(int moveTox, int moveToY, Direction direction) {
        setInventory();
        if (playerX > 0 && playerX < App.canvasWidth - 200 && playerY < App.canvasHeight - 200 && playerY > 0) {
            moveTotheDirection(moveTox, moveToY);
        } else {
            changeRoom(direction);
        }
    }

    public void changeRoom(Direction direction) {
        if (!rooms[mapX][mapY].isEnemy) {
            if (direction == Direction.NORTH && rooms[mapX][mapY].isNorthDoor()) {
                mapY -= 1;
                actionForEnteringRoom();
                dotY -= charSize * 1.2;
                dot.setY(dotY);
                dot.setX(dotX);
                setObject();
                setPlayerCenter();
            } else if (direction == Direction.SOUTH && rooms[mapX][mapY].isSouthDoor()) {
                mapY += 1;
                actionForEnteringRoom();
                dotY += charSize * 1.2;
                dot.setY(dotY);
                dot.setX(dotX);
                setObject();
                setPlayerCenter();
            }

            else if (direction == Direction.EAST && rooms[mapX][mapY].isEastDoor()) {
                mapX += 1;
                actionForEnteringRoom();
                dotX += charSize * 2.5;
                dot.setY(dotY);
                dot.setX(dotX);
                setObject();
                setPlayerCenter();
            }

            else if (direction == Direction.WEST && rooms[mapX][mapY].isWestDoor()) {
                mapX -= 1;
                actionForEnteringRoom();
                dotX -= charSize * 2.5;
                dot.setY(dotY);
                dot.setX(dotX);
                setObject();
                setPlayerCenter();
            } else {
                takeAStepBack();
            }
        } else {
            takeAStepBack();
        }

    }

    public void actionForEnteringRoom() {

        if (rooms[mapX][mapY].isBoost) {
            player.inventory.addItem(new BoostATK());
            rooms[mapX][mapY].isBoost = false;
        }
        if (rooms[mapX][mapY].isHeal) {
            player.inventory.addItem(new Heal());
            rooms[mapX][mapY].isHeal = false;
        }
        setEnemy(rooms[mapX][mapY].isEnemy);
        if (mapX == app.map.mapX - 1 && mapY == app.map.mapY - 1 && !rooms[mapX][mapY].isEnemy) {
            app.root.getChildren().remove(3, app.root.getChildren().size());
            app.root.getChildren().get(2).setVisible(true);
            app.root.getChildren().get(0).setVisible(true);
        }
    }

    @Override
    public void startAutoFight() {

        if (rooms[mapX][mapY].isEnemy) {
            Fight fight;
            fight = new Fight(player, rooms[mapX][mapY].enemy);
            if (!fight.fight(diffilculty) && player.hp > 0) {
                setEnemy(false);
                rooms[mapX][mapY].isEnemy = false;
                if (mapX == app.map.mapX - 1 && mapY == app.map.mapY - 1) {
                    app.root.getChildren().remove(3, app.root.getChildren().size());
                    app.root.getChildren().get(2).setVisible(true);
                    app.root.getChildren().get(0).setVisible(true);
                }
            } else {
                app.root.getChildren().remove(3, app.root.getChildren().size());
                app.root.getChildren().get(2).setVisible(true);
                app.root.getChildren().get(1).setVisible(true);
            }
        }
    }

    public void takeAStepBack() {
        if (playerX <= 0) {
            playerX += 100;
            playerImageView.setX(playerX);
            setHpposition();
        } else if (playerX >= App.canvasWidth - 200) {
            playerX -= 100;
            playerImageView.setX(playerX);
            setHpposition();
        } else if (playerY >= App.canvasHeight - 200) {
            playerY -= 100;
            playerImageView.setY(playerY);
            setHpposition();
        } else if (playerY <= 0) {
            playerY += 100;
            playerImageView.setY(playerY);
            setHpposition();
        }
    }

    public void moveTotheDirection(int x, int y) {
        playerX += x;
        playerY += y;
        playerImageView.setX(playerX);
        playerImageView.setY(playerY);
        setHpposition();
    }

    public void rotateCharacterModel(int angle) {
        playerImageView.setRotate(angle);
    }

    public void setPlayerCenter() {
        playerX = 500;
        playerY = App.canvasHeight / 2;
        playerImageView.setY(playerX);
        playerImageView.setX(playerY);
        setHpposition();
    }

    private void setEnemy(Boolean isEnemy) {
        if (isEnemy) {
            enemyImageView.setVisible(true);
        } else {
            enemyImageView.setVisible(false);
        }
    }

    public void setHpposition() {
        playerHP.setX(playerX);
        playerHP.setY(playerY - 100);
        playerHP.setText("" + player.hp);
    }

    private void removeAllObject() {
        ObservableList<Node> childrens = app.root.getChildren();
        childrens.get(6).setVisible(false);
        childrens.get(7).setVisible(false);
        childrens.get(8).setVisible(false);
        childrens.get(9).setVisible(false);
    }

    public void setObject() {
        removeAllObject();
        ObservableList<Node> childrens = app.root.getChildren();
        if (rooms[mapX][mapY].isNorthDoor()) {
            childrens.get(5).setVisible(false);
        } else {
            childrens.get(5).setVisible(true);
        }
        if (rooms[mapX][mapY].isEastDoor()) {
            childrens.get(6).setVisible(false);
        } else {
            childrens.get(6).setVisible(true);
        }
        if (rooms[mapX][mapY].isSouthDoor()) {
            childrens.get(7).setVisible(false);
        } else {
            childrens.get(7).setVisible(true);
        }
        if (rooms[mapX][mapY].isWestDoor()) {
            childrens.get(8).setVisible(false);
        } else {
            childrens.get(8).setVisible(true);
        }
        if (app.map.mapX - 1 == mapX && app.map.mapY - 1 == mapY) {
            childrens.get(9).setVisible(true);
        } else {
            childrens.get(9).setVisible(false);
        }
    }

    public void initializeItem() {
        nbHeal.setVisible(false);
        nbATK.setVisible(false);
        nbHeal.setFill(Color.WHITE);
        nbATK.setFill(Color.WHITE);
        nbATK.setX(App.canvasWidth / 2);
        nbATK.setY(App.canvasHeight / 2);
        nbHeal.setX(100);
        nbHeal.setY(200);
        nbATK.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        nbHeal.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        app.root.getChildren().add(nbATK);
        app.root.getChildren().add(nbHeal);
    }

    public void initializeRoomObject() {
        ImageView northWall;
        northWall = new Wall().initializeWalltop(0, 0, (int) App.canvasWidth, 50);
        ImageView eastWall = new Wall().initializeWall((int) App.canvasWidth - 50, 0, 50, (int) App.canvasHeight);
        ImageView southWall = new Wall().initializeWalltop(0, (int) App.canvasHeight - 100, (int) App.canvasWidth, 50);
        ImageView westWall = new Wall().initializeWall(0, 0, 50, (int) App.canvasHeight);
        // 5
        app.root.getChildren().add(northWall);
        // 6
        app.root.getChildren().add(eastWall);
        // 7
        app.root.getChildren().add(southWall);
        // 8
        app.root.getChildren().add(westWall);
    }

    public Node initializePlayerHPPosition() {
        Text hp = playerHP;
        hp.setFill(Color.WHITE);
        hp.setX(App.canvasWidth / 2);
        hp.setY(App.canvasHeight / 2);
        return hp;
    }

    public void map() {
        Text mapInString = new Text(app.map.mapInString());
        mapInString.setX(3 * App.canvasWidth / 4 - 100);
        mapInString.setY(100);
        mapInString.setFill(Color.WHITE);
        mapInString.setFont(Font.font("Monospaced", (App.canvasHeight / 4) / (app.map.mapX * 2)));
        app.root.getChildren().add(mapInString);
    }

    public Node initializeBGPosition() {
        FileInputStream inputstreamBG;
        ImageView imageViewBG = new ImageView();
        try {
            inputstreamBG = new FileInputStream("img/background/bg" + "" + ".png");
            Image background = new Image(inputstreamBG);
            imageViewBG.setImage(background);
            imageViewBG.setX(0);
            imageViewBG.setY(0);
            imageViewBG.setFitHeight(App.canvasHeight);
            imageViewBG.setFitWidth(App.canvasWidth);
            imageViewBG.setPreserveRatio(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return imageViewBG;
    }

    public Node initializeLose() {
        ImageView imageViewLose = new ImageView();
        FileInputStream inputstream;
        try {
            inputstream = new FileInputStream("img/background/lose" + "" + ".png");
            Image backgroundLose = new Image(inputstream);
            imageViewLose.setImage(backgroundLose);
            imageViewLose.setX(0);
            imageViewLose.setY(0);
            imageViewLose.setFitHeight(App.canvasHeight);
            imageViewLose.setFitWidth(App.canvasWidth);
            imageViewLose.setPreserveRatio(false);
            imageViewLose.setVisible(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return imageViewLose;
    }

    public Node initializeWin() {
        ImageView imageViewWin = new ImageView();
        FileInputStream inputstream;
        try {
            inputstream = new FileInputStream("img/background/win" + "" + ".png");
            Image backgroundLose = new Image(inputstream);
            imageViewWin.setImage(backgroundLose);
            imageViewWin.setX(0);
            imageViewWin.setY(0);
            imageViewWin.setFitHeight(App.canvasHeight);
            imageViewWin.setFitWidth(App.canvasWidth);
            imageViewWin.setPreserveRatio(false);
            imageViewWin.setVisible(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return imageViewWin;
    }

    public Node treasure() {
        ImageView imageViewTreasure = new ImageView();

        FileInputStream inputstream;
        try {
            inputstream = new FileInputStream("img/item/chest.png");

            Image treasure = new Image(inputstream);
            imageViewTreasure.setImage(treasure);
            imageViewTreasure.setX(App.canvasHeight / 2);
            imageViewTreasure.setY(App.canvasHeight / 2);
            imageViewTreasure.setFitHeight(200);
            imageViewTreasure.setFitWidth(200);
            imageViewTreasure.setPreserveRatio(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return imageViewTreasure;

    }

    public Node initializeDot() {
        dot.setText("O");
        dot.setX(dotX);
        dot.setY(dotY);
        dot.setFill(Color.RED);
        return dot;
    }

    public Node initializePlayerPosition() {

        FileInputStream inputstream;
        try {
            inputstream = new FileInputStream("img/playerModel/PM" + "" + ".gif");

            Image playerModel = new Image(inputstream);
            playerImageView.setImage(playerModel);
            playerImageView.setX(App.canvasWidth / 2);
            playerImageView.setY(App.canvasHeight / 2);
            playerImageView.setFitHeight(200);
            playerImageView.setFitWidth(200);
            playerImageView.setPreserveRatio(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return playerImageView;
    }

    public Node initializeEnemyPosition() {

        FileInputStream inputstream;
        try {
            inputstream = new FileInputStream("img/playerModel/shrek" + "" + ".gif");

            Image playerModel = new Image(inputstream);
            enemyImageView.setImage(playerModel);
            enemyImageView.setX(App.canvasWidth / 2);
            enemyImageView.setY(App.canvasHeight / 2);
            enemyImageView.setFitHeight(350);
            enemyImageView.setFitWidth(350);
            enemyImageView.setVisible(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return enemyImageView;

    }

    public Node button4tiers() {
        Button button4tiers = new Button("4/3");
        EventHandler<ActionEvent> event4tier = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                App.canvasHeight = 1080;
                App.canvasWidth = 1440;

            }

        };
        button4tiers.setLayoutX(200);
        button4tiers.setLayoutY(400);

        button4tiers.setOnAction(event4tier);
        return button4tiers;
    }

    public Node button16neuf() {
        Button button16neuf = new Button("16/9");
        EventHandler<ActionEvent> event16neuf = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                App.canvasHeight = 1080;
                App.canvasWidth = 1920;
            }

        };

        button16neuf.setOnAction(event16neuf);
        button16neuf.setLayoutX(200);
        button16neuf.setLayoutY(500);
        return button16neuf;
    }

    @Override
    public void openInventory() {
        nbHeal.setVisible(true);
        nbATK.setVisible(true);

    }

    @Override
    public void closeInventory() {
        nbHeal.setVisible(false);
        nbATK.setVisible(false);
    }

}
