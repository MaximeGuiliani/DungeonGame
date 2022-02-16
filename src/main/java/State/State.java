package State;

import controller.JavaFXController;

public abstract class State {

    JavaFXController controller;

    public State(JavaFXController controller) {
        this.controller = controller;

    }

    public abstract void upPresed(JavaFXController controller);

    public abstract void downPressed(JavaFXController controller);

    public abstract void leftPressed(JavaFXController controller);

    public abstract void rightPressed(JavaFXController controller);

    public abstract void numpad1Pressed(JavaFXController controller);

    public abstract void numpad2Pressed(JavaFXController controller);

    public abstract void numpad3Pressed(JavaFXController controller);

    public abstract void escapePressed(JavaFXController controller);

    public abstract void fightPressed(JavaFXController controller);

    

}
