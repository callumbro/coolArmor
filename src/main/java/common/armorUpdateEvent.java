package common;

import com.typesafe.config.ConfigException;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import java.util.UUID;

public class armorUpdateEvent {

    private boolean helmetOn;
    private boolean chestplateOn;
    private boolean leggingsOn;
    private boolean bootsOn;

    //speed boost attribute
    private static final UUID speedBoostUUID = UUID.fromString("4f0c407f-2c09-4aa8-8be7-6c5ca5dd7822");
    AttributeModifier speedBoost = new AttributeModifier(speedBoostUUID, "Speed Boost", 0.5D, 2).setSaved(false);

    //sprint boost attribute
    private static final UUID sprintBoostUUID = UUID.fromString("6ebce7a2-8f59-405b-9dd5-e4a2eb53ca00");
    AttributeModifier sprintBoost = new AttributeModifier(sprintBoostUUID, "Sprint Boost", 0.5D, 2).setSaved(false);

    //knockback attribute
    private static final UUID knockbackUUID = UUID.fromString("cdb5ef05-b104-4ebb-b6bd-9028e9ee609e");
    AttributeModifier knockbackResist = new AttributeModifier(knockbackUUID, "Knockback Resistance", 1.0D, 0).setSaved(false);

    @SubscribeEvent
    public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event)
    {

        if(event.entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entityLiving;

            IAttributeInstance movement = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            IAttributeInstance knockback = player.getEntityAttribute(SharedMonsterAttributes.knockbackResistance);

            if (movement.getModifier(speedBoostUUID) != null)
            {
                movement.removeModifier(speedBoost);
            }
            if (movement.getModifier(sprintBoostUUID) != null)
            {
                movement.removeModifier(sprintBoost);
            }
            if (knockback.getModifier(knockbackUUID) != null)
            {
                knockback.removeModifier(knockbackResist);
            }

            //helmet
            if (player.getCurrentArmor(3) != null && player.getCurrentArmor(3).getItem().equals(CoolArmor.itemCoolHelmet)){
                helmetOn = true;

                //auto feed player food
                if (player.getFoodStats().getFoodLevel() < 20.0F)
                {
                    player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + 1);
                }

                //auto feed player saturation
                if (player.getFoodStats().getSaturationLevel() < 20.0F)
                {
                    player.getFoodStats().setFoodSaturationLevel(player.getFoodStats().getSaturationLevel() + 1.0F);
                }

                //can always eat despite being full on hunger
                player.canEat(true);

                //breathe underwater
                if (player.isInWater() && player.getAir() < 300) {
                    player.setAir(player.getAir() + 2);
                }

                //remove bad potion effects
                player.removePotionEffect(Potion.wither.id);
                player.removePotionEffect(Potion.confusion.id);
                player.removePotionEffect(Potion.blindness.id);
                player.removePotionEffect(Potion.harm.id);
                player.removePotionEffect(Potion.poison.id);
                player.removePotionEffect(Potion.hunger.id);

                //night vision
                player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 10, 0));
            }
            else if (helmetOn == true)
            {
                helmetOn = false;

            }

            //chestplate
            if (player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem().equals(CoolArmor.itemCoolChestplate)) {
                chestplateOn = true;

                player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 10, 2));
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

                //increase movement and sprint speed
                movement.applyModifier(speedBoost);
                if (player.isSprinting())
                {
                    movement.applyModifier(sprintBoost);
                }

                //increase player fly speed
                //player.capabilities.setFlySpeed(0.2F);
            }
            else if (leggingsOn == true)
            {
                leggingsOn = false;

                movement.removeModifier(speedBoost);
                movement.removeModifier(sprintBoost);
                //player.capabilities.setFlySpeed(0.05F);
            }

            //boots
            if (player.getCurrentArmor(0) != null && player.getCurrentArmor(0).getItem().equals(CoolArmor.itemCoolBoots)) {
                bootsOn = true;

                if (!player.isSneaking())
                {
                    //auto step blocks if player is not sneaking
                    player.stepHeight = 1.0F;
                }
                else
                {
                    player.stepHeight = 0.5F;
                }

            }
            else if (bootsOn == true)
            {
                bootsOn = false;

                //back to normal step height
                player.stepHeight = 0.5F;
            }

            //full set
            if (helmetOn == true && chestplateOn == true && leggingsOn == true && bootsOn == true)
            {
                //remove knockback
                knockback.applyModifier(knockbackResist);
            }
            else
            {
                knockback.removeModifier(knockbackResist);
            }
        }

    }

    @SubscribeEvent
    public void onEntityLivingFallEvent(LivingFallEvent event)
    {
        if (bootsOn = true) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onFOVUpdatevent(FOVUpdateEvent event)
    {
        if (bootsOn)
        {
            event.newfov = 1.0F;
        }
    }
}
