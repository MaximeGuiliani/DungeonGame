package inventory;

import java.util.HashMap;

import model.Player;

public class Inventory {

    public enum touche {
        NUMPAD1, NUMPAD2,NUMPAD3;

    }

    Player player;
    HashMap<Integer, Integer> items;

    public Inventory(Player player) {
        this.player = player;
        this.items = new HashMap<>();
        initializeItems();

    }

    public void useItem(touche touche) {
        if (touche == inventory.Inventory.touche.NUMPAD1 && getHeal() > 0) {
            new Heal().effect(player);
            items.put(0, getHeal() - 1);
        }
        if (touche == inventory.Inventory.touche.NUMPAD2 && getAtkBoost() > 0) {
            new BoostATK().effect(player);
            items.put(1, getAtkBoost() - 1);
        }
        if (touche == inventory.Inventory.touche.NUMPAD3) {
            new Trap().effect(player);
        }

    }

    public void addItem(Item item) {
        if (item.getClass() == (Heal.class)) {

            items.put(0, getHeal() + 1);
        }
        if (item.getClass() == (BoostATK.class)) {

            items.put(1, getAtkBoost() + 1);
        }

    }

    public int getHeal() {
        return items.get(0);
    }

    public int getAtkBoost() {
        return items.get(1);
    }

    public void initializeItems() {
        items.put(0, 1);
        items.put(1, 1);
    }
}
