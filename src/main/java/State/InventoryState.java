package State;

import controller.JavaFXController;

public class InventoryState extends State {

    public InventoryState(JavaFXController controller) {
        super(controller);
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
        controller.player.heal();

    }

    @Override
    public void numpad2Pressed(JavaFXController controller) {
        controller.player.BoostATK();

    }

    @Override
    public void numpad3Pressed(JavaFXController controller) {
        controller.player.Trap();

    }

    @Override
    public void escapePressed(JavaFXController controller) {
        controller.player.closeInventory();
        controller.changeState(new GameState(controller));
        
    }

    @Override
    public void fightPressed(JavaFXController controller) {

    }

}
