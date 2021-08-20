package net.dakuro.arkhesys.item;

import net.dakuro.arkhesys.fluid.ModFluids;
import net.dakuro.arkhesys.sound.ModSoundEvents;
import net.dakuro.arkhesys.utils.Registration;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.RegistryObject;

public class ModItems
{
    public static final RegistryObject<Item> ARKHESYS_ICON = Registration.ITEMS.register("arkhesys_icon",
            () -> new Item(new Item.Properties().stacksTo(1).tab(ModItemGroup.ARKHESYS_TAB)));

    public static final RegistryObject<Item> CHAOS_SHARD = Registration.ITEMS.register("chaos_shard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHAOS_GEM = Registration.ITEMS.register("chaos_gem",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MUSIC_DISC_RTC = Registration.ITEMS.register("music_disc_rtc",
            () -> new MusicDiscItem(15, ModSoundEvents.MUSIC_DISC_RTC, new Item.Properties().stacksTo(1)
                    .rarity(Rarity.create("MYSTERIOUS", TextFormatting.DARK_RED))));

    public static final RegistryObject<Item> SILVER_INGOT = Registration.ITEMS.register("silver_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SILVER_SWORD = Registration.ITEMS.register("silver_sword",
            () -> new SwordItem(CustomItemTier.SILVER, 3, -2.4F, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SILVER_SHOVEL = Registration.ITEMS.register("silver_shovel",
            () -> new ShovelItem(CustomItemTier.SILVER, 1.5F, -3.0F, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SILVER_PICKAXE = Registration.ITEMS.register("silver_pickaxe",
            () -> new PickaxeItem(CustomItemTier.SILVER, 1, -2.8F, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SILVER_AXE = Registration.ITEMS.register("silver_axe",
            () -> new AxeItem(CustomItemTier.SILVER, 5.5F, -3.0F, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SILVER_HOE = Registration.ITEMS.register("silver_hoe",
            () -> new HoeItem(CustomItemTier.SILVER, -2, -0.5F, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SILVER_HELMET = Registration.ITEMS.register("silver_helmet",
            () -> new ArmorItem(CustomArmorMaterials.SILVER_ARMOR, EquipmentSlotType.HEAD, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SILVER_CHESTPLATE = Registration.ITEMS.register("silver_chestplate",
            () -> new ArmorItem(CustomArmorMaterials.SILVER_ARMOR, EquipmentSlotType.CHEST, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SILVER_LEGGINGS = Registration.ITEMS.register("silver_leggings",
            () -> new ArmorItem(CustomArmorMaterials.SILVER_ARMOR, EquipmentSlotType.LEGS, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SILVER_BOOTS = Registration.ITEMS.register("silver_boots",
            () -> new ArmorItem(CustomArmorMaterials.SILVER_ARMOR, EquipmentSlotType.FEET, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> ASTRAAL_BUCKET = Registration.ITEMS.register("astraal_bucket",
            () -> new BucketItem(ModFluids.ASTRAAL_FLUID, new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));

    public static final RegistryObject<Item> UNIV_BOTTLE = Registration.ITEMS.register("univ_bottle",
            () -> new LingeringPotionItem(new Item.Properties().stacksTo(1)
                    .rarity(Rarity.RARE)));

    public static final RegistryObject<Item> EMPTY_BOTTLE = Registration.ITEMS.register("empty_bottle",
            () -> new Item(new Item.Properties()));

    public static void init() { }
}
