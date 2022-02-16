package State;

import controller.JavaFXController;

public class GameState extends State {

    public GameState(JavaFXController controller) {
        super(controller);
    }

    @Override
    public void upPresed(JavaFXController controller) {
        controller.player.goNorth();

    }

    @Override
    public void downPressed(JavaFXController controller) {
        controller.player.goSouth();

    }

    @Override
    public void leftPressed(JavaFXController controller) {
        controller.player.goWest();

    }

    @Override
    public void rightPressed(JavaFXController controller) {
        controller.player.goEast();

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
        controller.player.openInventory();
        controller.changeState(new InventoryState(controller));

    }

    @Override
    public void fightPressed(JavaFXController controller) {
        controller.player.fight();
        if (controller.player.isInfight) {
            controller.changeState(new FightState(controller));
        }

    }

}
