package inventory;


import model.Player;


public class Heal extends Item{

    @Override
    void effect(Player player) {
        player.hp+= 10 ;       
    }
    
    
}
