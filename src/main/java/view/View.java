package view;

import model.Player.Direction;

public interface View {

    void rotateCharacterModel(int angle);

    void moveCharacterModel(int x, int y, Direction direction);

    void setInventory();

    void startAutoFight() ;

    void openInventory();

    void closeInventory();
}
