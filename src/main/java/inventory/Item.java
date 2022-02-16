package inventory;

import model.Player;

public abstract class Item {
    int x;
    int y;
    abstract void effect(Player player);

}
