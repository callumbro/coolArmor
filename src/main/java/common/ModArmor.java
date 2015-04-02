package common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;

/**
 * Created by Callum on 04/01/2015.
 */
public class ModArmor extends ItemArmor
{

    public ModArmor(String unlocalizedName, ArmorMaterial material, int type)
    {
        super(material, 0, type);
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.setTextureName("coolarmor:item_" + unlocalizedName);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        return "coolarmor:textures/models/armor/armor_" + ((this.armorType == 2) ? "2" : "1") + ".png";
    }
}
