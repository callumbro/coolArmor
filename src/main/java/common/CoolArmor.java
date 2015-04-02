import common.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;


/**
 * Created by Callum on 02/27/2015.
 */

@Mod(modid = "coolarmor", name = "Cool Armor", version = CoolArmor.version)

public class CoolArmor
{
    public static final String version = "1.0.0";

    @Instance("coolarmor")
    public static CoolArmor instance;

    //create items
    public static Item itemCoolChestplate;
    public static Item itemCoolLeggings;
    public static Item itemCoolHelmet;
    public static Item itemCoolBoots;

    @EventHandler
    public void preInit(FMLPreInitializationEvent preInitializationEvent)
    {
        //initialize items
        itemCoolHelmet = new ModArmor("coolHelmet", ItemArmor.ArmorMaterial.DIAMOND, 0);
        itemCoolChestplate = new ModArmor("coolChestplate", ItemArmor.ArmorMaterial.DIAMOND, 1);
        itemCoolLeggings = new ModArmor("coolLeggings", ItemArmor.ArmorMaterial.DIAMOND, 2);
        itemCoolBoots = new ModArmor("coolBoots", ItemArmor.ArmorMaterial.DIAMOND, 3);

        //register items
        GameRegistry.registerItem(itemCoolChestplate, "coolChestplate");
        GameRegistry.registerItem(itemCoolLeggings, "coolLeggings");
        GameRegistry.registerItem(itemCoolHelmet, "coolHelmet");
        GameRegistry.registerItem(itemCoolBoots, "coolBoots");


    }
    public void load(FMLInitializationEvent initializationEvent)
    {

    }
    public void postInit(FMLPostInitializationEvent postInitializationEvent)
    {

    }



}
