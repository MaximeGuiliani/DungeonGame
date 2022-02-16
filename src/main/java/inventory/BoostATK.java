package inventory;


import model.Player;


public class BoostATK extends Item{

    @Override
    void effect(Player player) {
        player.atk += 10;        
    }

    
    
}
