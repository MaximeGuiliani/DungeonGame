package controller;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import map.Map;
import model.Fight.TURN;
import view.JavaFXView;

import javafx.scene.layout.HBox;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class App extends Application {
    public static double canvasHeight = 1080;
    public static double canvasWidth = 1920;
    public Map map = new Map(6, 6);
    public JavaFXView view;
    public Group root;
    public TURN diffilculty = TURN.PLAYER;

    /*
     * /** The main entry point for all JavaFX applications. The start method is
     * called after the init method has returned, and after the system is ready for
     * the application to begin running.
     *
     * <p> NOTE: This method is called on the JavaFX Application Thread. </p>
     *
     * @param primaryStage the primary stage for this application, onto which the
     * application scene can be set. Applications may create other stages, if
     * needed, but they will not be primary stages.
     * 
     * @throws Exception if something goes wrong
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage secondStage = new Stage();
        secondStage.setScene(new Scene(new HBox(4, new Label("Game Option"))));

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

        Button buttonEasy = new Button("easy");
        EventHandler<ActionEvent> easyDificulty = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                diffilculty = TURN.PLAYER;
            }

        };

        buttonEasy.setOnAction(easyDificulty);
        buttonEasy.setLayoutX(100);
        buttonEasy.setLayoutY(600);

        Button buttonhard = new Button("hard");
        EventHandler<ActionEvent> hardDificulty = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                diffilculty = TURN.ENEMY;
            }

        };

        buttonhard.setOnAction(hardDificulty);
        buttonhard.setLayoutX(300);
        buttonhard.setLayoutY(600);

        Group menuRoot = new Group(playButton(secondStage, primaryStage), button4tiers, button16neuf, buttonhard,
                buttonEasy, new Canvas(400, 800),
                new Text("       Maxime Guiliani's Game of dungeon \n\n"
                +"                          RULES   :  \n"
                        + "      L'objectif de ce jeu est d'arriver à la derniere salle du donjon tout en bas à droite et de battre le boss final.\n"
                        + "\n     pour se déplacer il faut utiliser les fleches directionelles  <  ^  v  >\n "

                       + "\n     inventaire:\n"  

                        + "       pour aller dans l'inventaire : ECHAP \n"
                        + "\n     NUMPAD 1 pour le heal" + "\n     NUMPAD 2 pour booster son attack \n     NUMPAD3 pour une petite surprise"
                        + "\n     Quand nous sommes dans l'inventaire on ne peut ni se battre ni se déplacer "
                        + "\n     pour quitter l'inventaire : ECHAP" +


                        "\n     combat:\n" +

                        "\n     f pour se battre contre un enemi (combat automatique)"
                        + "\n     dès que le combat est terminé le joueur peut se redéplacer"
                        + "\n     Bonne chance"


                ));
        Scene sceneMenu = new Scene(menuRoot);
        secondStage.setScene(sceneMenu);
        secondStage.show();

    }

    public void primaryStage(Stage primaryStage) throws FileNotFoundException {
        Button restart = new Button("restart");

        EventHandler<ActionEvent> eventplay = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    start(primaryStage);
                } catch (Exception e1) {

                    e1.printStackTrace();
                }
            }
        };

        restart.setOnAction(eventplay);
        restart.setLayoutX(200);
        restart.setLayoutY(300);
        restart.setVisible(false);
        this.view = new JavaFXView();
        view.diffilculty = diffilculty;
        // 0-3
        view.app.root = new Group(view.initializeWin(), view.initializeLose(), restart, view.initializeBGPosition());
        // 4
        view.app.root.getChildren().add(new Canvas(App.canvasWidth, App.canvasHeight));
        view.app.root.getChildren().get(2).setVisible(false);

        // 5-8
        view.initializeRoomObject();
        // 9
        view.app.root.getChildren().add(view.treasure());
        view.map();
        // 10
        view.app.root.getChildren().add(view.initializePlayerHPPosition());
        // 11
        view.app.root.getChildren().add(view.initializeDot());

        view.initializeItem();
        view.setObject();
        // 12
        view.app.root.getChildren().add(view.initializeEnemyPosition());
        // 13
        view.app.root.getChildren().add(view.initializePlayerPosition());
        Scene scene = new Scene(view.app.root);

        JavaFXController javaFXController = new JavaFXController(view.player);
        scene.setOnKeyPressed(javaFXController.eventHandler);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public Node playButton(Stage secondStage, Stage primaryStage) {
        Button play = new Button("play");

        EventHandler<ActionEvent> eventplay = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                secondStage.close();

                try {
                    primaryStage(primaryStage);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

            }
        };
        play.setOnAction(eventplay);
        play.setLayoutX(200);
        play.setLayoutY(700);
        return play;
    }

}
