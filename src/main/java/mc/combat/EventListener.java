package mc.combat;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;



public class EventListener extends attack implements Listener {

    private final Main plugin;
    private final java.util.Map<java.util.UUID,Long> cooldown = new java.util.HashMap<>();

    public EventListener(Main plugin) {

        this.plugin = plugin;
        
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onLeftClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Item item = event.getItem();
        PlayerInteractEvent.Action action = event.getAction();

        if (action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK || action == PlayerInteractEvent.Action.LEFT_CLICK_AIR){
            if(item instanceof customeitem.GenItem genItem && genItem.is("ac:two_maces")){

                String[] anims ={
                    "animation.twomaces.attack1",
                    "animation.twomaces.attack2",
                    "animation.twomaces.attack3"
                    
                };
                long[] cooldown ={
                    900,
                    1150,
                    1400
                };
                
                attackCombo(player, anims, cooldown);
                
            }
        }
    }

    
    
}
