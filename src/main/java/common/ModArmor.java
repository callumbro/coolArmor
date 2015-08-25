package common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;

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

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {

/*
        if (player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem().equals(CoolArmor.itemCoolLeggings)) {
            //player.moveEntityWithHeading(player.moveStrafing, player.moveForward * 1.005F);
            player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
           // player.capabilities.setPlayerWalkSpeed(0.2F);
            //player.capabilities.setFlySpeed(0.2F);
        }
        else {
            player.getEntityAttribute(SharedMonsterAttributes.movementSpeed).removeModifier(legsSpeed);
            //player.capabilities.setPlayerWalkSpeed(0.1F);
            //player.capabilities.setFlySpeed(0.05F);
        }
*/


    }
}
