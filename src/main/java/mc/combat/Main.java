package mc.combat;



import cn.nukkit.plugin.*;
import cn.nukkit.registry.RegisterException;
import cn.nukkit.registry.Registries;
import cn.nukkit.utils.TextFormat;;

public class Main extends PluginBase {

    public static Main INSTANCE;
    
    @Override
    public void onEnable(){
        this.getLogger().info(TextFormat.AQUA + "Combat System has been enabled!");
        this.getLogger().info(TextFormat.AQUA + "V0.0.0.5");
        
        this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
        
    }

    @Override
    public void onLoad(){
        INSTANCE = this;
        try {

            Registries.ITEM.registerCustomItem(this,customeitem.Hammer.class);
            
        } catch (RegisterException e) {
            // TODO: handle exception
            this.getLogger().error("Failed to register custom items!");
            throw new RuntimeException(e);
        }
        

        

    }

}