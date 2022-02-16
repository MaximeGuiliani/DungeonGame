package controller;

import State.GameState;
import State.State;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.Player;

public class JavaFXController {
    EventHandler<? super KeyEvent> eventHandler;
    private State state;
    public Player player;
    public JavaFXController controller = this;

    JavaFXController(Player player) {
        this.player = player;
        this.state = new GameState(this);

        eventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                case UP:
                    state.upPresed(controller);
                    break;
                case DOWN:
                    state.downPressed(controller);
                    break;
                case LEFT:
                    state.leftPressed(controller);
                    break;
                case RIGHT:
                    state.rightPressed(controller);
                    break;
                case NUMPAD1:
                    state.numpad1Pressed(controller);
                    break;
                case NUMPAD2:
                    state.numpad2Pressed(controller);
                    break;
                case NUMPAD3:
                    state.numpad3Pressed(controller);
                    break;
                case ESCAPE:
                    state.escapePressed(controller);
                    break;
                    case F:
                    state.fightPressed(controller);
                    break;
                default:
                    break;
                }
            }
        };
    }

    public void changeState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
