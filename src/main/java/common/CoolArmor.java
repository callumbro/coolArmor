import common.*;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;


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
    public static boolean IC2Loaded;
    public static boolean ProjectELoaded;
    public static boolean TELoaded;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //initialize items
        itemCoolHelmet = new ModArmor("coolHelmet", ItemArmor.ArmorMaterial.DIAMOND, 0);
        itemCoolChestplate = new ModArmor("coolChestplate", ItemArmor.ArmorMaterial.DIAMOND, 1);
        itemCoolLeggings = new ModArmor("coolLeggings", ItemArmor.ArmorMaterial.DIAMOND, 2);
        itemCoolBoots = new ModArmor("coolBoots", ItemArmor.ArmorMaterial.DIAMOND, 3);

        //register items
        GameRegistry.registerItem(itemCoolHelmet, "coolHelmet");
        GameRegistry.registerItem(itemCoolChestplate, "coolChestplate");
        GameRegistry.registerItem(itemCoolLeggings, "coolLeggings");
        GameRegistry.registerItem(itemCoolBoots, "coolBoots");

    }

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        //create item objects
        ItemStack coolHelmet = new ItemStack(itemCoolHelmet, 1, 0);
        ItemStack coolChestplate = new ItemStack(itemCoolChestplate, 1, 0);
        ItemStack coolLeggings = new ItemStack(itemCoolLeggings, 1, 0);
        ItemStack coolBoots = new ItemStack(itemCoolBoots, 1, 0);

        IC2Loaded = Loader.isModLoaded("IC2");
        if(IC2Loaded) {
            ItemStack iridiumPlate = new ItemStack(GameData.getItemRegistry().getRaw("IC2:itemPartIridium"), 1);
            ItemStack quantumHelmet = new ItemStack(GameData.getItemRegistry().getRaw("IC2:itemArmorQuantumHelmet"), 1, 1);
            ItemStack quantumChestplate = new ItemStack(GameData.getItemRegistry().getRaw("IC2:itemArmorQuantumChestplate"), 1, 1);
            ItemStack quantumLeggings = new ItemStack(GameData.getItemRegistry().getRaw("IC2:itemArmorQuantumLegs"), 1, 1);
            ItemStack quantumBoots = new ItemStack(GameData.getItemRegistry().getRaw("IC2:itemArmorQuantumBoots"), 1, 1);
            ItemStack mfsuStack = new ItemStack(GameData.getBlockRegistry().getRaw("IC2:blockElectric"), 1, 2);

            GameRegistry.addRecipe(coolHelmet, new Object[]{"ABA", "BCB", "B B", Character.valueOf('A'), mfsuStack, Character.valueOf('B'), iridiumPlate, Character.valueOf('C'), quantumHelmet});
            GameRegistry.addRecipe(coolChestplate, new Object[]{"ABA", "BCB", "B B", Character.valueOf('A'), mfsuStack, Character.valueOf('B'), iridiumPlate, Character.valueOf('C'), quantumChestplate});
            GameRegistry.addRecipe(coolLeggings, new Object[]{"ABA", "BCB", "B B", Character.valueOf('A'), mfsuStack, Character.valueOf('B'), iridiumPlate, Character.valueOf('C'), quantumLeggings});
            GameRegistry.addRecipe(coolBoots, new Object[]{"ABA", "BCB", "B B", Character.valueOf('A'), mfsuStack, Character.valueOf('B'), iridiumPlate, Character.valueOf('C'), quantumBoots});
        }

        ProjectELoaded = Loader.isModLoaded("ProjectE");
        if (ProjectELoaded)
        {
            ItemStack gemHelmet = new ItemStack((GameData.getItemRegistry().getRaw("ProjectE:item.pe_gem_armor_0")), 1, 0);
            ItemStack gemChestplate = new ItemStack((GameData.getItemRegistry().getRaw("ProjectE:item.pe_gem_armor_1")), 1, 0);
            ItemStack gemLeggings = new ItemStack((GameData.getItemRegistry().getRaw("ProjectE:item.pe_gem_armor_2")), 1, 0);
            ItemStack gemBoots = new ItemStack((GameData.getItemRegistry().getRaw("ProjectE:item.pe_gem_armor_3")), 1, 0);
            ItemStack KleinStar = new ItemStack((GameData.getItemRegistry().getRaw("ProjectE:item.pe_klein_star")), 1, 5);
            ItemStack darkMatter = new ItemStack((GameData.getItemRegistry().getRaw("ProjectE:item.pe_matter")), 1, 0);

            GameRegistry.addRecipe(coolHelmet, new Object[]{"ABA", "ACA", "A A", Character.valueOf('A'), darkMatter, Character.valueOf('B'), KleinStar, Character.valueOf('C'), gemHelmet});
            GameRegistry.addRecipe(coolChestplate, new Object[]{"ABA", "ACA", "A A", Character.valueOf('A'), darkMatter, Character.valueOf('B'), KleinStar, Character.valueOf('C'), gemChestplate});
            GameRegistry.addRecipe(coolLeggings, new Object[]{"ABA", "ACA", "A A", Character.valueOf('A'), darkMatter, Character.valueOf('B'), KleinStar, Character.valueOf('C'), gemLeggings});
            GameRegistry.addRecipe(coolBoots, new Object[]{"ABA", "ACA", "A A", Character.valueOf('A'), darkMatter, Character.valueOf('B'), KleinStar, Character.valueOf('C'), gemBoots});
        }

        TELoaded = Loader.isModLoaded("ThermalExpansion");
        if (TELoaded)
        {
            ItemStack eleHelmet = new ItemStack((GameData.getItemRegistry().getRaw("ThermalFoundation:armor.helmetElectrum")), 1, 0);
            ItemStack eleChestplate = new ItemStack((GameData.getItemRegistry().getRaw("ThermalFoundation:armor.plateElectrum")), 1, 0);
            ItemStack eleLeggings = new ItemStack((GameData.getItemRegistry().getRaw("ThermalFoundation:armor.legsElectrum")), 1, 0);
            ItemStack eleBoots = new ItemStack((GameData.getItemRegistry().getRaw("ThermalFoundation:armor.bootsElectrum")), 1, 0);
            ItemStack ingotEnderium = new ItemStack((GameData.getItemRegistry().getRaw("ThermalFoundation:material")), 1, 76);
            ItemStack capEnderium = new ItemStack((GameData.getItemRegistry().getRaw("ThermalExpansion:capacitor")), 1, 5);

            GameRegistry.addRecipe(coolHelmet, new Object[]{"ABA", "ACA", "A A", Character.valueOf('A'), ingotEnderium, Character.valueOf('B'), capEnderium, Character.valueOf('C'), eleHelmet});
            GameRegistry.addRecipe(coolChestplate, new Object[]{"ABA", "ACA", "A A", Character.valueOf('A'), ingotEnderium, Character.valueOf('B'), capEnderium, Character.valueOf('C'), eleChestplate});
            GameRegistry.addRecipe(coolLeggings, new Object[]{"ABA", "ACA", "A A", Character.valueOf('A'), ingotEnderium, Character.valueOf('B'), capEnderium, Character.valueOf('C'), eleLeggings});
            GameRegistry.addRecipe(coolBoots, new Object[]{"ABA", "ACA", "A A", Character.valueOf('A'), ingotEnderium, Character.valueOf('B'), capEnderium, Character.valueOf('C'), eleBoots});
        }

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }


}
