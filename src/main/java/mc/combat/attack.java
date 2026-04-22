package mc.combat;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import cn.nukkit.Player;
import cn.nukkit.network.protocol.AnimateEntityPacket;

public class attack {
    private static final Map<UUID,Long> nextAttack = new HashMap<>();
    private static final Map<UUID,Integer> comboStage = new HashMap<>();
    private static final Map<UUID,Long> lastAttack = new HashMap<>();

    /**
     * @param animation
     * @param cooldown
     */
    public void attackCombo(Player player,String[] AnimationName,long[] coolDown){

        long currentTime = System.currentTimeMillis();
        UUID uuid = player.getUniqueId();

        if(currentTime < nextAttack.getOrDefault(uuid, 0L)) return;
        if(currentTime - lastAttack.getOrDefault(uuid, 0L) > 2500) {
            comboStage.put(uuid, 0);
        }

        int currentStage = comboStage.getOrDefault(uuid, 0);
        String currentAnim= AnimationName[currentStage];
        long currentCooldown = coolDown[currentStage];

        sendAnim(player, currentAnim);

        String msg = (currentStage == AnimationName.length - 1) ? "§l§cHEAVY ATTACK!" : "§l§eCOMBO " + (currentStage + 1);
        player.sendActionBar(msg);

        lastAttack.put(uuid, currentTime);
        nextAttack.put(uuid, currentTime + currentCooldown);

        int nextStage = (currentStage + 1) % AnimationName.length;
        comboStage.put(uuid, nextStage);
    }

    public void sendAnim(Player player, String AnimationName){
        

        AnimateEntityPacket pk = new AnimateEntityPacket();

        pk.animation = AnimationName;
        pk.blendOutTime = 0.1f;
        pk.entityRuntimeIds.add(player.getId());

        pk.nextState = ""; 
        pk.stopExpression = "";
        pk.controller = "";
        pk.stopExpressionVersion = 0;

        cn.nukkit.Server.broadcastPacket(player.getViewers().values(), pk);
        player.dataPacket(pk);

        
    }
}
