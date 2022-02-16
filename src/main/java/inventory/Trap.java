package inventory;

import model.Player;

public class Trap extends Item{

    @Override
    void effect(Player player) {
        player.hp-= 10 ;       
    }
    
}
