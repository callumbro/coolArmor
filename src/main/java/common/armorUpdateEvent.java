package common;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class armorUpdateEvent {

    private boolean helmetOn;
    private boolean chestplateOn;
    private boolean leggingsOn;
    private boolean bootsOn;

    @SubscribeEvent
    public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event)
    {

        if(event.entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entityLiving;

            //full set
            if (player.getCurrentArmor(3).getItem().equals(CoolArmor.itemCoolHelmet) &&
                player.getCurrentArmor(2).getItem().equals(CoolArmor.itemCoolChestplate) &&
                player.getCurrentArmor(1).getItem().equals(CoolArmor.itemCoolLeggings) &&
                player.getCurrentArmor(0).getItem().equals(CoolArmor.itemCoolBoots))
            {
                //player.knockBack();
            }

            //helmet
            if (player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem().equals(CoolArmor.itemCoolHelmet)){
                helmetOn = true;

                //auto feed player
                if (player.getFoodStats().getSaturationLevel() < 20.0F){
                    player.getFoodStats().setFoodSaturationLevel(20.0F);
                    player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + 1);
                    player.canEat(true);
                }

                //breathe underwater
                if (player.isInWater() && player.getAir() < 300) {
                    player.setAir(300);
                }
            }
            else if (helmetOn == true)
            {
                helmetOn = false;

            }

            //chestplate
            if (player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem().equals(CoolArmor.itemCoolChestplate)) {
                chestplateOn = true;

                player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 10, 3));
                player.capabilities.allowFlying = true;
            }
            else if (chestplateOn == true)
            {
                chestplateOn = false;

                player.capabilities.allowFlying = false;
                player.capabilities.isFlying = false;
            }

            //leggings
            if (player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem().equals(CoolArmor.itemCoolLeggings)) {
                leggingsOn = true;

                //increase player walk and fly speed
                player.capabilities.setPlayerWalkSpeed(0.2F);
                player.capabilities.setFlySpeed(0.2F);
            }
            else if (leggingsOn == true)
            {
                leggingsOn = false;

                player.capabilities.setPlayerWalkSpeed(0.1F);
                player.capabilities.setFlySpeed(0.05F);
            }

            //boots
            if (player.getCurrentArmor(0) != null && player.getCurrentArmor(0).getItem().equals(CoolArmor.itemCoolBoots)) {
                bootsOn = true;

                //auto step blocks
                player.stepHeight = 1.0F;

            }
            else if (bootsOn == true)
            {
                bootsOn = false;

                //back to normal step height
                player.stepHeight = 0.5F;
            }
        }

    }

    @SubscribeEvent
    public void onEntityLivingFallEvent(LivingFallEvent event)
    {
        if (leggingsOn = true) {
            event.setCanceled(true);
        }
    }
}
