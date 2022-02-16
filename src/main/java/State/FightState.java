package State;

import controller.JavaFXController;

public class FightState extends State {

    public FightState(JavaFXController controller) {
        super(controller);
        if (controller.player.isInfight == false) {
            controller.changeState(new GameState(controller));
        }
    }

    @Override
    public void upPresed(JavaFXController controller) {
    }

    @Override
    public void downPressed(JavaFXController controller) {

    }

    @Override
    public void leftPressed(JavaFXController controller) {

    }

    @Override
    public void rightPressed(JavaFXController controller) {

    }

    @Override
    public void numpad1Pressed(JavaFXController controller) {

    }

    @Override
    public void numpad2Pressed(JavaFXController controller) {

    }

    @Override
    public void numpad3Pressed(JavaFXController controller) {

    }

    @Override
    public void escapePressed(JavaFXController controller) {
    }

    @Override
    public void fightPressed(JavaFXController controller) {

    }
}
