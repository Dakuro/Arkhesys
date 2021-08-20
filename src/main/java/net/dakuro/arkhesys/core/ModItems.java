package net.dakuro.arkhesys.core;

import net.dakuro.arkhesys.ARKHESYS;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ARKHESYS.MODID);

    public static final Rarity CHAOTIC = Rarity.create("chaotic", ChatFormatting.DARK_RED);

    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver",
            () -> new Item(new Item.Properties().tab(ModTab.ARKHESYS_TAB))
    );

    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> SILVER_SWORD = ITEMS.register("silver_sword",
            () -> new SwordItem(ModTiers.SILVER, 3, -2.4F, new Item.Properties())
    );

    public static final RegistryObject<Item> SILVER_PICKAXE = ITEMS.register("silver_pickaxe",
            () -> new PickaxeItem(ModTiers.SILVER, 1, -2.8F, new Item.Properties())
    );

    public static final RegistryObject<Item> SILVER_AXE = ITEMS.register("silver_axe",
            () -> new AxeItem(ModTiers.SILVER, 5.5F, -3.0F, new Item.Properties())
    );

    public static final RegistryObject<Item> SILVER_SHOVEL = ITEMS.register("silver_shovel",
            () -> new ShovelItem(ModTiers.SILVER, 1.5F, -3.0F, new Item.Properties())
    );

    public static final RegistryObject<Item> SILVER_HOE = ITEMS.register("silver_hoe",
            () -> new HoeItem(ModTiers.SILVER, -2, -0.5F, new Item.Properties())
    );

    public static final RegistryObject<Item> SILVER_HELMET = ITEMS.register("silver_helmet",
            () -> new ArmorItem(ModArmorMaterials.SILVER, EquipmentSlot.HEAD, new Item.Properties())
    );

    public static final RegistryObject<Item> SILVER_CHESTPLATE = ITEMS.register("silver_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SILVER, EquipmentSlot.CHEST, new Item.Properties())
    );

    public static final RegistryObject<Item> SILVER_LEGGINGS = ITEMS.register("silver_leggings",
            () -> new ArmorItem(ModArmorMaterials.SILVER, EquipmentSlot.LEGS, new Item.Properties())
    );

    public static final RegistryObject<Item> SILVER_BOOTS = ITEMS.register("silver_boots",
            () -> new ArmorItem(ModArmorMaterials.SILVER, EquipmentSlot.FEET, new Item.Properties())
    );

    public static final RegistryObject<Item> CHAOS_SHARD = ITEMS.register("chaos_shard",
            () -> new Item(new Item.Properties().rarity(CHAOTIC))
    );

    public static final RegistryObject<Item> CHAOS_GEM = ITEMS.register("chaos_gem",
            () -> new Item(new Item.Properties().rarity(CHAOTIC))
    );

    public static final RegistryObject<Item> MUSIC_DISC_RTC = ITEMS.register("music_disc_rtc",
            () -> new RecordItem(15, ModSoundEvents.MUSIC_DISC_RTC, new Item.Properties().rarity(CHAOTIC).stacksTo(1))
    );

    public static final RegistryObject<Item> EMPTY_BOTTLE = ITEMS.register("empty_bottle",
            () -> new Item(new Item.Properties())
    );

}