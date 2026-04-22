package mc.combat;

import cn.nukkit.item.Item;
import cn.nukkit.item.customitem.CustomItem;
import cn.nukkit.item.customitem.CustomItemDefinition;
import cn.nukkit.item.customitem.data.CreativeCategory;
import cn.nukkit.item.customitem.data.CreativeGroup;

public class customeitem {
    
public static class GenItem extends Item implements CustomItem {

    private final String customName;
    private final String textureName;
    private final boolean offHand;
    private final String tag;

    public GenItem(String id, String name, String texture,String tag ,boolean offHand){
        super(id);
        this.customName = name;
        this.textureName = texture;
        this.offHand = offHand;
        this.tag = tag;
    }
    
    @Override
    public CustomItemDefinition getDefinition() {
        return CustomItemDefinition
        .simpleBuilder(this)
        .creativeCategory(CreativeCategory.EQUIPMENT)
        .creativeGroup(CreativeGroup.SWORD)
        .icon(textureName)
        .name(customName)
        .allowOffHand(offHand)
        .handEquipped(true)
        .glint(true)
        .damage(5)
        .maxStackSize(1)
        .canDestroyInCreative(false)
        .tag("ac:weapon", tag)
        .build();
    }
    @Override
    public boolean is(String itemTag){

        if(itemTag.equals("ac:weapon") || itemTag.equals(tag)){
            return true;
        }
        else{
            return false;
        }
    }
}

public static class Hammer extends GenItem {
    public Hammer(){
        super("ac:oak_greathammer", "Hammer", "oak_greathammer","ac:two_maces", true);
    }
    
}

}
